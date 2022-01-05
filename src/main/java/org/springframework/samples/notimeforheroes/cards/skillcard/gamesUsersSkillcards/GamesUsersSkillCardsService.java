package org.springframework.samples.notimeforheroes.cards.skillcard.gamesUsersSkillcards;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.cards.skillcard.SkillCard;
import org.springframework.samples.notimeforheroes.cards.skillcard.SkillCardsService;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUser;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUserService;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.stereotype.Service;

@Service
public class GamesUsersSkillCardsService {

    @Autowired
    GamesUsersSkillCardsRepository gamesUsersSkillCardsRepository;

    @Autowired
    GameUserService gamesUsersService;

    @Autowired
    SkillCardsService skillCardsService;

    public Collection<GamesUsersSkillCards> findAll(){
        return gamesUsersSkillCardsRepository.findAll();
    }

    public Optional<GamesUsersSkillCards> findByGameUserSkill(GameUser gameUser, SkillCard skillCard){
        return gamesUsersSkillCardsRepository.findByGameUserSkill(gameUser, skillCard);
    }

    public Optional<GamesUsersSkillCards> findByGameUserSkill(Game game, User user, SkillCard skillCard){
        return gamesUsersSkillCardsRepository.findByGameUserSkill(gamesUsersService.findByGameAndUser(game, user).get(), skillCard);
    }

    public void createGameUserSkillCard(@Valid GamesUsersSkillCards gameUserSkillCard){
        gamesUsersSkillCardsRepository.save(gameUserSkillCard);
    }


    public Collection<GamesUsersSkillCards> findAllAvailableSkillsandOnTableByGameAndUser(Game game, User user){
		return gamesUsersSkillCardsRepository.findAllAvailableSkillsAndOnTableByGameAndUser(game, user);
	}

    @Transactional
    public void useSkill(Game game, User user, SkillCard skillCard) {   //Descarta la skillcard
        GamesUsersSkillCards gamesUsersSkillCards = this.findByGameUserSkill(game, user, skillCard).get();
		gamesUsersSkillCards.setSkillState(SkillState.DISCARD);
		this.createGameUserSkillCard(gamesUsersSkillCards);
    }

    public void drawCards(Game game, User user, Integer cantidad) {
        List<SkillCard> onDeckCards = skillCardsService.findAllOnDeckSkillsByGameAndUser(game, user);
        if(onDeckCards.size() <= cantidad){     //Si le quedan menos cartas en el mazo de las que tiene que robar
            GameUser player = gamesUsersService.findByGameAndUser(game, user).get();
            player.setHeroeHealth(player.getHeroeHealth() - 1);
            List<SkillCard> discardedCards = skillCardsService.findAllOnDiscardedSkillsByGameAndUser(game, user);
            discardedCards.stream().forEach(c -> {
                GamesUsersSkillCards gusc = findByGameUserSkill(game, user, c).get();
                gusc.setSkillState(SkillState.ONDECK);
                gamesUsersSkillCardsRepository.save(gusc);
            });
            gamesUsersService.createGameUser(player);
        }
        for(int i = 0; i<cantidad; i++){
            GamesUsersSkillCards gusc = findByGameUserSkill(game, user, onDeckCards.get(i)).get();
            gusc.setSkillState(SkillState.ONHAND);
            createGameUserSkillCard(gusc);
        }
    }

    @Transactional
    public void deleteGamesUsersSkillCard(GamesUsersSkillCards g){
        gamesUsersSkillCardsRepository.delete(g);
    }

    

}


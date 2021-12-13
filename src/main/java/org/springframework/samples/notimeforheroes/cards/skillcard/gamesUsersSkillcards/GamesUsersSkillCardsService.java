package org.springframework.samples.notimeforheroes.cards.skillcard.gamesUsersSkillcards;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.cards.skillcard.SkillCard;
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


}


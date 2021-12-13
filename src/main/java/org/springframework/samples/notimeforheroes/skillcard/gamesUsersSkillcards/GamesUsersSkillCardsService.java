package org.springframework.samples.notimeforheroes.skillcard.gamesUsersSkillcards;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.gamesUsers.GameUser;
import org.springframework.samples.notimeforheroes.gamesUsers.GameUserService;
import org.springframework.samples.notimeforheroes.skillcard.SkillCard;
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

    public Collection<GamesUsersSkillCards> findByGameUserSkill(GameUser gameUser, SkillCard skillCard){
        return gamesUsersSkillCardsRepository.findByGameUserSkill(gameUser, skillCard);
    }

    public Collection<GamesUsersSkillCards> findByGameUserSkill(Game game, User user, SkillCard skillCard){
        /*
        Este método y el anterior devuelven Collection en vez de un Optional porque dos filas de GamesUsersSkillcards
        pueden tener el mismo (gamesUsersId, skillCardId) si un jugador tiene la misma carta dos veces
        */
        return gamesUsersSkillCardsRepository.findByGameUserSkill(gamesUsersService.findByGameAndUser(game, user).get(), skillCard);
    }


}


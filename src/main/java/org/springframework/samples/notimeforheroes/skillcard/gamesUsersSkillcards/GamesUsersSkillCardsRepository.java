package org.springframework.samples.notimeforheroes.skillcard.gamesUsersSkillcards;

import java.util.Collection;
import java.util.Optional;

import org.hibernate.annotations.CollectionId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.gamesUsers.GameUser;
import org.springframework.samples.notimeforheroes.skillcard.SkillCard;
import org.springframework.samples.notimeforheroes.user.User;


public interface GamesUsersSkillCardsRepository extends CrudRepository<GamesUsersSkillCards, Integer>{

    Collection<GamesUsersSkillCards> findAll();

    @Query(nativeQuery = true, value = "SELECT * FROM GAMES_USERS_SKILL_CARDS gus WHERE gus.GAME_USER_ID = ?1 AND gus.skill_cards_id = ?2")
    Optional<GamesUsersSkillCards> findByGameUserSkill(GameUser gameUser, SkillCard skillCard);
    
}

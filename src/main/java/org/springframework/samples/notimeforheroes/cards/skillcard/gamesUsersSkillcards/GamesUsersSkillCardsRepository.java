package org.springframework.samples.notimeforheroes.cards.skillcard.gamesUsersSkillcards;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.notimeforheroes.cards.skillcard.SkillCard;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUser;
import org.springframework.samples.notimeforheroes.user.User;

public interface GamesUsersSkillCardsRepository extends CrudRepository<GamesUsersSkillCards, Integer>{

    Collection<GamesUsersSkillCards> findAll();

    @Query(nativeQuery = true, value = "SELECT * FROM GAMES_USERS_SKILL_CARDS gus WHERE gus.GAME_USER_ID = ?1 AND gus.skill_cards_id = ?2")
    Optional<GamesUsersSkillCards> findByGameUserSkill(GameUser gameUser, SkillCard skillCard);
    
    @Query(nativeQuery = true, value = "SELECT s.*, gus.skill_state FROM GAMES_USERS_SKILL_CARDS gus JOIN  GAMES_USERS gu JOIN SKILLS s WHERE gu.FK_GAME = ?1 AND gu.FK_USER = ?2 AND gu.ID = gus.GAME_USER_ID AND gus.SKILL_CARDS_ID  = s.ID AND (gus.skill_state = 1 OR gus.skill_state = 0)")
	Collection<GamesUsersSkillCards> findAllAvailableSkillsAndOnTableByGameAndUser(Game game, User user);
}

package org.springframework.samples.notimeforheroes.cards.skillcard.gamesUsersSkillcards;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.notimeforheroes.cards.skillcard.SkillCard;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUser;


public interface GamesUsersSkillCardsRepository extends CrudRepository<GamesUsersSkillCards, Integer>{

    Collection<GamesUsersSkillCards> findAll();

    @Query(nativeQuery = true, value = "SELECT * FROM GAMES_USERS_SKILL_CARDS gus WHERE gus.GAME_USER_ID = ?1 AND gus.skill_cards_id = ?2")
    Optional<GamesUsersSkillCards> findByGameUserSkill(GameUser gameUser, SkillCard skillCard);
    
}

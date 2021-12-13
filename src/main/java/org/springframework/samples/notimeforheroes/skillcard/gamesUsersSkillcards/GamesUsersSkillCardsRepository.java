package org.springframework.samples.notimeforheroes.skillcard.gamesUsersSkillcards;

import java.util.Collection;

import org.hibernate.annotations.CollectionId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.notimeforheroes.gamesUsers.GameUser;
import org.springframework.samples.notimeforheroes.skillcard.SkillCard;


public interface GamesUsersSkillCardsRepository extends CrudRepository<GamesUsersSkillCards, Integer>{

    Collection<GamesUsersSkillCards> findAll();

    @Query(nativeQuery = true, value = "SELECT * FROM games_users_skill_cards WHERE game_user_id = ?1 AND skill_card_id = ?2")
    Collection<GamesUsersSkillCards> findByGameUserSkill(GameUser gameUser, SkillCard skillCard);
    
}

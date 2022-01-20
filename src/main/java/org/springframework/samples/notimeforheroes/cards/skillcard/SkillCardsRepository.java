package org.springframework.samples.notimeforheroes.cards.skillcard;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCard;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.user.User;

public interface SkillCardsRepository extends CrudRepository<SkillCard, Integer>{
	
	Page<SkillCard> findAll(Pageable page);

	
	Collection<SkillCard> findAll();
	
	Collection<SkillCard> findByColor(String color);
	
	SkillCard findByName(String name);

	@Query(nativeQuery = true, value = "SELECT s.* FROM GAMES_USERS gu JOIN GAMES_USERS_SKILL_CARDS gus JOIN SKILLS s WHERE gu.FK_GAME = ?1 AND gu.FK_USER = ?2 AND gu.ID = gus.GAME_USER_ID AND gus.SKILL_CARDS_ID  = s.ID")
	Collection<SkillCard> findAllSkillsByGameAndUser(Game game, User user);

	@Query(nativeQuery = true, value = "SELECT s.* FROM GAMES_USERS gu JOIN GAMES_USERS_SKILL_CARDS gus JOIN SKILLS s WHERE gu.FK_GAME = ?1 AND gu.FK_USER = ?2 AND gu.ID = gus.GAME_USER_ID AND gus.SKILL_CARDS_ID  = s.ID AND gus.skill_state = 1")
	List<SkillCard> findAllAvailableSkillsByGameAndUser(Game game, User user);

	@Query(nativeQuery = true, value = "SELECT s.* FROM GAMES_USERS gu JOIN GAMES_USERS_SKILL_CARDS gus JOIN SKILLS s WHERE gu.FK_GAME = ?1 AND gu.FK_USER = ?2 AND gu.ID = gus.GAME_USER_ID AND gus.SKILL_CARDS_ID  = s.ID AND gus.skill_state = 0")
	List<SkillCard> findAllOnDeckSkillsByGameAndUser(Game game, User user);

	@Query(nativeQuery = true, value = "SELECT s.* FROM GAMES_USERS gu JOIN GAMES_USERS_SKILL_CARDS gus JOIN SKILLS s WHERE gu.FK_GAME = ?1 AND gu.FK_USER = ?2 AND gu.ID = gus.GAME_USER_ID AND gus.SKILL_CARDS_ID  = s.ID AND gus.skill_state = 2")
	List<SkillCard> findAllOnDiscardedSkillsByGameAndUser(Game game, User user);
	
}

package org.springframework.samples.notimeforheroes.game;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.notimeforheroes.user.User;

public interface GameRepository extends CrudRepository<Game, Integer>{
//
	
	Collection<Game> findAll();
	
	@Query("SELECT g FROM games g WHERE g.isInProgress = FALSE")
	Collection<Game> findAllEnded();

	Collection<Game> findAllByIsInProgress(Boolean isInProgress);

	Collection<Game> findAllByCreator(User user);

	@Query("SELECT g FROM games g WHERE g.joinCode = ?1")
	Optional<Game> findByJoinCode(String joinCode);

	@Query(nativeQuery = true, value = "SELECT DISTINCT g.* FROM GAMES g JOIN GAMES_USERS gu WHERE g.id= gu.fk_game AND (g.is_public=true or (g.is_public=false and gu.fk_user=?1))")
	Collection<Game> findPublicAndOwn(User user);
	
	@Query("SELECT g FROM games g WHERE g.winner = user")
	Collection<Game> findByWinner(User user);

	@Query(nativeQuery = true, value = "SELECT * FROM Games g JOIN Games_Users gu WHERE g.id = gu.fk_game AND gu.fk_user = ?1")
	Collection<Game> findByUser(User user);
}

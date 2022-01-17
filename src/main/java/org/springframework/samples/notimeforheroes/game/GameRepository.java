package org.springframework.samples.notimeforheroes.game;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.notimeforheroes.user.User;

public interface GameRepository extends CrudRepository<Game, Integer>{
	
	Collection<Game> findAll();
	
	@Query("SELECT g FROM games g WHERE g.isInProgress = FALSE AND g.duration != 0")
	Collection<Game> findAllEnded();

	@Query("SELECT g FROM games g WHERE g.isInProgress = TRUE")
	Collection<Game> findAllByIsInProgress(Boolean isInProgress);

	@Query("SELECT g FROM games g WHERE g.creator = ?1")
	Collection<Game> findAllByCreator(User user);

	@Query("SELECT g FROM games g WHERE g.joinCode = ?1")
	Optional<Game> findByJoinCode(String joinCode);

	@Query(nativeQuery = true, value = "SELECT DISTINCT g.* FROM GAMES g JOIN GAMES_USERS gu WHERE g.id= gu.fk_game AND (g.is_public=true or (g.is_public=false and gu.fk_user=?1))")
	Collection<Game> findPublicAndOwn(User user);
	
	@Query("SELECT g FROM games g WHERE g.winner = ?1")
	Collection<Game> findByWinner(User user);

	@Query(nativeQuery=true, value="SELECT COUNT(g.*) FROM Games g JOIN Games_Users gu WHERE g.id = gu.fk_game AND gu.fk_user = ?1 AND g.date BETWEEN ?2 AND ?3")
	Integer findBetweenDates(User user, Date date1, Date date2);

	@Query(nativeQuery = true, value = "SELECT * FROM Games g JOIN Games_Users gu WHERE g.id = gu.fk_game AND gu.fk_user = ?1")
	Collection<Game> findByUser(User user);

//Obtiene la partida en curso del usuario que se le pasa por parámetro
	@Query(nativeQuery = true, value = "SELECT g.* FROM Games g JOIN Games_Users gu WHERE g.id = gu.fk_game AND g.is_in_progress = TRUE AND gu.fk_user = ?1")
	Optional<Game> findGameInProgressByUser(User user);

	@Query(nativeQuery = true, value = "SELECT  u.username as username, count(winner) as count  FROM GAMES g join users u where u.id=winner group by winner order by count(winner) desc, u.username asc LIMIT 10")
	List<Tuple> findRanking();
}

package org.springframework.samples.notimeforheroes.game.gamesUsers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.user.User;

public interface GameUserRepository extends CrudRepository<GameUser, Integer> {

    Collection<GameUser> findAll();
    
    @Query(nativeQuery = true, value = "SELECT * FROM games_users gu WHERE gu.fk_game=?1")
    List<GameUser> findAllByGame(Game game);

    @Query(nativeQuery = true, value = "SELECT * FROM Games_Users gu WHERE gu.fk_game = ?1 ORDER BY heroe_health")
    List<GameUser> findAllByGameOrderByHealth(Game game);

    @Query(nativeQuery = true, value = "SELECT * FROM Games_Users gu WHERE gu.fk_game = ?1 AND gu.heroe_health > 0")
    List<GameUser> findByGameUsersAlive(Game game);

    @Query(nativeQuery = true, value = "SELECT * FROM games_users gu WHERE gu.fk_game=?1 AND gu.fk_user=?2")
    Optional<GameUser> findByGameAndUser(Game game, User user);

    @Query(nativeQuery = true, value = "SELECT heroe_id FROM games_users gu WHERE gu.fk_game=?1 AND gu.fk_user=?2")
    Optional<Integer> findHeroeOfGameUser(Integer gameId, Integer userId);
    
    @Query(nativeQuery = true, value = "SELECT DISTINCT gu.gold FROM games_users gu JOIN games g WHERE g.is_in_progress=FALSE AND gu.fk_user = ?1 AND gu.gold IS NOT NULL")
    Collection<Integer> findAllGoldByUser(User user);
    
    @Query(nativeQuery = true, value = "SELECT DISTINCT gu.glory FROM games_users gu JOIN games g WHERE g.is_in_progress=FALSE AND gu.fk_user = ?1 AND gu.glory IS NOT NULL")
    Collection<Integer> findAllGloryByUser(User user);

    @Query(nativeQuery = true, value = "SELECT gu.heroe_id FROM games_users gu WHERE gu.fk_user = ?1 AND gu.heroe_id IS NOT NULL")
    Collection<Integer> getAllHeroesByUser(User user);

    
}
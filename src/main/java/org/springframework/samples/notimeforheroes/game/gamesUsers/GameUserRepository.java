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
    Collection<GameUser> findAllByGame(Game game);

    @Query(nativeQuery = true, value = "SELECT * FROM games_users gu WHERE gu.fk_game=?1 AND gu.fk_user=?2")
    Optional<GameUser> findByGameAndUser(Integer gameId, Integer userId);

    @Query(nativeQuery = true, value = "SELECT heroe_id FROM games_users gu WHERE gu.fk_game=?1 AND gu.fk_user=?2")
    Optional<Integer> findHeroeOfGameUser(Integer gameId, Integer userId);
    
    @Query(nativeQuery = true, value = "SELECT items_id FROM GAMES_USERS_ITEMS gui JOIN GAMES_USERS gu WHERE gui.game_user_id = gu.id AND gu.fk_game=?1 AND gu.fk_user=?2")
    Optional<List<Integer>> findItemsOfGameUser(Integer gameId, Integer userId);


    @Query(nativeQuery = true, value = "SELECT gu.gold FROM games_users gu WHERE gu.fk_user = ?1")
    Collection<Integer> findAllGoldByUser(User user);
    
    @Query(nativeQuery = true, value = "SELECT gu.glory FROM games_users gu WHERE gu.fk_user = ?1")
    Collection<Integer> findAllGloryByUser(User user);
    
    @Query(nativeQuery = true, value = "SELECT gu.heroe_id FROM games_users gu WHERE gu.fk_user = ?1")
    Collection<Integer> getHeroeFav(User user);
}
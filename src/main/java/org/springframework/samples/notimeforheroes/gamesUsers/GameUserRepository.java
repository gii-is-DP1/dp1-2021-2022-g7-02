package org.springframework.samples.notimeforheroes.gamesUsers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GameUserRepository extends CrudRepository<GameUser, Integer> {

    Collection<GameUser> findAll();

    @Query(nativeQuery = true, value = "SELECT * FROM games_users gu WHERE gu.fk_game=?1 AND gu.fk_user=?2")
    Optional<GameUser> findByGameAndUser(Integer gameId, Integer userId);

    @Query(nativeQuery = true, value = "SELECT heroe_id FROM games_users gu WHERE gu.fk_game=?1 AND gu.fk_user=?2")
    Optional<Integer> findHeroeOfGameUser(Integer gameId, Integer userId);
    
    @Query(nativeQuery = true, value = "SELECT items_id FROM GAMES_USERS_ITEMS gui JOIN GAMES_USERS gu WHERE gui.game_user_id = gu.id AND gu.fk_game=?1 AND gu.fk_user=?2")
    Optional<List<Integer>> findItemsOfGameUser(Integer gameId, Integer userId);
}
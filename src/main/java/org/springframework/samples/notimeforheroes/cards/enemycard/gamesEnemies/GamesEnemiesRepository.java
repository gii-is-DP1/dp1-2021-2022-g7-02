package org.springframework.samples.notimeforheroes.cards.enemycard.gamesEnemies;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyCard;
import org.springframework.samples.notimeforheroes.game.Game;

public interface GamesEnemiesRepository extends CrudRepository<GamesEnemies, Integer>{
    
    Collection<GamesEnemies> findAll();

    @Query(nativeQuery = true, value = "SELECT * FROM games_enemies ge WHERE ge.fk_game = ?1")
    Collection<GamesEnemies> findAllInGame(Game game);

    @Query(nativeQuery = true, value = "SELECT * FROM games_enemies ge WHERE ge.fk_game = ?1 AND ge.enemy_state = 0")
    Collection<GamesEnemies> findAllInGameOnTable(Game game);

    @Query(nativeQuery = true, value = "SELECT * FROM games_enemies ge WHERE ge.fk_game = ?1 AND ge.fk_enemy = ?2")
    Optional<GamesEnemies> findByGameAndEnemy(Game game, EnemyCard enemyCard);
}

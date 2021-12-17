package org.springframework.samples.notimeforheroes.cards.enemycard;


	
import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.notimeforheroes.cards.enemycard.gamesEnemies.GamesEnemies;
import org.springframework.samples.notimeforheroes.game.Game;



		
public interface EnemyCardRepository extends CrudRepository<EnemyCard, Integer>{

			
		Collection<EnemyCard> findAll();

		Collection<EnemyCard> findAllByIsBoss(Boolean isBoss);

		@Query(nativeQuery = true, value = "SELECT e.* FROM Enemies e JOIN Games_Enemies ge WHERE e.id = ge.fk_enemy AND ge.fk_game = ?1 AND ge.enemy_state = 0;")
		Collection<EnemyCard> findOnTableEnemiesByGame(Game game);

		@Query(nativeQuery = true, value = "SELECT e.* FROM Games_Enemies ge JOIN Enemies e WHERE ge.fk_enemy = e.id AND ge.id = ?1")
		Optional<EnemyCard> findEnemyOfGamesEnemies(GamesEnemies ge);
			
			
}




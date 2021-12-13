package org.springframework.samples.notimeforheroes.cards.heroecard;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.notimeforheroes.game.Game;

public interface HeroeCardsRepository extends CrudRepository<HeroeCard, Integer>{

		
		Collection<HeroeCard> findAll();

        Optional<HeroeCard> findByName(String heroe);
        
        Collection<HeroeCard> findByColor(String color);

        @Query(nativeQuery = true, value = "SELECT h.* FROM Heroes h JOIN Games_Users gu WHERE gu.heroe_id = h.id AND h.color = ?1 AND gu.fk_game = ?2")
        Collection<HeroeCard> findByColorAndGame(String color, Game game);

        @Query(nativeQuery = true, value = "SELECT h.* FROM games_users gu JOIN heroes h WHERE gu.fk_game=?1 AND gu.heroe_id=h.id")
        Collection<HeroeCard> findHeroesOfGame(Game game);
		
		
	}

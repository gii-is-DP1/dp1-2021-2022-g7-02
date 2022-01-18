package org.springframework.samples.notimeforheroes.cards.marketcard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.notimeforheroes.game.Game;

public interface MarketCardsRepository extends CrudRepository<MarketCard, Integer>{

	Collection<MarketCard> findAll();

	@Query(nativeQuery = true, value = "SELECT m.* FROM games_markets gm JOIN market m WHERE gm.fk_game=?1 AND gm.fk_market = m.id")
	//@Query("SELECT gm.fk_market FROM GameMarket gm WHERE gm.Game.id=?1")
	Collection<MarketCard> findAllByGame(Game game);

	@Query(nativeQuery = true, value = "SELECT m.* FROM games_markets gm JOIN market m WHERE gm.fk_game=?1 AND gm.fk_market = m.id AND gm.item_state = 0")
	//@Query("SELECT m FROM GameMarket gm JOIN MarketCard m WHERE gm.Game.id=?1 AND gm.Market.id = m.id AND gm.itemState = 0")
	Collection<MarketCard> findAllByGameAndOnDeck(Game game);

	@Query(nativeQuery = true, value = "SELECT m.* FROM games_markets gm JOIN market m WHERE gm.fk_game=?1 AND gm.fk_market = m.id AND gm.item_state = 1")
	//@Query("SELECT m FROM GameMarket gm JOIN MarketCard m WHERE gm.Game.id=?1 AND gm.Market.id = m.id AND gm.itemState = 1")
	Collection<MarketCard> findAllByGameAndOnTable(Game game);
	
	
}

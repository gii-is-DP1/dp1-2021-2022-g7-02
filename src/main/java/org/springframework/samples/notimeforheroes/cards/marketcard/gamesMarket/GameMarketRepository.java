package org.springframework.samples.notimeforheroes.cards.marketcard.gamesMarket;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.notimeforheroes.game.Game;

public interface GameMarketRepository extends CrudRepository<GameMarket, Integer> {

    Collection<GameMarket> findAll();

    @Query(nativeQuery = true, value = "SELECT * FROM games_markets gm WHERE gm.fk_game = ?1")
    //@Query("SELECT gm FROM GameMarket gm WHERE gm.game.id = ?1")
    Collection<GameMarket> findAllInGame(Game game);
    
    @Query(nativeQuery = true, value = "SELECT * FROM games_markets gm WHERE gm.fk_game = ?1 AND gm.fk_market = ?2")
    //@Query("SELECT gm FROM GameMarket gm WHERE gm.game.id = ?1 AND gm.market.id = ?2")
    GameMarket findOneItemInGame(Game game, int id);
}
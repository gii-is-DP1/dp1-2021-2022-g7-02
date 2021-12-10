package org.springframework.samples.notimeforheroes.gamesMarket;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GameMarketRepository extends CrudRepository<GameMarket, Integer> {

    Collection<GameMarket> findAll();

    @Query(nativeQuery = true, value = "SELECT * FROM games_markets gm WHERE gm.fk_game=?1")
    Optional<GameMarket> findByGame(Integer gameId);

    
}
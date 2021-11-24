package org.springframework.samples.notimeforheroes.cards.marketcard;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface MarketCardsRepository extends CrudRepository<MarketCard, Integer>{

	Collection<MarketCard> findAll();
	
}

package org.springframework.samples.notimeforheroes.cards.heroecard;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface HeroeCardsRepository extends CrudRepository<HeroeCard, Integer>{

		
		Collection<HeroeCard> findAll();
		
		
	}
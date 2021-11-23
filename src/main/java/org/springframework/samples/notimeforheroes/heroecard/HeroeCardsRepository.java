package org.springframework.samples.notimeforheroes.heroecard;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface HeroeCardsRepository extends CrudRepository<HeroeCards, Integer>{

		
		Collection<HeroeCards> findAll();
		
		
	}

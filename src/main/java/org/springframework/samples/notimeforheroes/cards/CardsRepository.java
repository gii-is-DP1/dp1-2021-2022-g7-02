package org.springframework.samples.notimeforheroes.cards;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;



	
public interface CardsRepository extends CrudRepository<Cards, Integer>{

		
		Collection<Cards> findAll();
		
		
		
	}

package org.springframework.samples.notimeforheroes.heroecard;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface HeroeCardsRepository extends CrudRepository<HeroeCard, Integer>{

		
		Collection<HeroeCard> findAll();

        HeroeCard findByName(String heroe);
        
        HeroeCard findByColor(String color);

		
		
	}

package org.springframework.samples.notimeforheroes.scenecard;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface SceneCardsRepository extends CrudRepository<SceneCards, Integer>{

	Collection<SceneCards> findAll();
	
	
}

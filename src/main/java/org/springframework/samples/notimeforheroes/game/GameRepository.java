package org.springframework.samples.notimeforheroes.game;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer>{

	
	Collection<Game> findAll();
	
	@Query("SELECT g FROM games g WHERE g.isInProgress = 0")
	Collection<Game> findAllEnded();

	Collection<Game> findAllByIsInProgress(Integer isInProgress);
	

	
}

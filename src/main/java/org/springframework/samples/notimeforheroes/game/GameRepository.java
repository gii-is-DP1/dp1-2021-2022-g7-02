package org.springframework.samples.notimeforheroes.game;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.notimeforheroes.user.User;

public interface GameRepository extends CrudRepository<Game, Integer>{

	
	Collection<Game> findAll();
	
	@Query("SELECT g FROM games g WHERE g.isInProgress = FALSE")
	Collection<Game> findAllEnded();

	Collection<Game> findAllByIsInProgress(Boolean isInProgress);

	Collection<Game> findAllByCreator(User user);
	

	
}

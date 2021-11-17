package org.springframework.samples.notimeforheroes.game;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.notimeforheroes.user.User;

public interface GameRepository extends CrudRepository<Game, Integer>{

	
	Collection<Game> findAll();
	
	@Query("SELECT g FROM games g WHERE g.isInProgress = FALSE")
	Collection<Game> findAllEnded();

	Collection<Game> findAllByIsInProgress(Boolean isInProgress);

	Collection<Game> findAllByCreator(User user);

	@Query("SELECT g FROM games g WHERE g.joinCode = ?1")
	Optional<Game> findByJoinCode(String joinCode);

	
	

	
}

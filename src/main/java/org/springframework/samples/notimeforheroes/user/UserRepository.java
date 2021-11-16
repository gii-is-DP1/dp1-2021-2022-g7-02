package org.springframework.samples.notimeforheroes.user;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.notimeforheroes.game.Game;

public interface UserRepository extends CrudRepository<User, Integer>{

	
	Collection<User> findAll();

	Optional<User> findByUsername(String username);

	
}

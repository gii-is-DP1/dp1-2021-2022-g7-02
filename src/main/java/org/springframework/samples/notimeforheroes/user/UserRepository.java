package org.springframework.samples.notimeforheroes.user;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{

	
	Collection<User> findAll();

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);
}

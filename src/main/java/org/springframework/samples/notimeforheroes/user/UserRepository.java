package org.springframework.samples.notimeforheroes.user;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{

	
	Collection<User> findAll();
	
}

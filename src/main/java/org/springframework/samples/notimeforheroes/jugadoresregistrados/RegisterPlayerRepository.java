package org.springframework.samples.notimeforheroes.jugadoresregistrados;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface RegisterPlayerRepository extends CrudRepository<RegisterPlayer, Integer>{

	
	Collection<RegisterPlayer> findAll();
	
}

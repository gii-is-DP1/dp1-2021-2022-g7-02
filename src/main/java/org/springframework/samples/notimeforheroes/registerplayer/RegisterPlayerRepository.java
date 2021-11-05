package org.springframework.samples.notimeforheroes.registerplayer;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface RegisterPlayerRepository extends CrudRepository<RegisterPlayer, Integer>{

	
	Collection<RegisterPlayer> findAll();
	
}

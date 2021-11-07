package org.springframework.samples.notimeforheroes.registerplayer;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Integer>{

	
	Collection<Player> findAll();
	
}

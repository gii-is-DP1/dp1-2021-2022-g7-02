package org.springframework.samples.notimeforheroes.jugadoresregistrados;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface JugadoresRegistradoRepository extends CrudRepository<JugadoresRegistrados, Integer>{

	
	Collection<JugadoresRegistrados> findAll();
	
}

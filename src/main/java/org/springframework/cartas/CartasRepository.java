package org.springframework.cartas;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;



	
public interface CartasRepository extends CrudRepository<Cartas, Integer>{

		
		Collection<Cartas> findAll();
		
	}

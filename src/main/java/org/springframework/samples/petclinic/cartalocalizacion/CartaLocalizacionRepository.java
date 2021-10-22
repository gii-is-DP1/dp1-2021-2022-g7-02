package org.springframework.samples.petclinic.cartalocalizacion;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

public interface CartaLocalizacionRepository extends CrudRepository<CartaLocalizacion, Integer>{

	public Collection<CartaLocalizacion> findAll() throws DataAccessException;
	
	
}
package org.springframework.samples.petclinic.Disease;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

public interface DiseasesRepository extends CrudRepository<Disease, Integer>{
	
	public Collection<Disease> findAll() throws DataAccessException;
}

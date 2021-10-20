package org.springframework.samples.petclinic.Disease;

import java.util.Collection;

import org.springframework.stereotype.Service;


@Service
public class DiseasesService {
	
	
	private DiseasesRepository diseasesRepository;
	
	
	public Collection<Disease> findDiseases(){
		return diseasesRepository.findAll();
	}

}

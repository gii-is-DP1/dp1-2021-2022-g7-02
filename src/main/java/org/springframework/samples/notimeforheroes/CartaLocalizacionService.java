package org.springframework.samples.notimeforheroes;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaLocalizacionService {
	
	@Autowired
	private CartaLocalizacionRepository diseasesRepository;
	
	@Transactional
	public Collection<CartaLocalizacion> findAll(){
		return diseasesRepository.findAll();
	}

}

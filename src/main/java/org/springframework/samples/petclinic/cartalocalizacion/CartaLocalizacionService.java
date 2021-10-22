package org.springframework.samples.petclinic.cartalocalizacion;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaLocalizacionService {
	
	@Autowired
	private CartaLocalizacionRepository cartaLocalizacionRepository;
	
	@Transactional
	public Collection<CartaLocalizacion> findAll(){
		return cartaLocalizacionRepository.findAll();
	}

}

package org.springframework.samples.notimeforheroes.cartalocalizacion;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

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
	
	public Optional<CartaLocalizacion> findById(int id) {
		return cartaLocalizacionRepository.findById(id);
	}


	public void delete(CartaLocalizacion cartaLoc) {
		cartaLocalizacionRepository.deleteById(cartaLoc.getId());

	}


	public void save(@Valid CartaLocalizacion cartaLoc) {
		cartaLocalizacionRepository.save(cartaLoc);

	}

}
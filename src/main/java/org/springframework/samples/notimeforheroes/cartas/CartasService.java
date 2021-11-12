package org.springframework.samples.notimeforheroes.cartas;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class CartasService {

	@Autowired
	CartasRepository cartasrepo;
	
	
	@Transactional
	public Collection<Cartas> findAll(){
		return cartasrepo.findAll();
	}
	
	
	
	@Transactional
	public Optional<Cartas> findById(Integer id){
		return cartasrepo.findById(id);
	}
	
	@Transactional
	public void deleteCarta(Cartas carta) {
		cartasrepo.deleteById(carta.getId());
		}
	
	@Transactional
	public void createCarta(@Valid Cartas carta) {
		cartasrepo.save(carta);
	}
}

package org.springframework.samples.notimeforheroes.cards;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class CardsService {

	@Autowired
	CardsRepository cartasrepo;
	
	
	@Transactional
	public Collection<Cards> findAll(){
		return cartasrepo.findAll();
	}
	
	
	
	@Transactional
	public Optional<Cards> findById(Integer id){
		return cartasrepo.findById(id);
	}
	
	@Transactional
	public void deleteCarta(Cards carta) {
		cartasrepo.deleteById(carta.getId());
		}
	
	@Transactional
	public void createCarta(@Valid Cards carta) {
		cartasrepo.save(carta);
	}
}

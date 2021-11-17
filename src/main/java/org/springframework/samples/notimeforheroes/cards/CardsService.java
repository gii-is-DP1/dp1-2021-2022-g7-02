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
	CardsRepository cardsrepo;
	
	
	@Transactional
	public Collection<Cards> findAll(){
		return cardsrepo.findAll();
	}
	
	
	
	@Transactional
	public Optional<Cards> findById(Integer id){
		return cardsrepo.findById(id);
	}
	
	@Transactional
	public void deleteCard(Cards card) {
		cardsrepo.deleteById(card.getId());
		}
	
	@Transactional
	public void createCard(@Valid Cards card) {
		cardsrepo.save(card);
	}
}

package org.springframework.samples.notimeforheroes.heroecard;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class HeroeCardsService {

	@Autowired
	HeroeCardsRepository HeroeCardsRepo;
	
	
	@Transactional
	public Collection<HeroeCards> findAll(){
		return HeroeCardsRepo.findAll();
	}
	
	
	@Transactional
	public Optional<HeroeCards> findById(Integer id){
		return HeroeCardsRepo.findById(id);
	}
	
	
	@Transactional
	public void deleteHeroeCard(HeroeCards card) {
		HeroeCardsRepo.deleteById(card.getId());
		}
	
	@Transactional
	public void createHeroeCard(@Valid HeroeCards card) {
		HeroeCardsRepo.save(card);
	}
}

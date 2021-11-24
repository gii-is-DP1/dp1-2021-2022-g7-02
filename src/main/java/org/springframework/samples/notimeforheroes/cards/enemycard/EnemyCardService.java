package org.springframework.samples.notimeforheroes.cards.enemycard;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class EnemyCardService {

	@Autowired
	EnemyCardRepository enemyCardRepo;
	
	
	@Transactional
	public Collection<EnemyCard> findAll(){
		return enemyCardRepo.findAll();
	}
	
	
	@Transactional
	public Optional<EnemyCard> findById(Integer id){
		return enemyCardRepo.findById(id);
	}
	
	
	@Transactional
	public void deleteEnemyCard(EnemyCard card) {
		enemyCardRepo.deleteById(card.getId());
		}
	
	@Transactional
	public void createEnemyCard(@Valid EnemyCard card) {
		enemyCardRepo.save(card);
	}
}

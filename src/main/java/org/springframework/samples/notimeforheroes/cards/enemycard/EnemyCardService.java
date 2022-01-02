package org.springframework.samples.notimeforheroes.cards.enemycard;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.cards.enemycard.gamesEnemies.GamesEnemies;
import org.springframework.samples.notimeforheroes.game.Game;
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

	public Collection<EnemyCard> findOnTableEnemiesByGame(Game game){
		return enemyCardRepo.findOnTableEnemiesByGame(game);
	}

	public Collection<EnemyCard> findOnDeckEnemiesByGame(Game game){
		return enemyCardRepo.findOnDeckEnemiesByGame(game);
	}
	
	public Optional<EnemyCard> findEnemyOfGamesEnemies(GamesEnemies ge){
		return enemyCardRepo.findEnemyOfGamesEnemies(ge);
	}
	
	public Collection<EnemyCard> findAllByIsBoss(Boolean isBoss){
		return enemyCardRepo.findAllByIsBoss(isBoss);
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

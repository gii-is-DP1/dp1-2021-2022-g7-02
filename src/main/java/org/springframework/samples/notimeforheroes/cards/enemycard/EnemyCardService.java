package org.springframework.samples.notimeforheroes.cards.enemycard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.notimeforheroes.cards.enemycard.gamesEnemies.GamesEnemies;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.stereotype.Service;

@Service
public class EnemyCardService {

	@Autowired
	EnemyCardRepository enemyCardRepo;
	
	
	@Transactional
	public Collection<EnemyCard> findAllPage(Integer pageNo, Integer pageSize){
		Pageable pagin = PageRequest.of(pageNo, pageSize);
		Page<EnemyCard> pageResult = enemyCardRepo.findAll(pagin);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		} else {
			return new ArrayList<EnemyCard>();
		}
	}
	
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
	public Integer countOnTableEnemiesByGame(Game game){
		return enemyCardRepo.countOnTableEnemiesByGame(game);
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

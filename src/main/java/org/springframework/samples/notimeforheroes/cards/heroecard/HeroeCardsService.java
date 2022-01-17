package org.springframework.samples.notimeforheroes.cards.heroecard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyCard;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.stereotype.Service;

@Service
public class HeroeCardsService {

	@Autowired
	HeroeCardsRepository heroeCardsRepo;
	
	
	@Transactional
	public Collection<HeroeCard> findAllPage(Integer pageNo, Integer pageSize){
		Pageable pagin = PageRequest.of(pageNo, pageSize);
		Page<HeroeCard> pageResult = heroeCardsRepo.findAll(pagin);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		} else {
			return new ArrayList<HeroeCard>();
		}
	}
	
	@Transactional
	public Collection<HeroeCard> findAll(){
		return heroeCardsRepo.findAll();
	}
	
	
	@Transactional
	public Optional<HeroeCard> findById(Integer id){
		return heroeCardsRepo.findById(id);
	}
	
	
	@Transactional
	public void deleteHeroeCard(HeroeCard card) {
		heroeCardsRepo.deleteById(card.getId());
		}
	
	@Transactional
	public void createHeroeCard(@Valid HeroeCard card) {
		heroeCardsRepo.save(card);
	}


    public HeroeCard findByName(String heroe) {
        return heroeCardsRepo.findByName(heroe).get();
    }
    
    public Collection<HeroeCard> findByColor(String color) {
        return heroeCardsRepo.findByColor(color);
    }

	public Collection<HeroeCard> findByColorAndGame(String color, Game game){
		return heroeCardsRepo.findByColorAndGame(color, game);
	}

	public Collection<HeroeCard> findHeroesOfGame(Game game){
		return heroeCardsRepo.findHeroesOfGame(game);
	}
}

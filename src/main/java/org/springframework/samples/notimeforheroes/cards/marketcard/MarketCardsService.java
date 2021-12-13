package org.springframework.samples.notimeforheroes.cards.marketcard;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.cards.marketcard.gamesMarket.GameMarketService;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.stereotype.Service;

@Service
public class MarketCardsService {

	@Autowired
	MarketCardsRepository marketRepository;

	@Autowired
	GameMarketService gameMarketService;
	
	@Transactional
	public Collection<MarketCard> findAll(){
		return marketRepository.findAll();
	}
	
	@Transactional
	public Optional<MarketCard> findById(Integer id){
		return marketRepository.findById(id);
	}

	public Collection<MarketCard> findByGame(Game game){
		return marketRepository.findAllByGame(game);
	}
	
	public Collection<MarketCard> findByGameOnDeck(Game game){
		
		return marketRepository.findAllByGameAndOnDeck(game);
	}

	public Collection<MarketCard> findAllByGameAndOnTable(Game game){
		
		return marketRepository.findAllByGameAndOnTable(game);
	}
	
	
	@Transactional
	public void saveMarketCard(@Valid MarketCard market) {
		marketRepository.save(market);
	}
	
	@Transactional
	public void deleteMarketCard(MarketCard market) {
		marketRepository.delete(market);
	}
	
}

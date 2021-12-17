package org.springframework.samples.notimeforheroes.cards.marketcard.gamesMarket;


import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCardsService;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.stereotype.Service;

@Service
public class GameMarketService {

	@Autowired
	GameMarketRepository gameMarketRepository;

	@Autowired
	GameMarketService gameMarketService;

	@Autowired
	MarketCardsService marketService;

	public Collection<GameMarket> findAll(){
		return gameMarketRepository.findAll();
	}

	public Collection<GameMarket> findAllInGame(Game game){
		return gameMarketRepository.findAllInGame(game);
	}
	
	@Transactional
	public void createGameMarket(@Valid GameMarket gameMarket) {
		gameMarketRepository.save(gameMarket);
	}
}



package org.springframework.samples.notimeforheroes.gamesMarket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.marketcard.MarketCard;
import org.springframework.samples.notimeforheroes.marketcard.MarketCardsService;
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

	public Collection<MarketCard> findByGame(Integer gameId){
		Collection<MarketCard> marktetGame = new ArrayList<MarketCard>();
		List<Integer> ids=gameMarketRepository.findByGame(gameId).get();
		for(int i = 0; i < ids.size(); i++) {
			marktetGame.add(marketService.findById(ids.get(i)).get());
		}
		return marktetGame;
	}

	
	
	
	
}



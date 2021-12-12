package org.springframework.samples.notimeforheroes.gamesMarket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.marketcard.MarketCard;
import org.springframework.samples.notimeforheroes.marketcard.MarketCardsService;
import org.springframework.samples.notimeforheroes.marketcard.MarketState;
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
		Collection<MarketCard> marketGame = new ArrayList<MarketCard>();
		List<Integer> ids=gameMarketRepository.findByGame(gameId).get();
		for(int i = 0; i < ids.size(); i++) {
			marketGame.add(marketService.findById(ids.get(i)).get());
		}
		return marketGame;
	}
	
	public Collection<MarketCard> findByGameOnDesck(Integer gameId){
		Collection<MarketCard> marketGame = new ArrayList<MarketCard>();
		List<Integer> ids=gameMarketRepository.findByGame(gameId).get();
		for(int i = 0; i < ids.size(); i++) {
			MarketCard card=marketService.findById(ids.get(i)).get();
			if(card.getMarketState()==MarketState.ONDESCK) {
				marketGame.add(card);
			}
		}
		return marketGame;
	}
	
	
	public Collection<MarketCard> findByGameOnDesckOntable(Integer gameId){
		Collection<MarketCard> marketGame = findByGameOnDesck(gameId);
		Collection<MarketCard> res= new ArrayList<MarketCard>();
		List<MarketCard> listaMarket=new ArrayList<MarketCard>(marketGame);
		
		for(int i=0; i<5; i++) {
			res.add(listaMarket.get(i));
		}
		return res;
	}
	
	@Transactional
	public void createMarketService(@Valid GameMarket gameMarket) {
		gameMarketRepository.save(gameMarket);
	}
}



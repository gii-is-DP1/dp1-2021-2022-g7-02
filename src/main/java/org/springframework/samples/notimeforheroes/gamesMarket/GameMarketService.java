package org.springframework.samples.notimeforheroes.gamesMarket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


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

	public Collection<MarketCard> findByGame(Integer id){
		Collection<MarketCard> cartasDelJuego = new ArrayList<MarketCard>();
		Integer total = gameMarketRepository.findNumbreOfCardByGame(id).size();
		for(int i = 0; i < total; i++) {
			Integer idCarta = gameMarketRepository.findByGame(id).get().getId();
			cartasDelJuego.add(marketService.findById(idCarta).get());
		}
		return cartasDelJuego;
	}

	
	
	
	
}



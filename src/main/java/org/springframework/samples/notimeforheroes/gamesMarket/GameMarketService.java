package org.springframework.samples.notimeforheroes.gamesMarket;

import java.util.Collection;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class GameMarketService {

	@Autowired
	GameMarketRepository gameMarketRepository;

	@Autowired
	GameMarketService gameMarketService;

	

	public Collection<GameMarket> findAll(){
		return gameMarketRepository.findAll();
	}

	public Optional<GameMarket> findByGame(Integer id){
		return gameMarketRepository.findByGame(id);
	}

	
	
	
	
}



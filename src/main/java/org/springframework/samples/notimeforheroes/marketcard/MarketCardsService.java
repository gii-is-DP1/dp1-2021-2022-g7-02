package org.springframework.samples.notimeforheroes.marketcard;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketCardsService {

	@Autowired
	MarketCardsRepository MarketRepository;
	
	@Transactional
	public Collection<MarketCard> findAll(){
		return MarketRepository.findAll();
	}
	
	@Transactional
	public Optional<MarketCard> findById(Integer id){
		return MarketRepository.findById(id);
	}
	
	@Transactional
	public void saveMarketCard(@Valid MarketCard market) {
		MarketRepository.save(market);
	}
	
	@Transactional
	public void deleteMarketCard(MarketCard market) {
		MarketRepository.delete(market);
	}
	
}

package org.springframework.samples.notimeforheroes.marketcard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.notimeforheroes.marketcard.MarketCard;
import org.springframework.samples.notimeforheroes.marketcard.MarketCardsService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class MarketCardServiceTest {

	@Autowired 
	MarketCardsService MarketService;
	
	@Test
	public void TestNoMarketCard() {
		Collection<MarketCard> market = MarketService.findAll();
		for(MarketCard c: market) {
			MarketService.deleteMarketCard(c);
		}	
		assertThat(MarketService.findAll().isEmpty()).isTrue();
	}
	
	@Test
	public void TestOneMarketCard() {
		Collection<MarketCard> market = MarketService.findAll();
		for(MarketCard c : market) {
			MarketService.deleteMarketCard(c);
		}		
		MarketCard MarketCard = new MarketCard();
		MarketCard.setName("Pocion curativa");
		MarketCard.setUrl("https:");
		MarketCard.setCost(8);
		MarketCard.setDescription("description");
		MarketService.saveMarketCard(MarketCard);
		assertThat(MarketService.findAll().size()).isEqualTo(1);

	}
	
	@Test
	public void TestMoreThanOneMarketCard() {
		
		Collection<MarketCard> MarketCards = MarketService.findAll();
		for(MarketCard c : MarketCards) {
			MarketService.deleteMarketCard(c);
		}		
		MarketCard MarketCard = new MarketCard();
		MarketCard.setName("Pocion curativa");
		MarketCard.setUrl("https:");
		MarketCard.setCost(8);
		MarketCard.setDescription("description");
		MarketService.saveMarketCard(MarketCard);
		
		MarketCard MarketCard2 = new MarketCard();
		MarketCard2.setName("Pocion curativa");
		MarketCard2.setUrl("https:");
		MarketCard2.setCost(8);
		MarketCard2.setDescription("description");
		MarketService.saveMarketCard(MarketCard2);
		
		assertThat(MarketService.findAll().size()).isGreaterThan(1);

	}
	
	@Test 
	public void TestEditMarketCard() {
		MarketCard MarketCard = MarketService.findById(1).get();
		String oldName = MarketCard.getName();
		
		String newName = oldName + " sky";
		MarketCard.setName(newName);
		MarketService.saveMarketCard(MarketCard);
		
		assertThat(MarketCard.getName()).isEqualTo(newName);

	}
	
	@Test
	public void TestDeleteMarketCard() {	
		
		MarketCard MarketCard = new MarketCard();
		MarketCard.setName("Pocion curativa");
		MarketCard.setUrl("https:");
		MarketCard.setCost(8);
		MarketCard.setDescription("description");
		MarketService.saveMarketCard(MarketCard);
		
		MarketCard market = MarketService.findById(MarketCard.getId()).get();
		assertTrue(MarketService.findAll().contains(market));
		
		MarketService.deleteMarketCard(MarketCard);
		assertFalse(MarketService.findAll().contains(market));

	}
	
	
	@Test
	public void TestNewMarketCard() {	
		Integer market = MarketService.findAll().size();
		
		MarketCard MarketCard = new MarketCard();
		MarketCard.setName("Pocion curativa");
		MarketCard.setUrl("https:");
		MarketCard.setCost(8);
		MarketCard.setDescription("description");
		MarketService.saveMarketCard(MarketCard);
		
		Integer newMarket = MarketService.findAll().size();
		assertTrue(market != newMarket);
	}
	
}

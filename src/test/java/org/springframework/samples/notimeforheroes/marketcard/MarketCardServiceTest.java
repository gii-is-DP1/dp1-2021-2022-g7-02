package org.springframework.samples.notimeforheroes.marketcard;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCard;
import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCardsService;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class MarketCardServiceTest {

	@Autowired 
	MarketCardsService MarketService;
	@Autowired
	UserService userService;
	
	@Test
	public void TestFindAllMarketCard() {
		Integer AllMarketCards = MarketService.findAll().size();
		MarketCard marketCard = NewMarketCard("Name", "url", 1, "description");
		MarketService.saveMarketCard(marketCard);
		
		Integer NewAllMarketCard = MarketService.findAll().size();
		
		assertTrue(NewAllMarketCard == AllMarketCards + 1);
	}
	
	@Test
	public void TestFindByIdMarketCard() {
		MarketCard marketCard = NewMarketCard("Name", "url", 1, "description");
		MarketService.saveMarketCard(marketCard);
		assertTrue(MarketService.findById(marketCard.getId()).orElse(null).equals(marketCard));
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
	
	private MarketCard NewMarketCard(String name, String url, Integer cost, String description) {
		MarketCard marketCard = new MarketCard();
		marketCard.setCost(cost);
		marketCard.setDescription(description);
		marketCard.setName(name);
		marketCard.setUrl(url);
		return marketCard;
	}
}

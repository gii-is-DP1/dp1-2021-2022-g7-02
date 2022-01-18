package org.springframework.samples.notimeforheroes.marketcard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCard;
import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCardsService;
import org.springframework.samples.notimeforheroes.cards.marketcard.gamesMarket.GameMarket;
import org.springframework.samples.notimeforheroes.cards.marketcard.gamesMarket.GameMarketService;
import org.springframework.samples.notimeforheroes.cards.scenecard.SceneCard;
import org.springframework.samples.notimeforheroes.game.Game;

import org.springframework.samples.notimeforheroes.game.GameService;
import org.springframework.samples.notimeforheroes.game.GameServiceTests;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class MarketCardServiceTest {

	@Autowired
	MarketCardsService marketService;
	@Autowired
	UserService userService;
	@Autowired
	GameService gameService;

	@Autowired
	GameMarketService gameMarketService;

	@Test
	void testFindAllPage() {
		Integer MarketCard = marketService.findAllPage(0, 2).size();
		assertTrue(MarketCard == 2);
	}

	@Test
	void testFindAllMarketCard() {
		Integer AllMarketCards = marketService.findAll().size();
		MarketCard marketCard = NewMarketCard("Name", "url", 1, "description");
		marketService.saveMarketCard(marketCard);

		Integer NewAllMarketCard = marketService.findAll().size();

		//
		assertTrue(NewAllMarketCard == AllMarketCards + 1);
	}

	@Test
	void testFindByIdMarketCard() {
		MarketCard marketCard = NewMarketCard("Name", "url", 1, "description");
		marketService.saveMarketCard(marketCard);

		//
		assertTrue(marketService.findById(marketCard.getId()).orElse(null).equals(marketCard));
	}

	@Test
	void testDeleteMarketCard() {

		MarketCard MarketCard = new MarketCard();
		MarketCard.setName("Pocion curativa");
		MarketCard.setUrl("https:");
		MarketCard.setCost(8);
		MarketCard.setDescription("description");
		marketService.saveMarketCard(MarketCard);

		MarketCard market = marketService.findById(MarketCard.getId()).get();

		//
		assertTrue(marketService.findAll().contains(market));

		marketService.deleteMarketCard(MarketCard);

		//
		assertFalse(marketService.findAll().contains(market));

	}

	@Test
	void testNewMarketCard() {
		Integer market = marketService.findAll().size();

		MarketCard MarketCard = new MarketCard();
		MarketCard.setName("Pocion curativa");
		MarketCard.setUrl("https:");
		MarketCard.setCost(8);
		MarketCard.setDescription("description");
		marketService.saveMarketCard(MarketCard);

		Integer newMarket = marketService.findAll().size();

		//
		assertTrue(market != newMarket);
	}

	@Test
	void testFindByGame() {
		Collection<MarketCard> marketCards = marketService.findAll();
		Game g1 = gameConstructor(1, LocalDate.now(), 10, false, (List<MarketCard>) marketCards);
		gameService.createGame(g1);
		Collection<MarketCard> cards = marketService.findByGame(g1);

		//
		assertTrue(marketCards.containsAll(cards));

	}

	@Test
	void testFindByGameOnDeck() {
		Collection<MarketCard> marketCards = marketService.findAll();
		Game g1 = gameConstructor(1, LocalDate.now(), 10, false, (List<MarketCard>) marketCards);
		gameService.createGame(g1);

		Collection<MarketCard> cards = marketService.findByGameOnDeck(g1);

		//
		assertTrue(cards.size() <= 5);
	}

	@Test
	void testFindAllByGameAndOnTable() {
		Collection<MarketCard> marketCards = marketService.findAll();
		Game g1 = gameConstructor(1, LocalDate.now(), 10, false, (List<MarketCard>) marketCards);
		gameService.createGame(g1);
		Collection<MarketCard> cards = marketService.findAllByGameAndOnTable(g1);

		// Comprobamos que coge 5 cartas para ponerlas a la venta
		assertTrue(cards.size() == 5);

	}

	private MarketCard NewMarketCard(String name, String url, Integer cost, String description) {
		MarketCard marketCard = new MarketCard();
		marketCard.setCost(cost);
		marketCard.setDescription(description);
		marketCard.setName(name);
		marketCard.setUrl(url);
		return marketCard;
	}

	public Game gameConstructor(int creatorId, LocalDate date, int duration, boolean isInProgress,
			List<MarketCard> items) {
		Game g = new Game();
		g.setCreator(userService.findById(creatorId).get());
		g.setDate(date);
		g.setDuration(duration);
		g.setIsInProgress(isInProgress);
		g.setItems(items);

		return g;
	}
}

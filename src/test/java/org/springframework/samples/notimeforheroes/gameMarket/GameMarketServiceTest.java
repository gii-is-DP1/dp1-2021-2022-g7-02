package org.springframework.samples.notimeforheroes.gameMarket;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.notimeforheroes.cards.marketcard.ItemState;
import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCard;
import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCardsService;

import org.springframework.samples.notimeforheroes.cards.marketcard.gamesMarket.GameMarket;
import org.springframework.samples.notimeforheroes.cards.marketcard.gamesMarket.GameMarketService;
import org.springframework.samples.notimeforheroes.game.Game;

import org.springframework.samples.notimeforheroes.game.GameService;
import org.springframework.samples.notimeforheroes.game.GameServiceTests;
import org.springframework.samples.notimeforheroes.marketcard.MarketCardServiceTest;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class GameMarketServiceTest {

    @Autowired
    GameMarketService gameMarketService;

    @Autowired
    GameService gameService;

    @Autowired
    UserService userService;

    @Autowired
    MarketCardsService marketService;

    @Test
    void testFindAllGameMarket() {
        Game g1 = gameConstructor(1, LocalDate.now(), 10, false);
        gameService.createGame(g1);
        Integer AllGameMarketCards = gameMarketService.findAll().size();
        GameMarket gameMarket = gameMarketService.findOneItemInGame(g1, 1);
        gameMarketService.createGameMarket(gameMarket);

        Integer newAllGameMarketCards = gameMarketService.findAll().size();

        assertTrue(AllGameMarketCards == newAllGameMarketCards);
    }

    @Test
    void testFindOneItemInGame() {
        Game g1 = gameConstructor(1, LocalDate.now(), 10, false);
        gameService.createGame(g1);

        List<MarketCard> cards = new ArrayList<>();
        MarketCard marketCard = MarketCardServiceTest.NewMarketCard("name", "url", 4, "description");
        marketService.saveMarketCard(marketCard);
        cards.add(marketCard);
        g1.setItems(cards);
        gameService.updateGame(g1);

        GameMarket gameMarket = gameMarketService.findOneItemInGame(g1, marketCard.getId());

        Collection<GameMarket> gameMarket1 = gameMarketService.findAllInGame(g1);

        assertTrue(gameMarket1.contains(gameMarket));

    }

    @Test
    void testFindAllInGame() {
        Game g1 = gameConstructor(1, LocalDate.now(), 10, false);
        gameService.createGame(g1);
        Collection<GameMarket> gameMarketCards = gameMarketService.findAllInGame(g1);
        Integer gameMarketCardSize = gameMarketCards.size();

        assertTrue(gameMarketCardSize <= 15);
    }

    /*
     * @Test
     * void testFindAllMarketCard() {
     * Integer AllMarketCards = marketService.findAll().size();
     * MarketCard marketCard = NewMarketCard("Name", "url", 1, "description");
     * marketService.saveMarketCard(marketCard);
     * 
     * Integer NewAllMarketCard = marketService.findAll().size();
     * 
     * //
     * assertTrue(NewAllMarketCard == AllMarketCards + 1);
     * }
     */

    /**
     * private MarketCard NewMarketCard(String name, String url, Integer cost,
     * String description) {
     * MarketCard marketCard = new MarketCard();
     * marketCard.setCost(cost);
     * marketCard.setDescription(description);
     * marketCard.setName(name);
     * marketCard.setUrl(url);
     * return marketCard;
     * }
     */

    public GameMarket newGameMarket(ItemState itemState) {
        GameMarket gameMarket = new GameMarket();
        gameMarket.setItemState(itemState);
        return gameMarket;
    }

    public Game gameConstructor(int creatorId, LocalDate date, int duration, boolean isInProgress) {
        Game g = new Game();
        g.setCreator(userService.findById(creatorId).get());
        g.setDate(date);
        g.setDuration(duration);
        g.setIsInProgress(isInProgress);

        return g;
    }
}

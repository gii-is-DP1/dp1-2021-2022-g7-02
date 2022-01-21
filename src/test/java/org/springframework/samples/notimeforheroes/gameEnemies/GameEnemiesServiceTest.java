package org.springframework.samples.notimeforheroes.gameEnemies;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyCard;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyCardService;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyState;
import org.springframework.samples.notimeforheroes.cards.enemycard.gamesEnemies.GamesEnemies;
import org.springframework.samples.notimeforheroes.cards.enemycard.gamesEnemies.GamesEnemiesService;
import org.springframework.samples.notimeforheroes.cards.marketcard.ItemState;
import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCard;
import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCardsService;
import org.springframework.samples.notimeforheroes.cards.marketcard.gamesMarket.GameMarket;
import org.springframework.samples.notimeforheroes.cards.marketcard.gamesMarket.GameMarketService;
import org.springframework.samples.notimeforheroes.enemycard.EnemyCardServiceTest;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.game.GameService;
import org.springframework.samples.notimeforheroes.marketcard.MarketCardServiceTest;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class GameEnemiesServiceTest {
	
		public static final Integer NUMBER_ENEMIES = 15;
		public static final Integer NUMBER_BOSSES_OUT_OF_GAME = 2;

	   	@Autowired
	    GamesEnemiesService gameEnemyService;

	    @Autowired
	    EnemyCardService enemyService;

	    @Autowired
	    UserService userService;
	    
	    @Autowired
	    GameService gameService;

	    @Autowired
	    MarketCardsService marketService;

	    @Test
	    void testFindAllGameEnemy() {
	        Game g1 = gameConstructor(1, LocalDate.now(), 10, false);
	        gameService.createGame(g1);
	        Integer AllGameEnemies = gameEnemyService.findAll().size();
	        List<EnemyCard> enemies= (List<EnemyCard>) enemyService.findAll();
	        g1.setEnemies(enemies);
	        gameService.createGame(g1);
	        Integer newAllGameEmemies = gameEnemyService.findAll().size();
	        assertTrue(newAllGameEmemies==AllGameEnemies);
	    }

	    @Test
	    void testFindAllInGame() {
	        Game g1 = gameConstructor(1, LocalDate.now(), 10, false);
	        gameService.createGame(g1);
	        Collection<GamesEnemies> gameEnemies = gameEnemyService.findAllInGame(g1);
	        Integer numberEnemiesGame = gameEnemies.size();
	        Integer numberTotalEnemiesByGame= NUMBER_ENEMIES-NUMBER_BOSSES_OUT_OF_GAME;

	        assertTrue(numberEnemiesGame == numberTotalEnemiesByGame);
	    }
	    
	    @Test
	    void testFindByGameAndEnemy() {
	        Game g1 = gameConstructor(1, LocalDate.now(), 10, false);
	        gameService.createGame(g1);

	        List<EnemyCard> enemies = (List<EnemyCard>) enemyService.findAll();
	        EnemyCard enemy=enemies.get(1);

	        GamesEnemies enemy1= gameEnemyService.findByGameAndEnemy(g1, enemy).get();

	        Collection<GamesEnemies> gamesEnemies = gameEnemyService.findAllInGame(g1);

	        assertTrue(gamesEnemies.contains(enemy1));

	    }

	    public GamesEnemies Enemy(EnemyState state) {
	        GamesEnemies gameEnemy = new GamesEnemies();
	        gameEnemy.setEnemyState(state);
	        return gameEnemy;
		}

	    public Game gameConstructor(int creatorId, LocalDate date, int duration, boolean isInProgress) {
	        Game g = new Game();
	        g.setCreator(userService.findById(creatorId).get());
	        g.setDate(date);
	        g.setDuration(duration);
	        g.setIsInProgress(isInProgress);

	        return g;
	    }

		public static User userConstructor(String lastname, String name, String username, String email, String password) {
			User u = new User();
			u.setEmail(email);
			u.setLastname(lastname);
			u.setPassword(password);
			u.setUsername(username);
			u.setName(name);
	
			return u;
		}
    
}
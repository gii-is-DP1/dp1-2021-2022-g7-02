package org.springframework.samples.notimeforheroes.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class GameServiceTests {
	
	
	@Autowired
	private GameService gameService;
	@Autowired
	private UserService userService;


	@Test
	void testFindAll(){
		Game g1 = gameConstructor(1, LocalDate.now(), 10, false);
		Game g2 = gameConstructor(1, LocalDate.now(), 20, true);
		Game g3 = gameConstructor(1, LocalDate.now(), 30, false);
		Game g4 = gameConstructor(1, LocalDate.now(), 40, true);
		
		Collection<Game> currentGames = gameService.findAll();
		
		Collection<Game> gamesToAdd = List.of(g1, g2, g3, g4);
		gamesToAdd.stream().forEach(gameToAdd -> 
			{
				gameService.createGame(gameToAdd);
				currentGames.add(gameToAdd);
			}
		);
		
		Collection<Game> gamesAtDatabase = gameService.findAll();
		
		assertTrue(currentGames.equals(gamesAtDatabase));
	}

	@Test
	void testFindById(){

		Game g1 = gameConstructor(1, LocalDate.now(), 10, false);
		Game g2 = gameConstructor(1, LocalDate.now(), 20, true);
		
		gameService.createGame(g1);
		gameService.createGame(g2);
		
		assertTrue(gameService.findById(g1.getId()).orElse(null).equals(g1));
	}
	
	@Test
	void testNewGame() {
		Game game = new Game();
		game.setCreator(userService.findById(1).orElse(null));
		game.setDate(LocalDate.now());
		game.setDuration(4000);
		game.setIsInProgress(false);
		gameService.createGame(game);
		Collection<Game> gamesInDatabase = gameService.findAll();
		
		assertTrue(gamesInDatabase.contains(game));
	}
	
	@Test
	void testDeleteGame() {
		Game game = new Game();
		game.setCreator(userService.findById(1).orElse(null));
		game.setDate(LocalDate.now());
		game.setDuration(4000);
		game.setIsInProgress(false);
		gameService.createGame(game);
		Collection<Game> gamesInDatabase = gameService.findAll();
		assertTrue(gamesInDatabase.contains(game));
		
		gameService.deleteGame(game);
		gamesInDatabase = gameService.findAll();
		assertFalse(gamesInDatabase.contains(game));
	}
	
	@Test
	void testFindAllEnded() {
		
		Game g1 = gameConstructor(1, LocalDate.now(), 10, false);
		Game g2 = gameConstructor(1, LocalDate.now(), 20, true);
		Game g3 = gameConstructor(1, LocalDate.now(), 30, false);
		Game g4 = gameConstructor(1, LocalDate.now(), 40, true);
		
		gameService.createGame(g1);
		gameService.createGame(g2);
		gameService.createGame(g3);
		gameService.createGame(g4);
		
		
		Collection<Game> endedGames = gameService.findAllEnded();
		boolean res = endedGames.contains(g1) && endedGames.contains(g3) &&
		!endedGames.contains(g2) && !endedGames.contains(g4);
		assertTrue(res);
	}
	
	
	
	
	private Game gameConstructor(int creatorId, LocalDate date, int duration, boolean isInProgress) {
		Game g = new Game();
		g.setCreator(userService.findById(creatorId).get());
		g.setDate(date);
		g.setDuration(duration);
		g.setIsInProgress(isInProgress);
		
		return g;
	}
}

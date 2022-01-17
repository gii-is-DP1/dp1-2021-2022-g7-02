package org.springframework.samples.notimeforheroes.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import javax.validation.constraints.Null;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.notimeforheroes.cards.enemycard.gamesEnemies.GamesEnemies;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUserService;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class GameServiceTests{
	
	
	@Autowired
	private GameService gameService;
	@Autowired
	private UserService userService;
	@Autowired
	private GameUserService gameUserService;


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
		
		//
		assertTrue(currentGames.equals(gamesAtDatabase));
	}

	@Test
	void testFindById(){

		Game g1 = gameConstructor(1, LocalDate.now(), 10, false);
		Game g2 = gameConstructor(1, LocalDate.now(), 20, true);
		
		gameService.createGame(g1);
		gameService.createGame(g2);

		//		
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
		
		//		
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

		//
		assertTrue(gamesInDatabase.contains(game));
		
		gameService.deleteGame(game);
		gamesInDatabase = gameService.findAll();

		//
		assertFalse(gamesInDatabase.contains(game));
	}

	@Test
	void testFindByWinner(){
		Game g1 = gameService.findById(1).orElse(null);
		Game g2 = gameService.findById(2).orElse(null);
		g1.setWinner(userService.findById(2).orElse(null));
		g2.setWinner(userService.findById(2).orElse(null));
		gameService.updateGame(g1);
		gameService.updateGame(g2);

		Collection<Game> gamesWinner = gameService.findByWinner(userService.findById(2).orElse(null));

		//
		assertTrue(gamesWinner.contains(g1) && gamesWinner.contains(g2));

		g1.setWinner(null);
		gameService.updateGame(g1);

		Collection<Game> gamesWinner2 = gameService.findByWinner(userService.findById(2).orElse(null));

		//
		assertFalse(gamesWinner2.contains(g1) && gamesWinner2.contains(g2));
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
		boolean res = endedGames.contains(g1) && endedGames.contains(g3) && !endedGames.contains(g2) && !endedGames.contains(g4);

		//
		assertTrue(res);

		g1.setIsInProgress(true);
		gameService.updateGame(g1);

		Collection<Game> endedGames2 = gameService.findAllEnded();
		boolean res2 = endedGames2.contains(g1) && endedGames2.contains(g3) && !endedGames2.contains(g2) && !endedGames2.contains(g4);

		//
		assertFalse(res2);
	}

	@Test
	void testFindByIsInProgress() {
		
		Game g1 = gameConstructor(1, LocalDate.now(), 10, false);
		Game g2 = gameConstructor(1, LocalDate.now(), 20, true);
		
		gameService.createGame(g1);
		gameService.createGame(g2);
		
		Collection<Game> progressGames = gameService.findAllByIsInProgress();
		boolean res = progressGames.contains(g2) && !progressGames.contains(g1);

		//
		assertTrue(res);

		g1.setIsInProgress(true);
		gameService.updateGame(g1);

		Collection<Game> progressGames2 = gameService.findAllByIsInProgress();
		boolean res2 = progressGames2.contains(g2) && !progressGames2.contains(g1);

		//
		assertFalse(res2);
	}

	@Test
	void testFindByUser() {
		
		Game g1 = gameConstructor(1, LocalDate.now(), 10, false);
		Game g2 = gameConstructor(2, LocalDate.now(), 10, false);

		User gamer1 = userService.findById(1).orElse(null);
		User gamer2 = userService.findById(2).orElse(null);

		List<User> users = new ArrayList<User>();
		users.add(gamer1);
		users.add(gamer2);

		g1.setUsers(users);
		g2.setUsers(users);

		gameService.createGame(g1);	
		gameService.createGame(g2);
		
		Collection<Game> games = gameService.findByUser(userService.findById(1).orElse(null));

		//
		assertTrue(games.contains(g1) && games.contains(g2));

		users.remove(1);
		g1.setUsers(users);
		gameService.updateGame(g1);
		Collection<Game> games2 = gameService.findByUser(userService.findById(1).orElse(null));

		//
		assertFalse(!games2.contains(g1) && games2.contains(g2));
	}

	@Test
	void testFindByIsInProgressAndUser() {
		
		Game g1 = gameConstructor(1, LocalDate.now(), 10, false);

		User gamer1 = userService.findById(1).orElse(null);
		User gamer2 = userService.findById(2).orElse(null);

		List<User> users = new ArrayList<User>();
		users.add(gamer1);
		users.add(gamer2);

		g1.setUsers(users);
		
		gameService.createGame(g1);		
		
		Optional<Game> progressGame1 = gameService.findGameInProgressByUser(userService.findById(1).orElse(null));
		Optional<Game> progressGame2 = gameService.findGameInProgressByUser(userService.findById(1).orElse(null));

		//
		assertTrue(!progressGame1.equals(null) && !progressGame2.equals(null));

		users.remove(0);
		g1.setUsers(users);
		gameService.updateGame(g1);

		Optional<Game> progressGame11 = gameService.findGameInProgressByUser(userService.findById(1).orElse(null));
		Optional<Game> progressGame22 = gameService.findGameInProgressByUser(userService.findById(1).orElse(null));

		//
		assertFalse(progressGame11.equals(null) && !progressGame22.equals(null));
	}
	
	@Test
	void testAddPlayerToGame(){

		Game g1 = gameConstructor(1, LocalDate.now(), 0, false);
		User gamer1 = userService.findById(1).orElse(null);
		User gamer2 = userService.findById(2).orElse(null);

		List<User> users = new ArrayList<User>();
		users.add(gamer1);
		users.add(gamer2);

		gameService.createGame(g1);	

		User gamer3 = userService.findById(1).orElse(null);

		assertFalse(!g1.getUsers().contains(gamer3));

		users.add(gamer3);

		gameService.updateGame(g1);

		assertTrue(g1.getUsers().contains(gamer3));
	}

	@Test
	void testFindRanking(){

		Game g1 = gameService.findById(1).orElse(null);
		g1.setWinner(userService.findById(2).orElse(null));

		Game g2 = gameService.findById(2).orElse(null);
		g2.setWinner(userService.findById(1).orElse(null));

		Game g3 = gameService.findById(3).orElse(null);
		g3.setWinner(userService.findById(1).orElse(null));

		List<Tuple> ranking = gameService.findRanking();
	
		boolean res = true;
		int cont = 0;
		for (Tuple tuple : ranking) {
			if(cont == 0){
				res = tuple.get(0).equals(userService.findById(1).orElse(null).getUsername()) && tuple.get(1).toString().equals("2");
				cont++;
			}
			if(cont == 1){
				res = tuple.get(0).equals(userService.findById(2).orElse(null).getUsername()) && tuple.get(1).toString().equals("1");
			}
		}
		assertTrue(res);
	}
	
	@Test
	void testFindAllByCreator(){
		Game g1 = gameConstructor(1, LocalDate.now(), 10, false);
		gameService.createGame(g1);
		User creator = userService.findById(1).orElse(null);
		Collection<Game> gamesCreatedBy = gameService.findAllByCreator(creator);

		boolean res = gamesCreatedBy.contains(g1);

		assertTrue(res);
	}

	@Test
	void testFindByJoinCode(){
		Game g1 = gameConstructor(1, LocalDate.now(), 10, false);
		g1.setJoinCode("abcde");
		gameService.createGame(g1);
		
		Optional<Game> gameByCode = gameService.findByJoinCode("abcde");

		assertTrue(!gameByCode.equals(null));
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

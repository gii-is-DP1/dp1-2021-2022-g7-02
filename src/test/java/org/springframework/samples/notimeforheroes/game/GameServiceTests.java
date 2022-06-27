package org.springframework.samples.notimeforheroes.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import javax.persistence.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.notimeforheroes.cards.enemycard.gamesEnemies.GamesEnemies;
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCard;
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCardsService;
import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCard;
import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCardsService;
import org.springframework.samples.notimeforheroes.game.exceptions.DontHaveEnoughGoldToBuyException;
import org.springframework.samples.notimeforheroes.game.exceptions.GameFullException;
import org.springframework.samples.notimeforheroes.game.exceptions.HeroeNotAvailableException;
import org.springframework.samples.notimeforheroes.game.exceptions.ItemNotSelectedException;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUser;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUserService;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.samples.notimeforheroes.user.UserGlory;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.samples.notimeforheroes.user.UsersServiceTests;
import org.springframework.samples.notimeforheroes.user.exceptions.DuplicatedUserEmailException;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class GameServiceTests {

	@Autowired
	private GameService gameService;
	@Autowired
	private UserService userService;
	@Autowired
	private GameUserService gameUserService;
	@Autowired
	private HeroeCardsService heroeCardService;
	@Autowired
	private MarketCardsService marketCardService;
	
	User user1 = UsersServiceTests.userConstructor("Antonio", "Paco", "pacoan", "pa@gmail.com", "1234");
	User user2 = UsersServiceTests.userConstructor("Jose", "Manuel", "manujo", "manu@gmail.com", "123234");
	User user3 = UsersServiceTests.userConstructor("Manuel", "Jose", "chema", "chema@gmail.com", "13234");

	@Test
	void testFindAll() {
		Game g1 = gameConstructor(1, LocalDate.now(), 10, false);
		Game g2 = gameConstructor(1, LocalDate.now(), 20, true);
		Game g3 = gameConstructor(1, LocalDate.now(), 30, false);
		Game g4 = gameConstructor(1, LocalDate.now(), 40, true);

		Collection<Game> currentGames = gameService.findAll();

		Collection<Game> gamesToAdd = List.of(g1, g2, g3, g4);
		gamesToAdd.stream().forEach(gameToAdd -> {
			gameService.createGame(gameToAdd);
			currentGames.add(gameToAdd);
		});

		Collection<Game> gamesAtDatabase = gameService.findAll();

		//
		assertTrue(currentGames.equals(gamesAtDatabase));
	}

	@Test
	void testFindById() {

		Game g1 = gameConstructor(1, LocalDate.now(), 10, false);
		Game g2 = gameConstructor(1, LocalDate.now(), 20, true);

		gameService.createGame(g1);
		gameService.createGame(g2);

		//
		assertTrue(gameService.findById(g1.getId()).orElse(null).equals(g1));

		//
		assertFalse(gameService.findById(g1.getId()).orElse(null).equals(g2));
	}

	@Test
	void testNewGame() {
		Game game = new Game();
		game.setCreator(this.user1);
		game.setDate(LocalDate.now());
		game.setDuration(4000);
		game.setIsInProgress(false);
		gameService.createGame(game);
		Collection<Game> gamesInDatabase = gameService.findAll();

		//
		assertTrue(gamesInDatabase.contains(game));

		Game game2 = new Game();
		game2.setCreator(this.user1);
		game2.setDate(LocalDate.now());
		game2.setDuration(4000);
		game2.setIsInProgress(false);
		Collection<Game> gamesInDatabase2 = gameService.findAll();

		//
		assertFalse(gamesInDatabase2.contains(game2));
	}

	@Test
	void testDeleteGame() {
		Game game = new Game();
		game.setCreator(this.user1);
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
	void testUpdateGame() {
		Game game = gameConstructor(1, LocalDate.now(), 100, false);
		gameService.createGame(game);
		game.setCreator(this.user2);
		gameService.updateGame(game);

		//
		assertFalse(game.getCreator().equals(this.user1));

		//
		assertTrue(game.getCreator().equals(this.user2));
	}

	@Test
	void testFindByWinner() throws DataAccessException, DuplicatedUserEmailException {
		Game g1 = gameConstructor(1, LocalDate.now(), 10, false);
		Game g2 = gameConstructor(1, LocalDate.now(), 20, true);
		userService.saveUser(this.user1);
		userService.saveUser(this.user2);
		g1.setWinner(this.user2);
		g2.setWinner(this.user2);
		gameService.updateGame(g1);
		gameService.updateGame(g2);

		Collection<Game> gamesWinner = gameService.findByWinner(this.user2);

		//
		assertTrue(gamesWinner.contains(g1) && gamesWinner.contains(g2));

		g1.setWinner(null);
		gameService.updateGame(g1);

		Collection<Game> gamesWinner2 = gameService.findByWinner(this.user2);

		//
		assertFalse(gamesWinner2.contains(g1) && gamesWinner2.contains(g2));
	}

	@Test
	void testGetUrl() throws DataAccessException, DuplicatedUserEmailException {
		Game g1 = gameConstructor(1, LocalDate.now(), 0, false);
		userService.saveUser(this.user1);
		List<User> users = new ArrayList<User>();
		users.add(this.user1);
		
		g1.setUsers(users);
		gameService.updateGame(g1);

		//
		assertTrue(gameService.getGameUrl(g1).equals("waiting/" + g1.getId()));

	}
	@Test
	void testFindAllEnded() {

		Game g1 = gameConstructor(1, LocalDate.now(), 10, false);
		Game g2 = gameConstructor(1, LocalDate.now(), 20, true);
		g1.setIsInProgress(false);
		gameService.updateGame(g1);
		gameService.updateGame(g2);

		Collection<Game> endedGames = gameService.findAllEnded();
		System.out.println(endedGames);

		//
		assertTrue(!endedGames.contains(g2));
		assertTrue(endedGames.contains(g1));

		g1.setIsInProgress(true);
		gameService.updateGame(g1);

		endedGames = gameService.findAllEnded();

		//
		assertFalse(endedGames.contains(g1) && endedGames.contains(g2));
	}

	@Test
	void testFindByIsInProgress() {

		Game g1 = gameConstructor(1, LocalDate.now(), 10, false);
		Game g2 = gameConstructor(1, LocalDate.now(), 20, true);
		g1.setIsInProgress(false);
		gameService.updateGame(g1);
		gameService.updateGame(g2);

		Collection<Game> progressGames = gameService.findAllByIsInProgress();

		//
		assertTrue(!progressGames.contains(g1) && progressGames.contains(g2));

		g1.setIsInProgress(true);
		gameService.updateGame(g1);

		progressGames = gameService.findAllByIsInProgress();

		//
		assertFalse(!progressGames.contains(g1) && !progressGames.contains(g2));
	}

	@Test
	void testFindByUser() throws DataAccessException, DuplicatedUserEmailException {

		Game g1 = gameConstructor(1, LocalDate.now(), 10, false);
		Game g2 = gameConstructor(2, LocalDate.now(), 10, false);

		userService.saveUser(this.user1);
		userService.saveUser(this.user2);

		List<User> users = new ArrayList<User>();
		users.add(this.user1);
		users.add(this.user2);

		g1.setUsers(users);
		g2.setUsers(users);

		gameService.updateGame(g1);
		gameService.updateGame(g2);

		Collection<Game> games = gameService.findByUser(userService.findById(this.user1.getId()).orElse(null));

		//
		assertTrue(games.contains(g1) && games.contains(g2));

		users.remove(this.user1);
		g1.setUsers(users);
		gameService.updateGame(g1);
		games = gameService.findByUser(userService.findById(this.user1.getId()).orElse(null));

		//
		assertFalse(!games.contains(g1) && games.contains(g2));
	}

	@Test
	void testFindByIsInProgressAndUser() throws DataAccessException, DuplicatedUserEmailException {

		Game g1 = gameConstructor(1, LocalDate.now(), 10, false);

		userService.saveUser(this.user1);
		userService.saveUser(this.user2);

		List<User> users = new ArrayList<User>();
		users.add(this.user1);
		users.add(this.user2);

		g1.setUsers(users);

		gameService.updateGame(g1);

		Optional<Game> progressGame1 = gameService
				.findGameInProgressByUser(userService.findById(this.user1.getId()).orElse(null));
		Optional<Game> progressGame2 = gameService
				.findGameInProgressByUser(userService.findById(this.user1.getId()).orElse(null));

		//
		assertTrue(!progressGame1.equals(null) && !progressGame2.equals(null));

		users.remove(0);
		g1.setUsers(users);
		gameService.updateGame(g1);

		Optional<Game> progressGame11 = gameService
				.findGameInProgressByUser(userService.findById(this.user1.getId()).orElse(null));
		Optional<Game> progressGame22 = gameService
				.findGameInProgressByUser(userService.findById(this.user1.getId()).orElse(null));

		//
		assertFalse(progressGame11.equals(null) && !progressGame22.equals(null));
	}

	@Test
	void testAddPlayerToGame() throws DataAccessException, DuplicatedUserEmailException {

		Game g1 = gameConstructor(1, LocalDate.now(), 0, false);
		userService.saveUser(this.user1);
		userService.saveUser(this.user2);

		List<User> users = new ArrayList<User>();
		users.add(this.user1);
		users.add(this.user2);
		g1.setUsers(users);
		gameService.updateGame(g1);

		userService.saveUser(this.user3);

		//
		assertFalse(g1.getUsers().contains(this.user3));

		users.add(this.user3);

		gameService.updateGame(g1);

		//
		assertTrue(g1.getUsers().contains(this.user3));
	}

	@Test
	void testFindRanking() throws DataAccessException, DuplicatedUserEmailException {

		Game g1 = gameConstructor(1, LocalDate.now(), 10, false);
		Game g2 = gameConstructor(2, LocalDate.now(), 10, false);
		Game g3 = gameConstructor(2, LocalDate.now(), 10, false);
		userService.saveUser(this.user1);
		userService.saveUser(this.user2);
		g1.setWinner(this.user1);
		g2.setWinner(this.user2);
		g3.setWinner(this.user2);
		gameService.updateGame(g1);
		gameService.updateGame(g2);
		gameService.updateGame(g3);

		List<Tuple> ranking = gameService.findRanking();

		boolean res = true;
		int cont = 0;
		for (Tuple tuple : ranking) {
			if (cont == 0) {
				res = tuple.get(0).equals(userService.findById(this.user2.getId()).orElse(null).getUsername())
						&& tuple.get(1).toString().equals("2");
				cont++;
			}
			if (cont == 1) {
				res = tuple.get(0).equals(userService.findById(this.user1.getId()).orElse(null).getUsername())
						&& tuple.get(1).toString().equals("1");
			}
		}

		//
		assertTrue(res);

		for (Tuple tuple : ranking) {
			if (cont == 1) {
				res = !tuple.get(0).equals(userService.findById(this.user2.getId()).orElse(null).getUsername())
						&& tuple.get(1).toString().equals("2");
				cont++;
			}
			if (cont == 0) {
				res = !tuple.get(0).equals(userService.findById(this.user1.getId()).orElse(null).getUsername())
						&& tuple.get(1).toString().equals("1");
			}
		}

		//
		assertFalse(res);
	}

	@Test
	void testFindBetweenDates() throws DataAccessException, DuplicatedUserEmailException {
		Game g1 = gameConstructor(1, LocalDate.of(2022, 1, 13), 100, false);
		Game g2 = gameConstructor(1, LocalDate.of(2022, 1, 14), 100, false);
		userService.saveUser(this.user1);
		userService.saveUser(this.user2);
		User gamer1 = this.user1;
		User gamer2 = this.user2;
		List<User> lista = new ArrayList<User>() {
			{
				add(gamer1);
				add(gamer2);
			}
		};
		g1.setUsers(lista);
		g2.setUsers(lista);
		gameService.updateGame(g1);
		gameService.updateGame(g2);

		Integer findBetweenDates = gameService.findBetweenDates(gamer1, LocalDate.of(2022, 1, 12),
				LocalDate.of(2022, 1, 15));

		//
		assertTrue(findBetweenDates == 2);

		Integer findBetweenDates2 = gameService.findBetweenDates(gamer1, LocalDate.of(2022, 1, 14),
				LocalDate.of(2022, 1, 15));

		//
		assertFalse(findBetweenDates2 == 2);
	}

	@Test
	void testGetClassification() throws DataAccessException, DuplicatedUserEmailException {

		Game g1 = gameConstructor(1, LocalDate.now(), 1000, false);

		userService.saveUser(this.user1);
		userService.saveUser(this.user2);
		List<User> lista = new ArrayList<User>() {
			{
				add(user1);
				add(user2);
			}
		};
		g1.setUsers(lista);
		gameService.updateGame(g1);

		GameUser gameUser1 = gameUserService.findByGameAndUser(g1, this.user1).orElse(null);
		gameUser1.setGlory(22);
		gameUser1.setGold(1);
		gameUserService.saveGameUser(gameUser1);

		GameUser gameUser2 = gameUserService.findByGameAndUser(g1, this.user2).orElse(null);
		gameUser2.setGlory(26);
		gameUser2.setGold(1);
		gameUserService.saveGameUser(gameUser2);

		List<UserGlory> clasi = gameService.getClassification(g1);

		//
		assertTrue(clasi.get(0).getUser().equals(this.user2) && clasi.get(1).getUser().equals(this.user1));

		//
		assertFalse(clasi.get(0).getUser().equals(this.user1) && clasi.get(1).getUser().equals(this.user2));
	}

	@Test
	void testFindAllByCreator() throws DataAccessException, DuplicatedUserEmailException {
		userService.saveUser(this.user1);
		Game g1 = gameConstructor(this.user1.getId(), LocalDate.now(), 10, false);
		gameService.updateGame(g1);
		User creator = this.user1;
		Collection<Game> gamesCreatedBy = gameService.findAllByCreator(creator);

		//
		assertTrue(gamesCreatedBy.contains(g1));
	}

	@Test
	void testFindByJoinCode() {
		Game g1 = gameConstructor(1, LocalDate.now(), 10, false);
		g1.setJoinCode("abcde");
		gameService.updateGame(g1);

		Optional<Game> gameByCode = gameService.findByJoinCode("abcde");

		assertTrue(!gameByCode.equals(null));
	}
	
	@Test
	void testSelectHeroe() throws HeroeNotAvailableException, DataAccessException, DuplicatedUserEmailException {
		Game g1 = gameConstructor(1, LocalDate.now(), 10, false);

		userService.saveUser(this.user1);

		List<User> users = new ArrayList<User>();
		users.add(this.user1);

		g1.setUsers(users);

		gameService.updateGame(g1);
		HeroeCard heroe=heroeCardService.findById(1).get();
		gameService.selectHeroe(g1, user1, heroe.getName());
		gameService.updateGame(g1);
		GameUser gameUser = gameUserService.findByGameAndUser(g1, user1).get();

		assertTrue(gameUser.getHeroe().equals(heroe));
	}
	
	
	@Test
	void testSelectFirstPlayer() throws DontHaveEnoughGoldToBuyException, ItemNotSelectedException, Exception {
		Game g1 = gameConstructor(1, LocalDate.now(), 10, true);

		userService.saveUser(this.user1);

		List<User> users = new ArrayList<User>();
		users.add(this.user1);
		

		
		g1.setUsers(users);
		gameService.updateGame(g1);
		
		gameService.selectFirstPlayer(g1.getId());
		gameService.updateGame(g1);
		assertTrue(g1.getUserPlaying().equals(user1));
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

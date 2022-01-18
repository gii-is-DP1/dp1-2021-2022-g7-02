package org.springframework.samples.notimeforheroes.heroecard;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

<<<<<<< HEAD
import java.util.Collection;
=======
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
>>>>>>> b434d1c5d6e855688f18cf59f59fe2a8d4cb66b7

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCard;
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCardsService;
<<<<<<< HEAD
=======
import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCard;
import org.springframework.samples.notimeforheroes.cards.skillcard.SkillCard;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.game.GameService;
import org.springframework.samples.notimeforheroes.game.GameServiceTests;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUser;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUserService;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.samples.notimeforheroes.user.UsersServiceTests;
import org.springframework.samples.notimeforheroes.user.exceptions.DuplicatedUserEmailException;
>>>>>>> b434d1c5d6e855688f18cf59f59fe2a8d4cb66b7
import org.springframework.stereotype.Service;
import java.util.List;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class HeroeCardServiceTest {

	@Autowired
	HeroeCardsService heroeCardsService;

	@Autowired
	GameUserService gameUserService;

	@Autowired
	GameService gameService;

	@Autowired
	UserService userService;

	@Test
	void testFindAllHeroeCardPagination() {
		Integer heroeCards = heroeCardsService.findAllPage(0, 2).size();
		assertTrue(heroeCards == 2);

	}

	@Test
	void testFindAllHeroeCard() {
		Integer AllHeroeCards = heroeCardsService.findAll().size();
		HeroeCard heroeCard = NewHeroeCard("name", "url", 2, "skill", "color");
		heroeCardsService.createHeroeCard(heroeCard);

		Integer NewAllHeroeCard = heroeCardsService.findAll().size();
		assertTrue(NewAllHeroeCard == AllHeroeCards + 1);
	}

	@Test
	void testFindByIdHeroeCard() {
		HeroeCard heroeCard = NewHeroeCard("name", "url", 2, "skill", "color");
		heroeCardsService.createHeroeCard(heroeCard);
		assertTrue(heroeCardsService.findById(heroeCard.getId()).orElse(null).equals(heroeCard));
	}

	@Test
	void testFindByNameHeroeCard() {
		HeroeCard heroeCard = NewHeroeCard("name", "url", 2, "skill", "color");
		heroeCardsService.createHeroeCard(heroeCard);
		assertTrue(heroeCardsService.findByName(heroeCard.getName()).equals(heroeCard));
	}

	@Test
	void testFindByColorHeroeCard() {
		HeroeCard heroeCard = NewHeroeCard("name", "url", 2, "skill", "azul");
		heroeCardsService.createHeroeCard(heroeCard);
		Collection<HeroeCard> NewHeroeCardsByColor = heroeCardsService.findByColor("azul");
		assertTrue(NewHeroeCardsByColor.contains(heroeCard));
	}

	@Test
	void testFindHeroesOfGame() throws DataAccessException, DuplicatedUserEmailException {
		User user1 = UsersServiceTests.userConstructor("Antonio", "Paco", "pacoan", "p1a@gmail.com", "1234");

		Game game = gameConstructor(1, LocalDate.now(), 10, false);
		gameService.createGame(game);

		HeroeCard heroeCard = NewHeroeCard("name", "url", 2, "skill", "azul");
		heroeCardsService.createHeroeCard(heroeCard);

		userService.saveUser(user1);
		List<User> lista = new ArrayList<User>();
		lista.add(user1);

		game.setUsers(lista);
		gameService.updateGame(game);

		GameUser gameUser1 = gameUserService.findByGameAndUser(game, user1).get();
		gameUser1.setHeroe(heroeCard);
		gameUserService.saveGameUser(gameUser1);

		//
		assertTrue(heroeCardsService.findHeroesOfGame(game).contains(heroeCard));

	}

	@Test
	void testFindByColorAndGame() throws DataAccessException, DuplicatedUserEmailException {

		User user1 = UsersServiceTests.userConstructor("Antonio", "Paco", "pacoan", "p1a@gmail.com", "1234");

		Game game = gameConstructor(1, LocalDate.now(), 10, false);
		gameService.createGame(game);

		HeroeCard heroeCard = NewHeroeCard("name", "url", 2, "skill", "azul");
		heroeCardsService.createHeroeCard(heroeCard);

		userService.saveUser(user1);
		List<User> lista = new ArrayList<User>();
		lista.add(user1);

		game.setUsers(lista);
		gameService.updateGame(game);

		GameUser gameUser1 = gameUserService.findByGameAndUser(game, user1).get();
		gameUser1.setHeroe(heroeCard);
		gameUserService.saveGameUser(gameUser1);

		Collection<HeroeCard> heroeCardColl = heroeCardsService.findByColorAndGame("azul", game);

		//
		assertTrue(heroeCardColl.contains(heroeCard));

	}

	@Test
	void testEditHeroeCard() {
		HeroeCard heroecard = heroeCardsService.findById(1).get();
		String oldColor = heroecard.getColor();

		String newColor = oldColor + " sky";
		heroecard.setColor(newColor);
		heroeCardsService.createHeroeCard(heroecard);

		assertThat(heroeCardsService.findById(heroecard.getId()).get().getColor()).isEqualTo(newColor);

	}

	@Test
	void testDeleteHeroeCard() {
		HeroeCard heroeCard = NewHeroeCard("name", "url", 2, "skill", "color");
		heroeCardsService.createHeroeCard(heroeCard);

		HeroeCard heroe = heroeCardsService.findById(heroeCard.getId()).get();

		assertTrue(heroeCardsService.findAll().contains(heroe));

		heroeCardsService.deleteHeroeCard(heroeCard);
		assertFalse(heroeCardsService.findAll().contains(heroe));

	}

	@Test
	public void testNewHeroeCard() {
		Integer Heroes = heroeCardsService.findAll().size();

		HeroeCard heroeCard = NewHeroeCard("name", "url", 2, "skill", "color");
		heroeCardsService.createHeroeCard(heroeCard);

		Integer newHeroes = heroeCardsService.findAll().size();
		assertTrue(Heroes != newHeroes);
	}

	private HeroeCard NewHeroeCard(String name, String url, Integer maxHealth, String skill, String color) {
		// Set<SkillCard> Skills = new HashSet<SkillCard>();
		HeroeCard heroeCard = new HeroeCard();
		heroeCard.setColor(color);
		heroeCard.setMaxHealth(maxHealth);
		heroeCard.setName(name);
		heroeCard.setSkill(skill);
		// heroeCard.setSkills(skills);
		heroeCard.setUrl(url);
		return heroeCard;
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

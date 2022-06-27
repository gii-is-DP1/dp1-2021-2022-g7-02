package org.springframework.samples.notimeforheroes.skillcard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.samples.notimeforheroes.actions.Action;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCardsService;
import org.springframework.samples.notimeforheroes.cards.marketcard.gamesMarket.GameMarketService;
import org.springframework.samples.notimeforheroes.cards.skillcard.SkillCard;
import org.springframework.samples.notimeforheroes.cards.skillcard.SkillCardsService;
import org.springframework.samples.notimeforheroes.cards.skillcard.gamesUsersSkillcards.GamesUsersSkillCards;
import org.springframework.samples.notimeforheroes.cards.skillcard.gamesUsersSkillcards.SkillState;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.game.GameService;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUser;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUserService;
import org.springframework.samples.notimeforheroes.game.GameService;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.samples.notimeforheroes.user.UsersServiceTests;
import org.springframework.samples.notimeforheroes.user.exceptions.DuplicatedUserEmailException;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class SkillCardServiceTest {

	@Autowired
	SkillCardsService skillService;

	@Autowired
	GameService gameService;

	@Autowired
	UserService userService;
	@Autowired
	GameUserService gameUserService;

	@Autowired
	HeroeCardsService heroeCardsService;

	@Test
	void testFindAllPage() {
		Integer skillCards = skillService.findAllPage(0, 3).size();
		//
		assertTrue(skillCards == 3);
	}

	@Test
	void testFindAll() {
		Integer allSkillCard = skillService.findAll().size();
		Collection<Action> actions = new ArrayList<>();
		SkillCard skillCard = newSkillCard("name", "rl", "description", "color", actions);
		skillService.saveSkillCard(skillCard);

		Integer allSkillCard2 = skillService.findAll().size();

		//
		assertTrue(allSkillCard + 1 == allSkillCard2);

	}

	@Test
	void testFindById() {
		Collection<Action> actions = new ArrayList<>();
		SkillCard skillCard = newSkillCard("name", "rl", "description", "color", actions);
		skillService.saveSkillCard(skillCard);

		//
		assertTrue(skillService.findById(skillCard.getId()).orElse(null).equals(skillCard));

	}

	@Test
	void testFindByColor() {
		Collection<Action> actions = new ArrayList<>();
		SkillCard skillCard = newSkillCard("name", "rl", "description", "color", actions);
		skillService.saveSkillCard(skillCard);

		Collection<SkillCard> SkillCardsByColor = skillService.findByColor("color");
		assertTrue(SkillCardsByColor.contains(skillCard));
	}

	@Test
	void testFindByName() {
		Collection<Action> actions = new ArrayList<>();
		SkillCard skillCard = newSkillCard("name", "rl", "description", "color", actions);
		skillService.saveSkillCard(skillCard);

		SkillCard SkillCardsByName = skillService.findByName("name");
		assertTrue(SkillCardsByName.equals(skillCard));
	}

	@Test
	void testFindByGameAndUser() throws DataAccessException, DuplicatedUserEmailException {
		User user1 = UsersServiceTests.userConstructor("Antonio", "Paco", "pacoan", "p1a@gmail.com", "1234");
		Collection<Action> actions = new ArrayList<>();

		Collection<SkillCard> skillsCards = new ArrayList<>();
		SkillCard skillCard = newSkillCard("name", "url", "description", "color", actions);
		skillService.saveSkillCard(skillCard);
		skillsCards.add(skillCard);

		Game game = gameConstructor(1, LocalDate.now(), 10, false);
		gameService.createGame(game);

		userService.saveUser(user1);
		List<User> lista = new ArrayList<User>();
		lista.add(user1);

		game.setUsers(lista);
		gameService.updateGame(game);

		GameUser gameUser1 = gameUserService.findByGameAndUser(game, user1).get();
		gameUser1.setSkillCards(skillsCards);
		gameUserService.saveGameUser(gameUser1);

		assertTrue(skillService.findByGameAndUser(game, user1).contains(skillCard));
	}

	@Test
	void testFindAllOnDeckSkillsByGameAndUser() throws DataAccessException, DuplicatedUserEmailException {
		User user1 = UsersServiceTests.userConstructor("Antonio", "Paco", "pacoan", "p1a@gmail.com", "1234");

		Game game = gameConstructor(1, LocalDate.now(), 10, false);
		gameService.createGame(game);

		userService.saveUser(user1);
		List<User> lista = new ArrayList<User>();
		lista.add(user1);

		game.setUsers(lista);
		gameService.updateGame(game);

		Collection<SkillCard> skillCards = skillService.findByGameAndUser(game, user1);
		Collection<SkillCard> allSkillCards = skillService.findAll();

		assertTrue(allSkillCards.containsAll(skillCards));

	}

	@Test
	void testFindAllOnDiscardedSkillsByGameAndUser() throws DataAccessException, DuplicatedUserEmailException {
		User user1 = UsersServiceTests.userConstructor("Antonio", "Paco", "pacoan", "p1a@gmail.com", "1234");
		Collection<Action> actions = new ArrayList<>();

		Collection<SkillCard> skillsCards = new ArrayList<>();
		SkillCard skillCard = newSkillCard("name", "url", "description", "color", actions);
		skillService.saveSkillCard(skillCard);
		skillsCards.add(skillCard);

		Game game = gameConstructor(1, LocalDate.now(), 10, false);
		gameService.createGame(game);

		userService.saveUser(user1);
		List<User> lista = new ArrayList<User>();
		lista.add(user1);

		game.setUsers(lista);
		gameService.updateGame(game);

		GamesUsersSkillCards gameUserSkillCard = new GamesUsersSkillCards();
		gameUserSkillCard.setSkillState(SkillState.DISCARD);

		GameUser gameUser1 = gameUserService.findByGameAndUser(game, user1).get();
		gameUser1.setSkillCards(skillsCards);
		gameUserService.saveGameUser(gameUser1);

		assertTrue(!skillService.findAllOnDiscardedSkillsByGameAndUser(game, user1).contains(skillCard));
	}

	@Test
	void testEditSkillCard() {
		SkillCard SkillCard = skillService.findById(1).get();
		String oldName = SkillCard.getName();

		String newName = oldName + " sky";
		SkillCard.setName(newName);
		skillService.saveSkillCard(SkillCard);

		assertThat(SkillCard.getName()).isEqualTo(newName);

	}

	@Test
	void testDeleteSkillCard() {

		SkillCard skillCard = new SkillCard();
		skillCard.setName("Disparo certero");
		skillCard.setUrl("https:");
		skillCard.setDescription("description");
		skillService.saveSkillCard(skillCard);

		SkillCard skill = skillService.findById(skillCard.getId()).get();
		assertTrue(skillService.findAll().contains(skill));

		skillService.deleteSkillCard(skillCard);
		assertFalse(skillService.findAll().contains(skill));

	}

	@Test
	void testFindAllAvailableSkillsByGameAndUser() throws DataAccessException, DuplicatedUserEmailException {
		Game g1 = gameConstructor(1, LocalDate.now(), 10, false);
		User user = UsersServiceTests.userConstructor("Antonio", "Paco", "pacoan", "p1a@gmail.com", "1234");
		userService.saveUser(user);
		List<User> users = new ArrayList<User>();
		users.add(user);
		g1.setUsers(users);
		gameService.updateGame(g1);
		Collection<SkillCard> skillCards = skillService.findAllAvailableSkillsByGameAndUser(g1, user);

		assertTrue(skillCards.size() <= 4);
	}

	@Test
	void testNewSkillCard() {
		Integer skill = skillService.findAll().size();

		SkillCard skillCard = new SkillCard();
		skillCard.setName("Disparo certero");
		skillCard.setUrl("https:");
		skillCard.setDescription("description");
		skillService.saveSkillCard(skillCard);

		Integer newSkill = skillService.findAll().size();
		assertTrue(skill != newSkill);
	}

	public SkillCard newSkillCard(String name, String url, String description, String color,
			Collection<Action> actions) {

		SkillCard skillCard = new SkillCard();
		skillCard.setName(name);
		skillCard.setUrl(url);
		skillCard.setDescription(description);
		skillCard.setColor(color);
		skillCard.setActions(actions);
		return skillCard;

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

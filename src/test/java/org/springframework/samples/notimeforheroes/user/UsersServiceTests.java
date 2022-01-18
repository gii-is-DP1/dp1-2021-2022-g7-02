package org.springframework.samples.notimeforheroes.user;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCardsService;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.game.GameService;
import org.springframework.samples.notimeforheroes.game.GameServiceTests;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUser;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUserService;
import org.springframework.samples.notimeforheroes.user.exceptions.DuplicatedUserEmailException;
import org.springframework.scheduling.quartz.LocalDataSourceJobStore;
import org.springframework.stereotype.Service;

import net.bytebuddy.asm.Advice.Local;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class UsersServiceTests {

	@Autowired
	private UserService userService;

	@Autowired
	private GameService gameService;

	@Autowired
	private GameUserService gameUserService;

	@Autowired
	private HeroeCardsService heroeCardsService;

	public static User userConstructor(String lastname, String name, String username, String email, String password) {
		User u = new User();
		u.setEmail(email);
		u.setLastname(lastname);
		u.setPassword(password);
		u.setUsername(username);
		u.setName(name);

		return u;
	}

	public Game gameConstructor(User creador) throws DataAccessException, DuplicatedUserEmailException {
		Game g = new Game();
		g.setCreator(creador);
		g.setDate(LocalDate.now());
		g.setDuration(10);
		g.setIsInProgress(true);

		return g;
	}

	public User user1 = userConstructor("Antonio", "Paco", "pacoan", "pad@gmail.com", "1234");
	public User user2 = userConstructor("Jose", "Manuel", "manujo", "manu@gmail.com", "123234");

	@Test
	void testFindAll() {
		User user1 = this.user1;
		User user2 = this.user2;

		Collection<User> currentUsers = userService.findAll();

		Collection<User> usersToAdd = List.of(user1, user2);
		usersToAdd.stream().forEach(userToAdd -> {
			try {
				userService.saveUser(userToAdd);
			} catch (DataAccessException | DuplicatedUserEmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentUsers.add(userToAdd);
		});

		Collection<User> usersAtDatabase = userService.findAll();

		//
		assertTrue(currentUsers.equals(usersAtDatabase));
	}

	@Test
	void testFindById() throws DataAccessException, DuplicatedUserEmailException {

		User user1 = this.user1;
		User user2 = this.user2;

		userService.saveUser(user1);
		userService.saveUser(user2);

		//
		assertTrue(userService.findById(user1.getId()).orElse(null).equals(user1));

		//
		assertFalse(userService.findById(user1.getId()).orElse(null).equals(user2));
	}

	@Test
	void testFindByUsername() throws DataAccessException, DuplicatedUserEmailException {

		User user1 = this.user1;
		userService.saveUser(user1);

		//
		assertTrue(userService.findByUsername("pacoan").orElse(null).equals(user1));

		//
		assertFalse(userService.findByUsername("javier").orElse(null).equals(user1));
	}

	@Test
	void testIsUserAdmin() throws DataAccessException, DuplicatedUserEmailException {

		User user1 = userService.findById(1).orElse(null);
		User user2 = userService.findById(8).orElse(null);

		//
		assertTrue(user1.isAdmin());

		//
		assertFalse(user2.isAdmin());
	}

	@Test
	void testFindAllInGameWithHeroe() throws DataAccessException, DuplicatedUserEmailException {

		userService.saveUser(this.user1);
		userService.saveUser(this.user2);
		User user1 = this.user1;
		User user2 = this.user2;
		List<User> users = new ArrayList<User>() {
			{
				add(user1);
				add(user2);
			}
		};

		Game g1 = gameService.findById(1).orElse(null);
		g1.setIsInProgress(true);
		g1.setUsers(users);
		gameService.updateGame(g1);

		//
		assertFalse(userService.findAllInGameWithHeroeSelected(g1).contains(user1)
				&& userService.findAllInGameWithHeroeSelected(g1).contains(user2));

		GameUser gameUser1 = gameUserService.findByGameAndUser(g1, user1).get();
		gameUser1.setHeroe(heroeCardsService.findByName("Taheral"));
		gameUserService.saveGameUser(gameUser1);

		GameUser gameUser2 = gameUserService.findByGameAndUser(g1, user2).get();
		gameUser2.setHeroe(heroeCardsService.findByName("Feldon"));
		gameUserService.saveGameUser(gameUser2);

		//
		assertTrue(userService.findAllInGameWithHeroeSelected(g1).contains(user1)
				&& userService.findAllInGameWithHeroeSelected(g1).contains(user2));
	}

	@Test
	void testDeleteUser() throws DataAccessException, DuplicatedUserEmailException {
		User user = userConstructor("Fernandez", "Fermin", "ffgonext", "fermin@email.com", "123");
		userService.saveUser(this.user1);
		userService.saveUser(user);
		Game g1 = gameConstructor(user);
		Game g2 = gameConstructor(this.user1);
		List<User> users = new ArrayList<User>() {
			{
				add(user);
			}
		};
		g1.setUsers(users);
		g2.setUsers(users);
		gameService.updateGame(g1);
		gameService.updateGame(g2);

		//
		assertFalse(!g1.getUsers().contains(user) && !g2.getUsers().contains(user));

		userService.deleteUser(user);

		//
		assertTrue(!g1.getUsers().contains(user) && !g2.getUsers().contains(user));
	}

	@Test
	public void testNingunJugador() {
		Collection<User> users = userService.findAll();
		for (User u : users) {
			userService.deleteUser(u);
		}
		assertTrue(userService.findAll().isEmpty());
	}

	@Test
	public void testUnJugador() throws DataAccessException, DuplicatedUserEmailException {
		Collection<User> users = userService.findAll();
		for (User u : users) {
			userService.deleteUser(u);
		}
		User user = new User();
		user.setName("Juan");
		user.setEmail("juan@gmail.com");
		user.setLastname("Soto");
		user.setUsername("Juanillo7");
		user.setPassword("1234");
		userService.saveUser(user);
		assertTrue(userService.findAll().size() == 1);
	}

	@Test
	void testGetOpponents() throws DataAccessException, DuplicatedUserEmailException {
		User user = userConstructor("Fernandez", "Fermin", "ffgonext", "fermin@email.com", "123");
		userService.saveUser(this.user1);
		userService.saveUser(user);
		userService.saveUser(this.user2);
		Game g1 = gameConstructor(user);
		Game g2 = gameConstructor(this.user1);
		List<User> users1 = new ArrayList<User>() {
			{
				add(user);
				add(user2);
			}
		};
		List<User> users2 = new ArrayList<User>() {
			{
				add(user);
				add(user1);
				add(user2);
			}
		};
		g1.setUsers(users1);
		g2.setUsers(users2);
		gameService.updateGame(g1);
		gameService.updateGame(g2);

		Map<User, Integer> resEsperado = new HashMap<User, Integer>();
		resEsperado.put(user, 2);
		resEsperado.put(this.user1, 1);
		Map<User, Integer> opponents = userService.getListOfOpponents(this.user2);

		//
		assertTrue(resEsperado.equals(opponents));

		opponents = userService.getListOfOpponents(user);

		//
		assertFalse(resEsperado.equals(opponents));
	}

	@Test
	public void testMasJugadores() throws DataAccessException, DuplicatedUserEmailException {
		Collection<User> users = userService.findAll();
		for (User u : users) {
			userService.deleteUser(u);
		}
		User user = new User();
		user.setName("Juan");
		user.setEmail("juan@gmail.com");
		user.setLastname("Soto");
		user.setUsername("Juanillo7");
		user.setPassword("1234");
		userService.saveUser(user);

		User user1 = new User();
		user1.setName("Jose");
		user1.setEmail("jose@gmail.com");
		user1.setLastname("Sota");
		user1.setUsername("Jose7");
		user1.setPassword("1234");
		userService.saveUser(user1);

		assertTrue(userService.findAll().size() > 1);
	}

	@Test
	public void testEditUser() throws DuplicatedUserEmailException {
		User user = this.user1;
		userService.saveUser(user);

		String oldName = user.getName();

		String newName = oldName + "X";
		user.setName(newName);
		// userService.saveUser(user);

		User user1 = userService.findByUsername(this.user1.getUsername()).get();

		//
		assertTrue(user1.getName().equals(newName));
	}

	@Test
	public void testEditUserSameName() throws DuplicatedUserEmailException {
		String email = this.user1.getEmail();

		User user = new User();
		user.setEmail(email);
		user.setName(this.user1.getName());
		user.setLastname("Sota");
		user.setUsername("Jose7");
		user.setPassword("1234");
		userService.saveUser(user);

		Assertions.assertThrows(DuplicatedUserEmailException.class, () -> {
			userService.saveUser(user);
		});

	}
}
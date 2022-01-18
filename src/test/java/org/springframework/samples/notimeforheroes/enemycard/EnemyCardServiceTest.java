package org.springframework.samples.notimeforheroes.enemycard;

import static org.assertj.core.api.Assertions.assertThat;

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
import org.springframework.dao.DataAccessException;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyCard;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyCardService;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.game.GameService;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUser;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUserService;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.samples.notimeforheroes.user.UsersServiceTests;
import org.springframework.samples.notimeforheroes.user.exceptions.DuplicatedUserEmailException;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EnemyCardServiceTest {

	@Autowired
	EnemyCardService enemyCardService;

	@Autowired
	GameService gameService;

	@Autowired
	UserService userService;

	@Autowired
	GameUserService gameUserService;

	@Test
	void testFindAllEnemiesPagination() {
		Integer enemies = enemyCardService.findAllPage(0, 2).size();
		assertTrue(enemies == 2);
	}

	@Test
	void TestNoEnemyCard() {
		Collection<EnemyCard> enemyCard = enemyCardService.findAll();
		for (EnemyCard c : enemyCard) {
			enemyCardService.deleteEnemyCard(c);
		}
		assertThat(enemyCardService.findAll().isEmpty()).isTrue();

	}

	@Test
	void testCountOnTableEnemiesByGame() throws DataAccessException, DuplicatedUserEmailException {
		Game game = gameConstructor(1, LocalDate.now(), 10, false);
		gameService.createGame(game);

		Integer enemies = enemyCardService.countOnTableEnemiesByGame(game);

		assertTrue(enemies <= 4);

	}

	@Test
	void testFindAllByIsBoss() {
		Collection<EnemyCard> enemyCard = enemyCardService.findAll();

		Collection<EnemyCard> enemyCardIsBoos = enemyCardService.findAllByIsBoss(true);

		assertTrue(enemyCard.containsAll(enemyCardIsBoos));
	}

	@Test
	void TestFindAllEnemies() {
		Integer AllEnemies = enemyCardService.findAll().size();
		EnemyCard enemy = NewEnemyCard(1, 1, 1, 1, "name", "url");
		enemyCardService.createEnemyCard(enemy);

		Integer NewAllEnemies = enemyCardService.findAll().size();
		assertTrue(NewAllEnemies == AllEnemies + 1);
	}

	@Test
	void TestFindByIdEnemyCard() {
		EnemyCard enemy = NewEnemyCard(1, 1, 1, 1, "name", "url");
		enemyCardService.createEnemyCard(enemy);

		assertTrue(enemyCardService.findById(enemy.getId()).orElse(null).equals(enemy));
	}

	@Test
	void testOneEnemyCard() {
		Collection<EnemyCard> enemyCards = enemyCardService.findAll();
		for (EnemyCard c : enemyCards) {
			enemyCardService.deleteEnemyCard(c);
		}
		EnemyCard enemyCard = new EnemyCard();
		enemyCard.setExtraGlory(1);
		enemyCard.setExtraGold(0);
		enemyCard.setGlory(2);
		enemyCard.setMaxHealth(4);
		enemyCard.setName("Enemigo");
		enemyCard.setUrl(".");

		enemyCardService.createEnemyCard(enemyCard);
		assertThat(enemyCardService.findAll().size()).isEqualTo(1);
		assertThat(new ArrayList<>(enemyCardService.findAll()).get(0).getName()).isEqualTo(enemyCard.getName());

	}

	@Test
	void TestMoreThanOneEnemyCard() {

		Collection<EnemyCard> enemyCards = enemyCardService.findAll();
		for (EnemyCard c : enemyCards) {
			enemyCardService.deleteEnemyCard(c);
		}
		EnemyCard enemyCard = new EnemyCard();
		enemyCard.setExtraGlory(1);
		enemyCard.setExtraGold(0);
		enemyCard.setGlory(2);
		enemyCard.setMaxHealth(4);
		enemyCard.setName("Enemigo");
		enemyCard.setUrl(".");
		enemyCardService.createEnemyCard(enemyCard);

		EnemyCard enemyCard1 = new EnemyCard();
		enemyCard1.setExtraGlory(0);
		enemyCard1.setExtraGold(2);
		enemyCard1.setGlory(3);
		enemyCard1.setMaxHealth(5);
		enemyCard1.setName("Enemigo");
		enemyCard1.setUrl(".");

		enemyCardService.createEnemyCard(enemyCard1);

		assertThat(enemyCardService.findAll().size()).isEqualTo(2);

	}

	@Test
	void TestEditEnemyCard() {
		EnemyCard enemycard = enemyCardService.findById(1).get();
		Integer oldExtraglory = enemycard.getExtraGlory();

		Integer newExtraglory = oldExtraglory + 1;
		enemycard.setExtraGlory(newExtraglory);
		enemyCardService.createEnemyCard(enemycard);

		assertThat(enemyCardService.findById(enemycard.getId()).get().getExtraGlory()).isEqualTo(newExtraglory);

	}

	@Test
	void TestDeleteEnemyCard() {

		EnemyCard enemyCard = new EnemyCard();
		enemyCard.setExtraGlory(1);
		enemyCard.setExtraGold(0);
		enemyCard.setGlory(2);
		enemyCard.setMaxHealth(4);
		enemyCard.setName("Enemigo");
		enemyCard.setUrl(".");
		enemyCardService.createEnemyCard(enemyCard);

		EnemyCard enemy = enemyCardService.findById(enemyCard.getId()).get();

		assertTrue(enemyCardService.findAll().contains(enemy));

		enemyCardService.deleteEnemyCard(enemyCard);
		assertFalse(enemyCardService.findAll().contains(enemy));

	}

	@Test
	void TestNewEnemyCard() {
		Integer Enemies = enemyCardService.findAll().size();

		EnemyCard enemyCard = new EnemyCard();
		enemyCard.setExtraGlory(1);
		enemyCard.setExtraGold(0);
		enemyCard.setGlory(2);
		enemyCard.setMaxHealth(4);
		enemyCard.setName("Enemigo");
		enemyCard.setUrl(".");
		enemyCardService.createEnemyCard(enemyCard);

		Integer newEnemies = enemyCardService.findAll().size();
		assertTrue(Enemies != newEnemies);
	}

	private EnemyCard NewEnemyCard(Integer ExtraGlory, Integer ExtraGold, Integer Glory, Integer Health, String name,
			String url) {
		EnemyCard enemyCard = new EnemyCard();
		enemyCard.setExtraGlory(ExtraGlory);
		enemyCard.setExtraGold(ExtraGold);
		enemyCard.setGlory(Glory);
		enemyCard.setMaxHealth(Health);
		enemyCard.setName(name);
		enemyCard.setUrl(url);
		return enemyCard;
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

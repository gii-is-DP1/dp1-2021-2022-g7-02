package org.springframework.samples.notimeforheroes.heroecard;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCard;
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCardsService;
import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCard;
import org.springframework.samples.notimeforheroes.cards.skillcard.SkillCard;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.game.GameService;
import org.springframework.samples.notimeforheroes.game.GameServiceTests;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUser;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUserService;
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
	GameServiceTests gameServiceTest;

	@Test
	void TestFindAllHeroeCard() {
		Integer AllHeroeCards = heroeCardsService.findAll().size();
		HeroeCard heroeCard = NewHeroeCard("name", "url", 2, "skill", "color");
		heroeCardsService.createHeroeCard(heroeCard);

		Integer NewAllHeroeCard = heroeCardsService.findAll().size();
		assertTrue(NewAllHeroeCard == AllHeroeCards + 1);
	}

	@Test
	void TestFindByIdHeroeCard() {
		HeroeCard heroeCard = NewHeroeCard("name", "url", 2, "skill", "color");
		heroeCardsService.createHeroeCard(heroeCard);
		assertTrue(heroeCardsService.findById(heroeCard.getId()).orElse(null).equals(heroeCard));
	}

	@Test
	void TestFindByNameHeroeCard() {
		HeroeCard heroeCard = NewHeroeCard("name", "url", 2, "skill", "color");
		heroeCardsService.createHeroeCard(heroeCard);
		assertTrue(heroeCardsService.findByName(heroeCard.getName()).equals(heroeCard));
	}

	@Test
	void TestFindByColorHeroeCard() {
		HeroeCard heroeCard = NewHeroeCard("name", "url", 2, "skill", "azul");
		heroeCardsService.createHeroeCard(heroeCard);
		Collection<HeroeCard> NewHeroeCardsByColor = heroeCardsService.findByColor("azul");
		assertTrue(NewHeroeCardsByColor.contains(heroeCard));
	}

	@Test
	void TestEditHeroeCard() {
		HeroeCard heroecard = heroeCardsService.findById(1).get();
		String oldColor = heroecard.getColor();

		String newColor = oldColor + " sky";
		heroecard.setColor(newColor);
		heroeCardsService.createHeroeCard(heroecard);

		assertThat(heroeCardsService.findById(heroecard.getId()).get().getColor()).isEqualTo(newColor);

	}

	@Test
	void TestDeleteHeroeCard() {
		HeroeCard heroeCard = NewHeroeCard("name", "url", 2, "skill", "color");
		heroeCardsService.createHeroeCard(heroeCard);

		HeroeCard heroe = heroeCardsService.findById(heroeCard.getId()).get();

		assertTrue(heroeCardsService.findAll().contains(heroe));

		heroeCardsService.deleteHeroeCard(heroeCard);
		assertFalse(heroeCardsService.findAll().contains(heroe));

	}

	@Test
	public void TestNewHeroeCard() {
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

	public GameUser NewGameUser(HeroeCard heroe, Integer maxHealth, Collection<SkillCard> skill,
			List<MarketCard> market,
			Boolean hasEscapeToken, Integer gold, Integer glory, Boolean winner, Integer damageShielded) {
		GameUser gameUser = new GameUser();
		gameUser.setHeroe(heroe);
		gameUser.setDamageShielded(damageShielded);
		gameUser.setGlory(glory);
		gameUser.setGold(gold);
		gameUser.setHasEscapeToken(hasEscapeToken);
		gameUser.setHeroeHealth(maxHealth);
		gameUser.setItems(market);
		gameUser.setSkillCards(skill);
		gameUser.setWinner(winner);
		return gameUser;
	}

}

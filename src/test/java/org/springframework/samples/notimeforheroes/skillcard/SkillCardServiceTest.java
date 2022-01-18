package org.springframework.samples.notimeforheroes.skillcard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.samples.notimeforheroes.actions.Action;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.notimeforheroes.cards.skillcard.SkillCard;
import org.springframework.samples.notimeforheroes.cards.skillcard.SkillCardsService;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.samples.notimeforheroes.user.UsersServiceTests;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class SkillCardServiceTest {

	@Autowired
	SkillCardsService skillService;

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
}

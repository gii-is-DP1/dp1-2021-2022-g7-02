package org.springframework.samples.notimeforheroes.skillcard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.notimeforheroes.skillcard.SkillCard;
import org.springframework.samples.notimeforheroes.skillcard.SkillCardsService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class SkillCardServiceTest {

	
	@Autowired 
	SkillCardsService skillService;
	
	@Test
	public void TestNoSkillCard() {
		Collection<SkillCard> skill = skillService.findAll();
		for(SkillCard c: skill) {
			skillService.deleteSkillCard(c);
		}	
		assertThat(skillService.findAll().isEmpty()).isTrue();
	}
	
	@Test
	public void TestOneSkillCard() {
		Collection<SkillCard> SkillCards = skillService.findAll();
		for(SkillCard c : SkillCards) {
			skillService.deleteSkillCard(c);
		}		
		SkillCard skillCard = new SkillCard();
		skillCard.setName("Disparo certero");
		skillCard.setUrl("https:");
		skillCard.setDescription("description");
		skillCard.setDeckid(2);
		skillService.saveSkillCard(skillCard);
		assertThat(skillService.findAll().size()).isEqualTo(1);

	}
	
	@Test
	public void TestMoreThanOneSkillCard() {
		
		Collection<SkillCard> SkillCards = skillService.findAll();
		for(SkillCard c : SkillCards) {
			skillService.deleteSkillCard(c);
		}		
		SkillCard skillCard = new SkillCard();
		skillCard.setName("Disparo certero");
		skillCard.setUrl("https:");
		skillCard.setDescription("description");
		skillCard.setDeckid(2);
		skillService.saveSkillCard(skillCard);
		
		SkillCard skillCard2 = new SkillCard();
		skillCard2.setName("Lobo");
		skillCard2.setUrl("https:");
		skillCard2.setDescription("description");
		skillCard2.setDeckid(3);
		skillService.saveSkillCard(skillCard2);
		
		assertThat(skillService.findAll().size()).isGreaterThan(1);

	}
	
	@Test 
	public void TestEditSkillCard() {
		SkillCard SkillCard = skillService.findById(1).get();
		String oldName = SkillCard.getName();
		
		String newName = oldName + " sky";
		SkillCard.setName(newName);
		skillService.saveSkillCard(SkillCard);
		
		assertThat(SkillCard.getName()).isEqualTo(newName);

	}
	
	@Test
	public void TestDeleteSkillCard() {	
		
		SkillCard skillCard = new SkillCard();
		skillCard.setName("Disparo certero");
		skillCard.setUrl("https:");
		skillCard.setDescription("description");
		skillCard.setDeckid(2);
		skillService.saveSkillCard(skillCard);
		
		SkillCard skill = skillService.findById(skillCard.getId()).get();
		assertTrue(skillService.findAll().contains(skill));
		
		skillService.deleteSkillCard(skillCard);
		assertFalse(skillService.findAll().contains(skill));

	}
	
	
	@Test
	public void TestNewSkillCard() {	
		Integer skill = skillService.findAll().size();
		
		SkillCard skillCard = new SkillCard();
		skillCard.setName("Disparo certero");
		skillCard.setUrl("https:");
		skillCard.setDescription("description");
		skillCard.setDeckid(2);
		skillService.saveSkillCard(skillCard);
		
		Integer newSkill = skillService.findAll().size();
		assertTrue(skill != newSkill);
	}
	
	
}

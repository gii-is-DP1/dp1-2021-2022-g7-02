package org.springframework.samples.notimeforheroes.heroecard;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.notimeforheroes.heroecard.HeroeCards;
import org.springframework.samples.notimeforheroes.heroecard.HeroeCardsService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class HeroeCardServiceTest {

	@Autowired
	HeroeCardsService HeroeCardService;
	
	@Test
	public void TestNoHeroeCard() {
		Collection<HeroeCards> HeroeCard = HeroeCardService.findAll();
		for(HeroeCards c : HeroeCard) {
			HeroeCardService.deleteHeroeCard(c);
		}
		assertThat(HeroeCardService.findAll().isEmpty()).isTrue();

	}
	
	@Test
	public void TestOneHeroeCard() {
		Collection<HeroeCards> HeroeCards = HeroeCardService.findAll();
		for(HeroeCards c : HeroeCards) {
			HeroeCardService.deleteHeroeCard(c);
		}		
		HeroeCards HeroeCard = new HeroeCards();
		HeroeCard.setId(2);
		HeroeCard.setName("Salomon");
		HeroeCard.setUrl("https:");
		HeroeCard.setLife(2);
		HeroeCard.setSkill("skill");
		HeroeCard.setColor("blue");
		HeroeCard.setDeckid(5);
		HeroeCardService.createHeroeCard(HeroeCard);
		assertThat(HeroeCardService.findAll().size()).isEqualTo(1);

	}
	
	@Test
	public void TestMoreThanOneHeroeCard() {
		
		Collection<HeroeCards> cards = HeroeCardService.findAll();
		for(HeroeCards c : cards) {
			HeroeCardService.deleteHeroeCard(c);
		}			
		HeroeCards HeroeCard = new HeroeCards();
		HeroeCard.setId(2);
		HeroeCard.setName("Salomon");
		HeroeCard.setUrl("https:");
		HeroeCard.setLife(2);
		HeroeCard.setSkill("skill");
		HeroeCard.setColor("blue");
		HeroeCard.setDeckid(5);
		HeroeCardService.createHeroeCard(HeroeCard);
		
		HeroeCards HeroeCard1 = new HeroeCards();
		HeroeCard1.setId(2);
		HeroeCard1.setName("Salomon");
		HeroeCard1.setUrl("https:");
		HeroeCard1.setLife(2);
		HeroeCard1.setSkill("skill");
		HeroeCard1.setColor("blue");
		HeroeCard1.setDeckid(5);
		HeroeCardService.createHeroeCard(HeroeCard1);
		
		assertThat(HeroeCardService.findAll().size()).isGreaterThan(1);

	}
	
	@Test 
	public void TestEditHeroeCard() {
		HeroeCards Heroecard = HeroeCardService.findById(2).get();
		String oldColor = Heroecard.getColor();
		
		String newColor = oldColor + " sky";
		Heroecard.setName(newColor);
		HeroeCardService.createHeroeCard(Heroecard);
		
		Heroecard = HeroeCardService.findById(2).get();
		assertThat(Heroecard.getColor()).isEqualTo(newColor);

	}
	
	@Test
	public void TestDeleteHeroeCard() {	
		
		HeroeCards HeroeCard = new HeroeCards();
		HeroeCard.setId(2);
		HeroeCard.setName("Salomon");
		HeroeCard.setUrl("https:");
		HeroeCard.setLife(2);
		HeroeCard.setSkill("skill");
		HeroeCard.setColor("blue");
		HeroeCard.setDeckid(5);
		HeroeCardService.createHeroeCard(HeroeCard);
		
		assertTrue(HeroeCardService.findAll().contains(HeroeCard));
		HeroeCardService.deleteHeroeCard(HeroeCard);
		assertFalse(HeroeCardService.findAll().contains(HeroeCard));

	}
	
	@Test
	public void TestNewHeroeCard() {	
		Integer Heroes = HeroeCardService.findAll().size();
		
		HeroeCards HeroeCard = new HeroeCards();
		HeroeCard.setId(2);
		HeroeCard.setName("Salomon");
		HeroeCard.setUrl("https:");
		HeroeCard.setLife(2);
		HeroeCard.setSkill("skill");
		HeroeCard.setColor("blue");
		HeroeCard.setDeckid(5);
		HeroeCardService.createHeroeCard(HeroeCard);
		
		Integer newHeroes = HeroeCardService.findAll().size();
		assertFalse(Heroes != newHeroes);
	}
	
	
}

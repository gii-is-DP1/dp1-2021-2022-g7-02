package org.springframework.samples.notimeforheroes.heroecard;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.validation.constraints.Min.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.notimeforheroes.heroecard.HeroeCard;
import org.springframework.samples.notimeforheroes.heroecard.HeroeCardsService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class HeroeCardServiceTest {

	@Autowired
	HeroeCardsService heroeCardsService;
	
	@Test
	public void TestNoHeroeCard() {
		Collection<HeroeCard> HeroeCard = heroeCardsService.findAll();
		for(HeroeCard c : HeroeCard) {
			heroeCardsService.deleteHeroeCard(c);
		}
		assertThat(heroeCardsService.findAll().isEmpty()).isTrue();

	}
	
	@Test
	public void testOneHeroeCard() {
		Collection<HeroeCard> heroeCards= heroeCardsService.findAll();
		for(HeroeCard c : heroeCards) {
			heroeCardsService.deleteHeroeCard(c);
		}		
		HeroeCard heroeCard = new HeroeCard();
		heroeCard.setId(2);
		heroeCard.setName("Salomon");
		heroeCard.setUrl("https:");
		heroeCard.setMaxHealth(2);
		heroeCard.setSkill("skill");
		heroeCard.setColor("blue");
		heroeCardsService.createHeroeCard(heroeCard);
		assertThat(heroeCardsService.findAll().size()).isEqualTo(1);
		assertThat(new ArrayList<>(heroeCardsService.findAll()).get(0).getName()).isEqualTo(heroeCard.getName());

	}
	@Test
	public void TestMoreThanOneHeroeCard() {
		
		Collection<HeroeCard> cards = heroeCardsService.findAll();
		for(HeroeCard c : cards) {
			heroeCardsService.deleteHeroeCard(c);
		}			
		HeroeCard heroeCard = new HeroeCard();
		heroeCard.setId(1);
		heroeCard.setName("Salomon");
		heroeCard.setUrl("https:");
		heroeCard.setMaxHealth(2);
		heroeCard.setSkill("skill");
		heroeCard.setColor("blue");
		heroeCardsService.createHeroeCard(heroeCard);
		
		HeroeCard heroeCard1 = new HeroeCard();
		heroeCard1.setId(2);
		heroeCard1.setName("Salomon");
		heroeCard1.setUrl("https:");
		heroeCard1.setMaxHealth(2);
		heroeCard1.setSkill("skill");
		heroeCard1.setColor("blue");
		heroeCardsService.createHeroeCard(heroeCard1);
		
		assertThat(heroeCardsService.findAll().size()).isEqualTo(2);

	}
	
	@Test 
	public void TestEditHeroeCard() {
		HeroeCard heroecard = heroeCardsService.findById(1).get();
		String oldColor = heroecard.getColor();
		
		String newColor = oldColor + " sky";
		heroecard.setColor(newColor);
		heroeCardsService.createHeroeCard(heroecard);
		
		assertThat(heroeCardsService.findById(heroecard.getId()).get().getColor()).isEqualTo(newColor);

	}
	
	@Test
	public void TestDeleteHeroeCard() {	
		
		HeroeCard heroeCard = new HeroeCard();
		heroeCard.setId(2);
		heroeCard.setName("Salomon");
		heroeCard.setUrl("https:");
		heroeCard.setMaxHealth(2);
		heroeCard.setSkill("skill");
		heroeCard.setColor("blue");
		heroeCardsService.createHeroeCard(heroeCard);
		
		HeroeCard heroe=heroeCardsService.findById(heroeCard.getId()).get();
		
		assertTrue(heroeCardsService.findAll().contains(heroe));
		
		heroeCardsService.deleteHeroeCard(heroeCard);
		assertFalse(heroeCardsService.findAll().contains(heroe));

	}
	
	@Test
	public void TestNewHeroeCard() {	
		Integer Heroes = heroeCardsService.findAll().size();
		
		HeroeCard heroeCard = new HeroeCard();
		heroeCard.setId(2);
		heroeCard.setName("Salomon");
		heroeCard.setUrl("https:");
		heroeCard.setMaxHealth(2);
		heroeCard.setSkill("skill");
		heroeCard.setColor("blue");
		heroeCardsService.createHeroeCard(heroeCard);
		
		Integer newHeroes = heroeCardsService.findAll().size();
		assertFalse(Heroes != newHeroes);
	}
	
	
}

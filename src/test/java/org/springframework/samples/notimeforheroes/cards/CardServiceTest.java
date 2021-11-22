package org.springframework.samples.notimeforheroes.cards;

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
public class CardServiceTest {

	@Autowired
	HeroeCardsService cardService;
	
	@Test
	public void TestNoCard() {
		Collection<HeroeCards> cards = cardService.findAll();
		for(HeroeCards c : cards) {
			cardService.deleteHeroeCard(c);
		}
		assertThat(cardService.findAll().isEmpty()).isTrue();

	}
	
	@Test
	public void TestOneCard() {
		Collection<HeroeCards> cards = cardService.findAll();
		for(HeroeCards c : cards) {
			cardService.deleteHeroeCard(c);
		}		
		HeroeCards card = new HeroeCards();
		card.setId(2);
		card.setName("Salomon");
		card.setUrl("https:");
		cardService.createHeroeCard(card);
		assertThat(cardService.findAll().size()).isEqualTo(1);

	}
	
	@Test
	public void TestMoreThanOneCard() {
		
		Collection<HeroeCards> cards = cardService.findAll();
		for(HeroeCards c : cards) {
			cardService.deleteHeroeCard(c);
		}			
		HeroeCards card = new HeroeCards();
		card.setId(2);
		card.setName("Salomon");
		card.setUrl("https:");
		cardService.createHeroeCard(card);
		
		HeroeCards card1 = new HeroeCards();
		card1.setId(3);
		card1.setName("Salomon2");
		card1.setUrl("https:");
		cardService.createHeroeCard(card1);
		
		assertThat(cardService.findAll().size()).isGreaterThan(1);

	}
	
	@Test 
	public void TestEditCard() {
		HeroeCards card = cardService.findById(1).get();
		String oldName = card.getName();
		
		String newName = oldName + " sky";
		card.setName(newName);
		cardService.createHeroeCard(card);
		
		card = cardService.findById(1).get();
		assertThat(card.getName()).isEqualTo(newName);

	}
	
	@Test
	public void TestDeleteCard() {	
		
		HeroeCards card = new HeroeCards();
		card.setId(2);
		card.setName("Salomon");
		card.setUrl("https:");
		cardService.createHeroeCard(card);
		
		assertTrue(cardService.findAll().contains(card));
		cardService.deleteHeroeCard(card);
		assertFalse(cardService.findAll().contains(card));
	}
	
	@Test
	public void TestNewCard() {	
		
		HeroeCards card = new HeroeCards();
		card.setId(2);
		card.setName("Salomon");
		card.setUrl("https:");
		cardService.createHeroeCard(card);

		assertTrue(cardService.findAll().contains(card));

	}
	
	
}

package org.springframework.samples.notimeforheroes.cards;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CardServiceTest {

	@Autowired
	CardsService cardService;
	
	@Test
	public void TestNoCard() {
		Collection<Cards> cards = cardService.findAll();
		for(Cards c : cards) {
			cardService.deleteCard(c);
		}
		assertThat(cardService.findAll().isEmpty()).isTrue();

	}
	
	@Test
	public void TestOneCard() {
		Collection<Cards> cards = cardService.findAll();
		for(Cards c : cards) {
			cardService.deleteCard(c);
		}		
		Cards card = new Cards();
		card.setColor("Blue");
		card.setDeckid(2);
		card.setExtraglory(15);
		card.setGlory(100);
		card.setGold(100);
		card.setId(2);
		card.setLife(100);
		card.setName("Lisa");
		card.setSkill("Kill");
		card.setType("heroe");
		cardService.createCard(card);
		assertThat(cardService.findAll().size()).isEqualTo(1);

	}
	
	@Test
	public void TestMoreThanOneCard() {
		
		Collection<Cards> cards = cardService.findAll();
		for(Cards c : cards) {
			cardService.deleteCard(c);
		}			
		Cards card = new Cards();
		card.setColor("Blue");
		card.setDeckid(2);
		card.setExtraglory(15);
		card.setGlory(100);
		card.setGold(100);
		card.setId(2);
		card.setLife(100);
		card.setName("Lisa");
		card.setSkill("Kill");
		card.setType("heroe");
		cardService.createCard(card);
		
		Cards card1 = new Cards();
		card1.setColor("Red");
		card1.setDeckid(4);
		card1.setExtraglory(13);
		card1.setGlory(10);
		card1.setGold(10);
		card1.setId(3);
		card1.setLife(100);
		card1.setName("Bill");
		card1.setSkill("Run");
		card1.setType("Heroe");
		cardService.createCard(card1);
		
		assertThat(cardService.findAll().size()).isGreaterThan(1);

	}
	
	@Test 
	public void TestEditCard() {
		Cards card = cardService.findById(1).get();
		String oldColor = card.getColor();
		
		String newColor = oldColor + " sky";
		card.setColor(newColor);
		cardService.createCard(card);
		
		card = cardService.findById(1).get();
		assertThat(card.getColor()).isEqualTo(newColor);

	}
	
	@Test
	public void TestDeleteCard() {	
		
		Cards card = new Cards();
		card.setColor("Blue");
		card.setDeckid(5);
		card.setExtraglory(15);
		card.setGlory(100);
		card.setGold(100);
		card.setId(1);
		card.setLife(100);
		card.setName("Lisa");
		card.setSkill("Kill");
		card.setType("heroe");
		cardService.createCard(card);
		
		assertTrue(cardService.findAll().contains(card));
		cardService.deleteCard(card);
		assertFalse(cardService.findAll().contains(card));
	}
	
	@Test
	public void TestNewCard() {	
		
		Cards card = new Cards();
		card.setColor("Blue");
		card.setDeckid(5);
		card.setExtraglory(15);
		card.setGlory(100);
		card.setGold(100);
		card.setId(1);
		card.setLife(100);
		card.setName("Lisa");
		card.setSkill("Kill");
		card.setType("heroe");
		cardService.createCard(card);

		assertTrue(cardService.findAll().contains(card));

	}
	
	
}

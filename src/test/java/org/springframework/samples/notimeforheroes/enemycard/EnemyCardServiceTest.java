package org.springframework.samples.notimeforheroes.enemycard;

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
import org.springframework.samples.notimeforheroes.enemycard.EnemyCard;
import org.springframework.samples.notimeforheroes.enemycard.EnemyCardService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EnemyCardServiceTest {

	@Autowired
	EnemyCardService enemyCardService;
	
	@Test
	public void TestNoEnemyCard() {
		Collection<EnemyCard> enemyCard = enemyCardService.findAll();
		for(EnemyCard c : enemyCard) {
			enemyCardService.deleteEnemyCard(c);
		}
		assertThat(enemyCardService.findAll().isEmpty()).isTrue();

	}
	
	@Test
	public void testOneEnemyCard() {
		Collection<EnemyCard> enemyCards= enemyCardService.findAll();
		for(EnemyCard c : enemyCards) {
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
	public void TestMoreThanOneEnemyCard() {
		
		Collection<EnemyCard> enemyCards= enemyCardService.findAll();
		for(EnemyCard c : enemyCards) {
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
	public void TestEditEnemyCard() {
		EnemyCard enemycard = enemyCardService.findById(1).get();
		Integer oldExtraglory = enemycard.getExtraGlory();
		
		Integer newExtraglory = oldExtraglory + 1;
		enemycard.setExtraGlory(newExtraglory);
		enemyCardService.createEnemyCard(enemycard);
		
		assertThat(enemyCardService.findById(enemycard.getId()).get().getExtraGlory()).isEqualTo(newExtraglory);

	}
	
	@Test
	public void TestDeleteEnemyCard() {	
		
		EnemyCard enemyCard = new EnemyCard();
		enemyCard.setExtraGlory(1);
		enemyCard.setExtraGold(0);
		enemyCard.setGlory(2);
		enemyCard.setMaxHealth(4);
		enemyCard.setName("Enemigo");
		enemyCard.setUrl(".");
		enemyCardService.createEnemyCard(enemyCard);
		
		EnemyCard enemy=enemyCardService.findById(enemyCard.getId()).get();
		
		assertTrue(enemyCardService.findAll().contains(enemy));
		
		enemyCardService.deleteEnemyCard(enemyCard);
		assertFalse(enemyCardService.findAll().contains(enemy));

	}
	
	@Test
	public void TestNewEnemyCard() {	
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
	
	
}

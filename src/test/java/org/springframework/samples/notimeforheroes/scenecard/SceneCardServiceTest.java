package org.springframework.samples.notimeforheroes.scenecard;

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
public class SceneCardServiceTest {
	
	@Autowired 
	SceneCardsService sceneService;
	
	@Test
	public void TestNoSceneCard() {
		Collection<SceneCards> scenes = sceneService.findAll();
		for(SceneCards c: scenes) {
			sceneService.deleteSceneCard(c);
		}	
		assertThat(sceneService.findAll().isEmpty()).isTrue();
	}
	
	@Test
	public void TestOneSceneCard() {
		Collection<SceneCards> SceneCards = sceneService.findAll();
		for(SceneCards c : SceneCards) {
			sceneService.deleteSceneCard(c);
		}		
		SceneCards sceneCard = new SceneCards();
		sceneCard.setName("Battlefield");
		sceneCard.setUrl("https:");
		sceneCard.setDescription("description");
		sceneService.saveSceneCard(sceneCard);
		assertThat(sceneService.findAll().size()).isEqualTo(1);

	}
	
	@Test
	public void TestMoreThanOneSceneCard() {
		
		Collection<SceneCards> SceneCards = sceneService.findAll();
		for(SceneCards c : SceneCards) {
			sceneService.deleteSceneCard(c);
		}		
		SceneCards sceneCard = new SceneCards();
		sceneCard.setName("Battlefield");
		sceneCard.setId(2);
		sceneCard.setUrl("https:");
		sceneCard.setDescription("description");
		sceneService.saveSceneCard(sceneCard);
		
		SceneCards sceneCard2 = new SceneCards();
		sceneCard2.setName("Battlefield2");
		sceneCard.setId(3);
		sceneCard2.setUrl("https:");
		sceneCard2.setDescription("description2");
		sceneService.saveSceneCard(sceneCard);
		
		assertThat(sceneService.findAll().size()).isGreaterThan(1);

	}
	
	@Test 
	public void TestEditSceneCard() {
		SceneCards SceneCard = sceneService.findById(1).get();
		String oldName = SceneCard.getName();
		
		String newName = oldName + " sky";
		SceneCard.setName(newName);
		sceneService.saveSceneCard(SceneCard);
		
		assertThat(SceneCard.getName()).isEqualTo(newName);

	}
	
	@Test
	public void TestDeleteSceneCard() {	
		
		SceneCards sceneCard = new SceneCards();
		sceneCard.setName("Battlefield");
		sceneCard.setUrl("https:");
		sceneCard.setDescription("description");
		sceneService.saveSceneCard(sceneCard);
		
		SceneCards scene = sceneService.findById(sceneCard.getId()).get();
		assertTrue(sceneService.findAll().contains(scene));
		
		sceneService.deleteSceneCard(sceneCard);
		assertFalse(sceneService.findAll().contains(scene));

	}
	
	
	@Test
	public void TestNewSceneCard() {	
		Integer scene = sceneService.findAll().size();
		
		SceneCards sceneCard = new SceneCards();
		sceneCard.setName("Battlefield");
		sceneCard.setUrl("https:");
		sceneCard.setDescription("description");
		sceneService.saveSceneCard(sceneCard);
		
		Integer newScene = sceneService.findAll().size();
		assertTrue(scene != newScene);
	}

}

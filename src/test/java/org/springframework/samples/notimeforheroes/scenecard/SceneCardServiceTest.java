package org.springframework.samples.notimeforheroes.scenecard;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.notimeforheroes.cards.scenecard.SceneCard;
import org.springframework.samples.notimeforheroes.cards.scenecard.SceneCardsService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class SceneCardServiceTest {
	
	@Autowired 
	SceneCardsService sceneService;
	
	@Test
	public void TestFindAllSceneCard() {
		Integer AllSceneCards = sceneService.findAll().size();
		
		SceneCard scene = NewScene("Scena", "http", "Description", true);
		sceneService.saveSceneCard(scene);
		
		Integer NewAllSceneCards = sceneService.findAll().size();
		
		assertTrue(NewAllSceneCards == AllSceneCards+1);
	}
	
	@Test 
	public void TestFindByIdSceneCard() {
		SceneCard scene = NewScene("Scena", "http", "Description", true);
		sceneService.saveSceneCard(scene);
		assertTrue(sceneService.findById(scene.getId()).orElse(null).equals(scene));
	}
	
	@Test
	public void TestDeleteSceneCard() {	
		
		SceneCard sceneCard = new SceneCard();
		sceneCard.setName("Battlefield");
		sceneCard.setUrl("https:");
		sceneCard.setDescription("description");
		sceneService.saveSceneCard(sceneCard);
		
		SceneCard scene = sceneService.findById(sceneCard.getId()).get();
		assertTrue(sceneService.findAll().contains(scene));
		
		sceneService.deleteSceneCard(sceneCard);
		assertFalse(sceneService.findAll().contains(scene));

	}
	
	
	@Test
	public void TestNewSceneCard() {	
		Integer scene = sceneService.findAll().size();
		
		SceneCard sceneCard = new SceneCard();
		sceneCard.setName("Battlefield");
		sceneCard.setUrl("https:");
		sceneCard.setDescription("description");
		sceneService.saveSceneCard(sceneCard);
		
		Integer newScene = sceneService.findAll().size();
		assertTrue(scene != newScene);
	}

	private SceneCard NewScene(String name, String url, String description, boolean inGame) {
		SceneCard scene = new SceneCard();
		scene.setDescription(description);
		scene.setName(name);
		scene.setUrl(url);
		scene.setInGame(inGame);
		return scene;
	}
	
}

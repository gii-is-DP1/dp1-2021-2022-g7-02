package org.springframework.samples.notimeforheroes.cards.scenecard;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SceneCardsService {

	@Autowired
	SceneCardsRepository SceneRepository;
	
	@Transactional
	public Collection<SceneCard> findAll(){
		return SceneRepository.findAll();
	}
	
	@Transactional
	public Optional<SceneCard> findById(Integer id){
		return SceneRepository.findById(id);
	}
	
	@Transactional
	public void saveSceneCard(@Valid SceneCard scene) {
		SceneRepository.save(scene);
	}
	
	@Transactional
	public void deleteSceneCard(SceneCard scene) {
		SceneRepository.delete(scene);
	}
	
}

package org.springframework.samples.notimeforheroes.scenecard;

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
	public Collection<SceneCards> findAll(){
		return SceneRepository.findAll();
	}
	
	@Transactional
	public Optional<SceneCards> findById(Integer id){
		return SceneRepository.findById(id);
	}
	
	@Transactional
	public void saveSceneCard(@Valid SceneCards scene) {
		SceneRepository.save(scene);
	}
	
	@Transactional
	public void deleteSceneCard(SceneCards scene) {
		SceneRepository.delete(scene);
	}
	
}

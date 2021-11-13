package org.springframework.samples.notimeforheroes.achievements;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AchievementService {

	@Autowired
	AchievementRepository achievementsRepo;
	
	
	@Transactional
	public Collection<Achievement> findAll(){
		return achievementsRepo.findAll();
	}
	
	@Transactional
	public Optional<Achievement> findById(Integer id){
		return achievementsRepo.findById(id);
	}
	
	@Transactional
	public void deleteAchievement(Achievement achievement) {
		achievementsRepo.deleteById(achievement.getId());
	}
	
	@Transactional
	public void createAchievement(@Valid Achievement achievement) {
		achievementsRepo.save(achievement);
	}
	
}

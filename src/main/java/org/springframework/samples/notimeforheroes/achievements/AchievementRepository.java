package org.springframework.samples.notimeforheroes.achievements;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface AchievementRepository extends CrudRepository<Achievement, Integer>{

	
	Collection<Achievement> findAll();
	

	
}

package org.springframework.samples.notimeforheroes.achievements;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface AchievementRepository extends CrudRepository<Achievement, Integer>{

	Page<Achievement> findAll(Pageable page);	
	
	Collection<Achievement> findAll();
	
}

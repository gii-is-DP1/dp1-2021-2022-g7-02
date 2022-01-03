package org.springframework.samples.notimeforheroes.achievements;

import java.util.Collection;

import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AchievementRepository extends CrudRepository<Achievement, Integer>{

	
	Collection<Achievement> findAll();

	//@Query("SELECT a FROM achievement_user a WHERE a.user_id == ?1")
	Collection<Achievement> findByUser(Integer userId);
}

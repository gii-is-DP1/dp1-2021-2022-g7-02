package org.springframework.samples.notimeforheroes.skillcard;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface SkillCardsRepository extends CrudRepository<SkillCard, Integer>{
	
	Collection<SkillCard> findAll();

}

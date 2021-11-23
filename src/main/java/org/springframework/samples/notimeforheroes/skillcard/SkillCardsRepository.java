package org.springframework.samples.notimeforheroes.skillcard;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface SkillCardsRepository extends CrudRepository<SkillCards, Integer>{
	
	Collection<SkillCards> findAll();

}

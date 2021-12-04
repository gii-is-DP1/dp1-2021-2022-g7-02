package org.springframework.samples.notimeforheroes.skillcard;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillCardsService {
	
	@Autowired
	SkillCardsRepository SkillRepository;
	
	@Transactional
	public Collection<SkillCard> findAll(){
		return SkillRepository.findAll();
	}
	
	@Transactional
	public Optional<SkillCard> findById(Integer id){
		return SkillRepository.findById(id);
	}
	
	@Transactional
	public void saveSkillCard(@Valid SkillCard skill) {
		SkillRepository.save(skill);
	}
	
	@Transactional
	public void deleteSkillCard(SkillCard skill) {
		SkillRepository.delete(skill);
	}

}

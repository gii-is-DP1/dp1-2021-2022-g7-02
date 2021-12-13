package org.springframework.samples.notimeforheroes.skillcard;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.stereotype.Service;

@Service
public class SkillCardsService {
	
	@Autowired
	SkillCardsRepository skillRepository;
	
	@Transactional
	public Collection<SkillCard> findAll(){
		return skillRepository.findAll();
	}
	
	@Transactional
	public Optional<SkillCard> findById(Integer id){
		return skillRepository.findById(id);
	}
	
	@Transactional
	public Collection<SkillCard> findByColor(String color){
		return skillRepository.findByColor(color);
	}

	public Collection<SkillCard> findByGameAndUser(Game game, User user){
		return skillRepository.findAllSkillsByGameAndUser(game, user);
	}

	public Collection<SkillCard> findAllAvailableSkillsByGameAndUser(Game game, User user){
		return skillRepository.findAllAvailableSkillsByGameAndUser(game, user);
	}
	
	@Transactional
	public void saveSkillCard(@Valid SkillCard skill) {
		skillRepository.save(skill);
	}
	
	@Transactional
	public void deleteSkillCard(SkillCard skill) {
		skillRepository.delete(skill);
	}

}

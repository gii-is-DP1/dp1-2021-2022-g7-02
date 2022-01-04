package org.springframework.samples.notimeforheroes.cards.skillcard;

import java.util.Collection;
import java.util.Optional;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyCard;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyCardService;
import org.springframework.samples.notimeforheroes.cards.enemycard.gamesEnemies.GamesEnemiesService;
import org.springframework.samples.notimeforheroes.cards.skillcard.gamesUsersSkillcards.GamesUsersSkillCards;
import org.springframework.samples.notimeforheroes.cards.skillcard.gamesUsersSkillcards.GamesUsersSkillCardsService;
import org.springframework.samples.notimeforheroes.cards.skillcard.gamesUsersSkillcards.SkillState;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.stereotype.Service;

@Service
public class SkillCardsService {
	
	@Autowired
	SkillCardsRepository skillRepository;

	@Autowired
	GamesUsersSkillCardsService gamesUsersSkillCardsService;

	@Autowired
	EnemyCardService enemyCardService;

	@Autowired
	GamesEnemiesService gamesEnemiesService;
	
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

	public List<SkillCard> findAllAvailableSkillsByGameAndUser(Game game, User user){
		return skillRepository.findAllAvailableSkillsByGameAndUser(game, user);
	}

	public List<SkillCard> findAllOnDeckSkillsByGameAndUser(Game game, User user){
		return skillRepository.findAllOnDeckSkillsByGameAndUser(game, user);
	}

	public List<SkillCard> findAllOnDiscardedSkillsByGameAndUser(Game game, User user){
		return skillRepository.findAllOnDiscardedSkillsByGameAndUser(game, user);
	}
	
	
	public Integer numberOfEnemiesOfSkillCard(SkillCard skillCard) throws Exception{
		Integer skillCardId = skillCard.getId();
		List<Integer> noEnemyRequiredIdList = List.of(13,14,20,21,26,27,29,30,39,43,56,57,58);
		List<Integer> oneEnemyRequiredIdList = List.of(1,2,3,4,5,6,7,8,9,10,15,16,17,
										18,19,22,23,24,25,28,32,33,34,35,36,37,38,40,41,42,44,45,46,47,48,49,50,51,52,53,54,55);
		List<Integer> twoEnemiesRequiredIdList = List.of(11,12);
		List<Integer> threeEnemiesRequiredIdList = List.of(31);

		if(noEnemyRequiredIdList.contains(skillCardId))
			return 0;
		else if(oneEnemyRequiredIdList.contains(skillCardId)){
			return 1;
		}else if(twoEnemiesRequiredIdList.contains(skillCardId)){
			return 2;
		}else if(threeEnemiesRequiredIdList.contains(skillCardId)){
			return 3;
		}else{
			throw new Exception("No se pudieron procesar los enemigos necesarios de la carta");
		}
	}
	
	@Transactional
	public void useDisparoRápido(List<EnemyCard> enemiesTargetedList, Game game, User user, SkillCard skillCard){
		enemiesTargetedList.forEach(enemy -> {
			try {
				gamesEnemiesService.damageEnemy(game, enemy, user, 3);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		gamesUsersSkillCardsService.useSkill(game, user, skillCard);
		List<SkillCard> skillCards = findAllOnDeckSkillsByGameAndUser(game, user);
		SkillCard newSkill = skillCards.get(0);
		GamesUsersSkillCards newSkillGame = gamesUsersSkillCardsService.findByGameUserSkill(game, user, newSkill).get();
		if(newSkill.getName().equals(skillCard.getName())){	//Si la carta robada es un disparo rápido
			newSkillGame.setSkillState(SkillState.ONHAND);
			gamesUsersSkillCardsService.createGameUserSkillCard(newSkillGame);
			System.out.println("[DEBUG]: SE HA ROBADO UNA CARTA Disparo Rápido");
		}

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

package org.springframework.samples.notimeforheroes.achievements;


import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.notimeforheroes.achievements.exceptions.DuplicatedAchievementNameException;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUser;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUserService;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class AchievementService {

	@Autowired
	AchievementRepository achievementsRepo;

	@Autowired
	UserService userService;

	@Autowired
	GameUserService gameUserService;
	
	@Transactional
	public Collection<Achievement> findAll(){
		return achievementsRepo.findAll();
	}
	
	@Transactional
	public Optional<Achievement> findById(Integer id){
		return achievementsRepo.findById(id);
	}

	@Transactional
	public Collection<Achievement> findByUser(Integer userId){
		return achievementsRepo.findByUser(userId);
	}
	
	@Transactional
	public void deleteAchievement(Achievement achievement) {
		achievementsRepo.deleteById(achievement.getId());
	}
	
	@Transactional
	public void createAchievement(@Valid Achievement achievement) {
		achievementsRepo.save(achievement);
	}
	
	
	@Transactional(rollbackOn = DuplicatedAchievementNameException.class)
	public void saveAchievement(Achievement achievement) throws DataAccessException,DuplicatedAchievementNameException { 
			List<String> names=new ArrayList<String>();
			List<Achievement> achievements= (List<Achievement>) this.findAll();
			for (int i=0; i<achievements.size(); i++) {
				names.add(achievements.get(i).getName());
				if(!achievements.get(i).getId().equals(achievement.getId()))
					names.add(achievements.get(i).getName());
			}
            if (names.contains(achievement.getName())) {            	
            	throw new DuplicatedAchievementNameException();
            }else
                achievementsRepo.save(achievement);                
	}


	public 	List<Achievement> getAchievements(User user){

		Collection<GameUser> gamesUser = gameUserService.findAllGamesbyUser(user);
		List<Achievement> newAchievements = new ArrayList<Achievement>();
		//Collection<Achievement> oldAchievements = findByUser(user);

		Integer partidasJugadas = gamesUser.size();
		Integer partidasGanadas = 0;
		for(GameUser  g: gamesUser){
			if(g.getWinner()==true){
				partidasGanadas++;
			}
		}
/*
		if (partidasJugadas >= 1 && !oldAchievements.contains(((List<Achievement>) findAll()).get(0))){
			newAchievements.add(((List<Achievement>) findAll()).get(0));
		}
		
		if (partidasJugadas >= 5 && !oldAchievements.contains(((List<Achievement>) findAll()).get(1))){
			newAchievements.add(((List<Achievement>) findAll()).get(1));
		}
*/
	return newAchievements;

	}

		
	
}

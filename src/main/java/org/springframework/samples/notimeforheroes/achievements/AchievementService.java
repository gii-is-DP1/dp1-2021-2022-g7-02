package org.springframework.samples.notimeforheroes.achievements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.notimeforheroes.achievements.exceptions.DuplicatedAchievementNameException;
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCardsService;
import org.springframework.samples.notimeforheroes.game.GameService;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUserService;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class AchievementService {

	@Autowired
	AchievementRepository achievementsRepo;
	
	@Autowired
	GameService gameService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	HeroeCardsService heroeService;
	
	@Autowired
	GameUserService gameUserService;
	
	
	@Transactional
	public Collection<Achievement> findAllPage(Integer pageNo, Integer pageSize) {
		Pageable pagin = PageRequest.of(pageNo, pageSize);
		Page<Achievement> pageResult = achievementsRepo.findAll(pagin);
		if (pageResult.hasContent()) {
			return pageResult.getContent();
		} else {
			return new ArrayList<Achievement>();
		}
	}
	
	@Transactional
	public Collection<Achievement> findAll() {
		return achievementsRepo.findAll();
	}

	//
	@Transactional
	public void deleteAchievement(Achievement achievement) {
		achievementsRepo.deleteById(achievement.getId());
	}

	//
	@Transactional(rollbackOn = DuplicatedAchievementNameException.class)
	public void saveAchievement(Achievement achievement)
			throws DataAccessException, DuplicatedAchievementNameException {
		List<String> names = new ArrayList<String>();
		List<Achievement> achievements = (List<Achievement>) this.findAll();
		for (int i = 0; i < achievements.size(); i++) {
			names.add(achievements.get(i).getName());
			if (!achievements.get(i).getId().equals(achievement.getId()))
				names.add(achievements.get(i).getName());
		}
		if (names.contains(achievement.getName())) {
			throw new DuplicatedAchievementNameException();
		} else
			achievementsRepo.save(achievement);
	}
	
	public List<Boolean> achievedAchievement(Integer pageNo, Integer pageSize){ 
			Pageable pagin = PageRequest.of(pageNo, pageSize);
			List<Achievement> achievements = new ArrayList<Achievement>();
			Page<Achievement> pageResult = achievementsRepo.findAll(pagin);
			achievements.addAll(pageResult.getContent());
			List<Boolean> res=new ArrayList<Boolean>();
            for(int i=0;i<achievements.size(); i++) {
                AchievementType type=achievements.get(i).getType();
                int number=achievements.get(i).getNumberAchievement();
                Boolean test= false;
                
                if(type==AchievementType.PLAY) {
                	int games=gameService.findByUser(userService.getLoggedUser()).size();
                	if(number<=games) {
                		test=true;
                	}
                }
                if(type==AchievementType.WIN) {
                  	int gamesWin=gameService.findByWinner(userService.getLoggedUser()).size();
                	if(number<=gamesWin) {
                		test=true;
                	}
                }
                if(type==AchievementType.HEROE1) {
                  	int heroeUse=gameUserService.getAllUsesOfaHeroeByUser(userService.getLoggedUser(),1);
                	if(number<=heroeUse) {
                		test=true;
                	}
                }
                if(type==AchievementType.HEROE2) {
                  	int heroeUse=gameUserService.getAllUsesOfaHeroeByUser(userService.getLoggedUser(),2);
                	if(number<=heroeUse) {
                		test=true;
                	}
                }
                if(type==AchievementType.HEROE3) {
                  	int heroeUse=gameUserService.getAllUsesOfaHeroeByUser(userService.getLoggedUser(),3);
                	if(number<=heroeUse) {
                		test=true;
                	}
                }
                if(type==AchievementType.HEROE4) {
                  	int heroeUse=gameUserService.getAllUsesOfaHeroeByUser(userService.getLoggedUser(),4);
                	if(number<=heroeUse) {
                		test=true;
                	}
                }
                if(type==AchievementType.HEROE5) {
                  	int heroeUse=gameUserService.getAllUsesOfaHeroeByUser(userService.getLoggedUser(),5);
                	if(number<=heroeUse) {
                		test=true;
                	}
                }
                if(type==AchievementType.HEROE6) {
                  	int heroeUse=gameUserService.getAllUsesOfaHeroeByUser(userService.getLoggedUser(),6);
                	if(number<=heroeUse) {
                		test=true;
                	}
                }
                if(type==AchievementType.HEROE7) {
                  	int heroeUse=gameUserService.getAllUsesOfaHeroeByUser(userService.getLoggedUser(),7);
                	if(number<=heroeUse) {
                		test=true;
                	}
                }
                if(type==AchievementType.HEROE8) {
                  	int heroeUse=gameUserService.getAllUsesOfaHeroeByUser(userService.getLoggedUser(),8);
                	if(number<=heroeUse) {
                		test=true;
                	}
                }
                if(type==AchievementType.GOLD) {
                	int gold=gameUserService.getAllGoldByUser(userService.getLoggedUser());
                	if(number<=gold) {
                		test=true;
                	}
                }
                if(type==AchievementType.GLORY) {
                	int glory=gameUserService.getAllGloryByUser(userService.getLoggedUser());
                	if(number<=glory) {
                		test=true;
                	}
                }
        		res.add(test);
            }
            return res;
	}
}

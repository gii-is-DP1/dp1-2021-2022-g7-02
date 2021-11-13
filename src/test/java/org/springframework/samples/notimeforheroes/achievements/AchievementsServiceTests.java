package org.springframework.samples.notimeforheroes.achievements;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.notimeforheroes.achievements.exceptions.DuplicatedAchievementNameException;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AchievementsServiceTests {

	@Autowired
	private AchievementService achievementsService;
	
	@Test
	public void testNoAchievement(){
		Collection<Achievement> achievements=achievementsService.findAll();
		achievements.clear();
		assertThat(achievements.isEmpty()).isTrue();
	}
	
	@Test
	public void testOneAchievement(){
		Collection<Achievement> achievements=achievementsService.findAll();
		achievements.clear();
		
		Achievement achievement= new Achievement();
		achievement.setName("Beginner");
		achievement.setDescription("Play 5 games");
		achievements.add(achievement);
		assertThat(achievements.size()).isEqualTo(1);
	}
	
	@Test
	public void testMoreThanOneAchievements(){
		Collection<Achievement> achievements=achievementsService.findAll();
		achievements.clear();
		
		Achievement  achievement= new Achievement();
		achievement.setName("Beginner");
		achievement.setDescription("Play 5 games");
		achievements.add(achievement);
		
		Achievement  achievement1= new Achievement();
		achievement1.setName("Amateur");
		achievement1.setDescription("Play 10 games");
		achievements.add(achievement1);
		
		assertThat(achievements.size()).isGreaterThan(1);
	}

	@Test
	public void testEditAchievement(){
		Achievement achievement = achievementsService.findById(1).get();
		String oldName = achievement.getName();

		String newName = oldName + "X";
		achievement.setName(newName);
		achievementsService.createAchievement(achievement);

		achievement = achievementsService.findById(1).get();
		assertThat(achievement.getName()).isEqualTo(newName);
	}
	


	@Test
	public void testEditAchievementSameName() throws DuplicatedAchievementNameException{
		String name = achievementsService.findById(1).get().getName();

		Achievement achievement= new Achievement();
		achievement.setName(name);
		achievement.setDescription("Play 10 games");
	
				
		Assertions.assertThrows(DuplicatedAchievementNameException.class, () ->{
			achievementsService.saveAchievement(achievement);
		});	

		
	}
}

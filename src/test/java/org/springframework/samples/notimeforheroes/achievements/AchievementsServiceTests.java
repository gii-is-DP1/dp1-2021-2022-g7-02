package org.springframework.samples.notimeforheroes.achievements;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.notimeforheroes.achievements.exceptions.DuplicatedAchievementNameException;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AchievementsServiceTests {

	@Autowired 
	private AchievementService achievementsService;


	
	@Test
	void testFindAll() throws DataAccessException, DuplicatedAchievementNameException {
		Integer achievementN = achievementsService.findAll().size();
		System.out.println(achievementN);
		Achievement achievement = new Achievement();
		achievement.setName("Prueba");
		achievement.setDescription("Play 5 games");
		achievement.setNumberAchievement(5);
		achievement.setType(AchievementType.PLAY);
		achievementsService.saveAchievement(achievement);
		Integer achievementN1 = achievementsService.findAll().size();
		assertTrue(achievementN1 == achievementN + 1);
	}

	@Test
	void testNoAchievement() {
		Collection<Achievement> achievements = achievementsService.findAll();
		for (Achievement ac : achievements) {
			achievementsService.deleteAchievement(ac);
		}
		assertThat(achievementsService.findAll().isEmpty()).isTrue();
	}

	@Test
	void testOneAchievement() throws DataAccessException, DuplicatedAchievementNameException {
		Collection<Achievement> achievements = achievementsService.findAll();
		for (Achievement ac : achievements) {
			achievementsService.deleteAchievement(ac);
		}

		Achievement achievement = new Achievement();
		achievement.setName("Prueba");
		achievement.setDescription("Play 5 games");
		achievement.setNumberAchievement(5);
		achievement.setType(AchievementType.PLAY);
		achievementsService.saveAchievement(achievement);
		assertThat(achievementsService.findAll().size()).isEqualTo(1);
	}

	@Test
	void testMoreThanOneAchievements() throws DataAccessException, DuplicatedAchievementNameException {
		Collection<Achievement> achievements = achievementsService.findAll();

		for (Achievement ac : achievements) {
			achievementsService.deleteAchievement(ac);
		}

		Achievement achievement = new Achievement();
		achievement.setName("Prueba");
		achievement.setDescription("Play 5 games");
		achievement.setNumberAchievement(5);
		achievement.setType(AchievementType.PLAY);
		achievementsService.saveAchievement(achievement);

		Achievement achievement1 = new Achievement();
		achievement1.setName("Prueba1");
		achievement1.setDescription("Play 10 games");
		achievement1.setNumberAchievement(10);
		achievement1.setType(AchievementType.PLAY);
		achievementsService.saveAchievement(achievement1);

		assertThat(achievementsService.findAll().size()).isGreaterThan(1);
	}


	@Test
	public void testSaveAchievementSameName() throws DuplicatedAchievementNameException {
		List<Achievement> achievements = (List<Achievement>) achievementsService.findAll();

		String name = achievements.get(0).getName();

		Achievement achievement = new Achievement();
		achievement.setName(name);
		achievement.setDescription("Play 10 games");
		achievement.setNumberAchievement(10);
		achievement.setType(AchievementType.PLAY);

		Assertions.assertThrows(DuplicatedAchievementNameException.class, () -> {
			achievementsService.saveAchievement(achievement);
		});

	}
}

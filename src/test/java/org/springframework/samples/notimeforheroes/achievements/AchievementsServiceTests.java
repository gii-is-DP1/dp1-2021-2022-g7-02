package org.springframework.samples.notimeforheroes.achievements;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
		Collection<Achievement> achievementsFinal = achievementsService.findAll();
		assertTrue(achievementsFinal.size() == achievementN + 1);
		assertTrue(achievementsFinal.contains(achievement));
	}

	@Test
	void testFindPage() throws DataAccessException, DuplicatedAchievementNameException{
		Achievement achievement1 = new Achievement();
		achievement1.setName("Ach 1");
		achievement1.setDescription("Ach 1");
		achievement1.setNumberAchievement(1);
		achievement1.setType(AchievementType.PLAY);
		achievementsService.saveAchievement(achievement1);

		Achievement achievement2 = new Achievement();
		achievement2.setName("Ach 2");
		achievement2.setDescription("Ach 2");
		achievement2.setNumberAchievement(2);
		achievement2.setType(AchievementType.PLAY);
		achievementsService.saveAchievement(achievement2);

		Achievement achievement3 = new Achievement();
		achievement3.setName("Ach 3");
		achievement3.setDescription("Ach 3");
		achievement3.setNumberAchievement(3);
		achievement3.setType(AchievementType.PLAY);
		achievementsService.saveAchievement(achievement3);

		assertThat(achievementsService.findAllPage(0, 2).size()).isEqualTo(2);
	}

	@Test
	void testNoAchievement() throws DataAccessException, DuplicatedAchievementNameException {
		Achievement achievement1 = new Achievement();achievement1.setName("Ach 1");achievement1.setDescription("Ach 1");achievement1.setNumberAchievement(1);achievement1.setType(AchievementType.PLAY);achievementsService.saveAchievement(achievement1);
		for (Achievement ac : achievementsService.findAll()) {
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

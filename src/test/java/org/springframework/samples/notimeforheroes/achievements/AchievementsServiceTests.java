package org.springframework.samples.notimeforheroes.achievements;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	void testFindAll() {
		Integer achievement = achievementsService.findAll().size();
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
	void testOneAchievement() {
		Collection<Achievement> achievements = achievementsService.findAll();
		for (Achievement ac : achievements) {
			achievementsService.deleteAchievement(ac);
		}

		Achievement achievement = new Achievement();
		achievement.setName("Beginner");
		achievement.setDescription("Play 5 games");
		achievementsService.createAchievement(achievement);
		assertThat(achievementsService.findAll().size()).isEqualTo(1);
	}

	@Test
	void testMoreThanOneAchievements() {
		Collection<Achievement> achievements = achievementsService.findAll();

		for (Achievement ac : achievements) {
			achievementsService.deleteAchievement(ac);
		}

		Achievement achievement = new Achievement();
		achievement.setName("Beginner");
		achievement.setDescription("Play 5 games");
		achievementsService.createAchievement(achievement);

		Achievement achievement1 = new Achievement();
		achievement1.setName("Amateur");
		achievement1.setDescription("Play 10 games");
		achievementsService.createAchievement(achievement1);

		assertThat(achievementsService.findAll().size()).isGreaterThan(1);
	}

	@Test
	void testNewAchievement() {
		Achievement achievement = new Achievement();
		achievement.setName("Beginner");
		achievement.setDescription("Play 5 games");
		achievementsService.createAchievement(achievement);
		Collection<Achievement> achievementsInDatabase = achievementsService.findAll();

		//
		assertTrue(achievementsInDatabase.contains(achievement));

		achievementsService.deleteAchievement(achievement);
		achievementsInDatabase = achievementsService.findAll();

		//
		assertFalse(achievementsInDatabase.contains(achievement));
	}

	@Test
	void testDeleteAchievement() {
		Achievement achievement = new Achievement();
		achievement.setName("Beginner");
		achievement.setDescription("Play 5 games");
		achievementsService.createAchievement(achievement);

		assertTrue(achievementsService.findAll().contains(achievement));
		achievementsService.deleteAchievement(achievement);
		assertFalse(achievementsService.findAll().contains(achievement));

	}

	@Test
	public void testEditAchievement() {
		Achievement achievement = achievementsService.findById(1).get();
		String oldName = achievement.getName();

		String newName = oldName + "X";
		achievement.setName(newName);
		achievementsService.createAchievement(achievement);

		achievement = achievementsService.findById(1).get();
		assertThat(achievement.getName()).isEqualTo(newName);
	}

	@Test
	void testFindById() {

		Achievement achievement = new Achievement();
		achievement.setName("Beginner");
		achievement.setDescription("Play 5 games");
		achievementsService.createAchievement(achievement);

		Achievement achievement2 = new Achievement();
		achievement2.setName("Beginner2");
		achievement2.setDescription("Play 10 games");
		achievementsService.createAchievement(achievement2);

		//
		assertTrue(achievementsService.findById(achievement.getId()).get().equals(achievement));

		//
		assertFalse(achievementsService.findById(achievement.getId()).get().equals(achievement2));
	}

	@Test
	public void testEditAchievementSameName() throws DuplicatedAchievementNameException {
		String name = achievementsService.findById(1).get().getName();

		Achievement achievement = new Achievement();
		achievement.setName(name);
		achievement.setDescription("Play 10 games");

		Assertions.assertThrows(DuplicatedAchievementNameException.class, () -> {
			achievementsService.saveAchievement(achievement);
		});

	}
}

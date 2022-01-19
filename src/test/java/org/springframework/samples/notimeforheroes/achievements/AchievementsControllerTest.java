package org.springframework.samples.notimeforheroes.achievements;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.notimeforheroes.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = AchievementController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class AchievementsControllerTest {

	private static final int TEST_ACHIEVEMENT_ID = 17;

    @Autowired
	private MockMvc mockMvc;


    @MockBean
	private AuthoritiesService authoritiesService;


    private Achievement achievement;

	@BeforeEach
	void setup(){

		achievement = new Achievement();
		achievement.setName("Goblin");
		achievement.setId(TEST_ACHIEVEMENT_ID);
		achievement.setDescription("Obtain 10 gold coins");
		achievement.setType(AchievementType.GOLD);
		achievement.setNumberAchievement(10);
	}


    @WithMockUser(value = "spring")
    @Test
    void testShowAchievement() throws Exception{
        mockMvc.perform(get("/achievements", TEST_ACHIEVEMENT_ID)).andExpect(status().isOk())
		         .andExpect(model().attribute("achievement", hasProperty("name", is("Goblin"))))
                 .andExpect(model().attribute("achievement", hasProperty("description", is("Obtain 10 gold coins"))))
                 .andExpect(model().attribute("achievement", hasProperty("numberAchievement", is("10"))))
                 .andExpect(model().attribute("achievement", hasProperty("type", is("10"))))
                 .andExpect(view().name("achievements/achievementsListing"));

    }

}

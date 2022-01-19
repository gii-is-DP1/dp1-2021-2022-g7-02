package org.springframework.samples.notimeforheroes.enemycard;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyCard;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyCardController;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyCardService;
import org.springframework.samples.notimeforheroes.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = EnemyCardController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
class EnemyCardControllerTest {


	private static final int TEST_ENEMY_ID = 17;

    @Autowired
	private MockMvc mockMvc;

    @MockBean
    private EnemyCardService enemyCardService; 

    @MockBean
	private AuthoritiesService authoritiesService;



    private EnemyCard enemyCard;

	@BeforeEach
	void setup(){
        enemyCard =  new EnemyCard();
		enemyCard.setName("Classic");
		enemyCard.setId(TEST_ENEMY_ID);
		enemyCard.setUrl("url");
        enemyCard.setMaxHealth(10);
        enemyCard.setExtraGlory(5);
        enemyCard.setGlory(7);
        enemyCard.setHealthInGame(7);
        enemyCard.setIsBoss(true);
        enemyCard.setExtraGold(4);
        given(this.enemyCardService.findById(TEST_ENEMY_ID)).willReturn(Optional.of(enemyCard));
	}


    @WithMockUser(value = "spring")
    @Test
    void testShowEnemyCard() throws Exception{
        mockMvc.perform(get("/cards/enemies", TEST_ENEMY_ID)).andExpect(status().isOk())
		        .andExpect(model().attribute("enemies", hasProperty("name", is("Classic"))))
                .andExpect(model().attribute("enemies", hasProperty("url", is("url"))))
                .andExpect(model().attribute("enemies", hasProperty("maxHealth", is("10"))))
                .andExpect(model().attribute("enemies", hasProperty("extraGlory", is("7"))))
                .andExpect(model().attribute("enemies", hasProperty("extraGold", is("4"))))
                .andExpect(model().attribute("enemies", hasProperty("glory", is("7"))))
                .andExpect(model().attribute("enemies", hasProperty("healthInGame", is("7"))))
                .andExpect(model().attribute("enemies", hasProperty("isBoss", is("true"))))
                .andExpect(view().name("cards/enemies/enemiesCardsListing"));
    }
}

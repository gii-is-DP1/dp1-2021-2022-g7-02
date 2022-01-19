package org.springframework.samples.notimeforheroes.game;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
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

@WebMvcTest(controllers = GameController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class GameControllerTests {
    private static final int TEST_GAME_ID = 17;

    @Autowired
	private MockMvc mockMvc;

    @MockBean
    private GameService gameService; 

    @MockBean
	private AuthoritiesService authoritiesService;



    private Game game;

	@BeforeEach
	void setup(){
        game =  new Game();
        game.setDuration(1000);
        game.setIsInProgress(false);
        game.setIsPublic(true);
        
		
        given(this.gameService.findById(TEST_GAME_ID)).willReturn(Optional.of(game));
	}


    @WithMockUser(value = "spring")
    @Test
    void testShowEnemyCard() throws Exception{
        mockMvc.perform(get("/games", TEST_GAME_ID)).andExpect(status().isOk())
		        .andExpect(model().attribute("games", hasProperty("duration", is("1000"))))
                .andExpect(model().attribute("games", hasProperty("isInProgress", is("false"))))
                .andExpect(model().attribute("games", hasProperty("isPublic", is("true"))))
                .andExpect(view().name("games/listadoGames"));
    }
}

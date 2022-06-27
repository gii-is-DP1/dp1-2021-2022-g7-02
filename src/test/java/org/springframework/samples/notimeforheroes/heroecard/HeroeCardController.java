package org.springframework.samples.notimeforheroes.heroecard;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCard;
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCardsService;
import org.springframework.samples.notimeforheroes.configuration.SecurityConfiguration;
import org.springframework.samples.notimeforheroes.user.AuthoritiesService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import net.bytebuddy.asm.Advice.Local;

@WebMvcTest(controllers = HeroeCardController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class HeroeCardController {

    @Autowired
    private HeroeCardsService heroeService;

    private static final int TEST_HEROE_ID = 17;

    @Autowired
	private MockMvc mockMvc;

    @MockBean
	private AuthoritiesService authoritiesService;

    private HeroeCard heroeCard;

    @BeforeEach
	void setup() {
		heroeCard = new HeroeCard();
		heroeCard.setId(TEST_HEROE_ID);
		heroeCard.setName("leo");;
		heroeCard.setColor("blue");
		heroeCard.setMaxHealth(3);
		heroeCard.setSkill("skill");
		heroeCard.setUrl("url");
	    given(this.heroeService.findById(TEST_HEROE_ID)).willReturn(Optional.of(heroeCard));

	}

    @WithMockUser(value = "spring")
	@Test
	void testShowOwner() throws Exception {
		mockMvc.perform(get("/cards/heroes", TEST_HEROE_ID)).andExpect(status().isOk())
				.andExpect(model().attribute("heroes", hasProperty("name", is("Leo"))))
				.andExpect(model().attribute("heroes", hasProperty("color", is("blue"))))
				.andExpect(model().attribute("heroes", hasProperty("maxHealth", is("3"))))
				.andExpect(model().attribute("heroes", hasProperty("skill", is("skill"))))
                .andExpect(model().attribute("heroes", hasProperty("url", is("url"))))
				.andExpect(view().name("cards/heroes/heroeCardsListing"));
	}


}

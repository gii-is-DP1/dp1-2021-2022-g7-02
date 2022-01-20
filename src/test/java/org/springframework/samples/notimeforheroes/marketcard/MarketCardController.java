package org.springframework.samples.notimeforheroes.marketcard;

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
import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCard;
import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCardsService;
import org.springframework.samples.notimeforheroes.configuration.SecurityConfiguration;
import org.springframework.samples.notimeforheroes.user.AuthoritiesService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import net.bytebuddy.asm.Advice.Local;

@WebMvcTest(controllers = MarketCardController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class MarketCardController {



    @Autowired
    private MarketCardsService marketService;

    private static final int TEST_MARKET_ID = 17;

    @Autowired
	private MockMvc mockMvc;

    @MockBean
	private AuthoritiesService authoritiesService;

    private MarketCard marketCard;


@BeforeEach
	void setup() {
		marketCard = new MarketCard();
		marketCard.setId(TEST_MARKET_ID);
		marketCard.setName("leo");;
		marketCard.setCost(4);
		marketCard.setUrl("url");
		marketCard.setDescription("description");
	    given(this.marketService.findById(TEST_MARKET_ID)).willReturn(Optional.of(marketCard));

	}

    @WithMockUser(value = "spring")
	@Test
	void testShowOwner() throws Exception {
		mockMvc.perform(get("/cards/market", TEST_MARKET_ID)).andExpect(status().isOk())
				.andExpect(model().attribute("market", hasProperty("name", is("Leo"))))
				.andExpect(model().attribute("market", hasProperty("cost", is("4"))))
				.andExpect(model().attribute("market", hasProperty("description", is("description"))))
                .andExpect(model().attribute("market", hasProperty("url", is("url"))))
				.andExpect(view().name("cards/market/marketCardsListing"));
	}


}

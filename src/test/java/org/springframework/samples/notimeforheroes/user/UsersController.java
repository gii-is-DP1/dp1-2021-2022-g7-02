package org.springframework.samples.notimeforheroes.user;

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
import org.springframework.samples.notimeforheroes.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import net.bytebuddy.asm.Advice.Local;

@WebMvcTest(controllers = UserController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class UsersController {

    @Autowired
    private UserService userService;

    private static final int TEST_USER_ID = 17;

    @Autowired
	private MockMvc mockMvc;

    @MockBean
	private AuthoritiesService authoritiesService;

    private User user;

    @BeforeEach
	void setup() {
		user = new User();
		user.setId(TEST_USER_ID);
		user.setName("Leo");;
		user.setLastname("Franklin");
		user.setEmail("leo@hotmail.com");
		user.setEnabled(true);
		user.setUsername("leo2");
        user.setPassword("123");
		given(this.userService.findById(TEST_USER_ID)).willReturn(Optional.of(user));

	}

    @WithMockUser(value = "spring")
	@Test
	void testShowOwner() throws Exception {
		mockMvc.perform(get("/users", TEST_USER_ID)).andExpect(status().isOk())
				.andExpect(model().attribute("user", hasProperty("lastName", is("Franklin"))))
				.andExpect(model().attribute("user", hasProperty("name", is("Leo"))))
				.andExpect(model().attribute("user", hasProperty("email", is("leo@hotmail.com"))))
				.andExpect(model().attribute("user", hasProperty("enabled", is("true"))))
				.andExpect(model().attribute("user", hasProperty("password", is("123"))))
                .andExpect(model().attribute("user", hasProperty("username", is("leo2"))))
				.andExpect(view().name("users/userListing"));
	}

}

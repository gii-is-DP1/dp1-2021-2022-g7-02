package org.springframework.samples.notimeforheroes.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.samples.notimeforheroes.user.exceptions.DuplicatedUserEmailException;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class UsersServiceTests {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testNingunJugador(){
		Collection<User> users=userService.findAll();
		users.clear();
		assertThat(users.isEmpty()).isTrue();
	}
	
	@Test
	public void testUnJugador(){
		Collection<User> users=userService.findAll();
		users.clear();
		
		User user= new User();
		user.setName("Juan");
		user.setEmail("juan@gmail.com");
		user.setLastname("Soto");
		user.setUsername("Juanillo7");
		user.setPassword("1234");
		users.add(user);
		assertThat(users.size()).isEqualTo(1);
	}
	
	@Test
	public void testMasJugadores(){
		Collection<User> users=userService.findAll();
		users.clear();
		
		User  user= new User();
		user.setName("Juan");
		user.setEmail("juan@gmail.com");
		user.setLastname("Soto");
		user.setUsername("Juanillo7");
		user.setPassword("1234");
		users.add(user);
		
		User  user1= new User();
		user1.setName("Jose");
		user1.setEmail("jose@gmail.com");
		user1.setLastname("Sota");
		user1.setUsername("Jose7");
		user1.setPassword("1234");
		users.add(user1);
		
		assertThat(users.size()).isGreaterThan(1);
	}

	@Test
	public void testEditUser(){
		User user = userService.findById(1).get();
		String oldName = user.getName();

		String newName = oldName + "X";
		user.setName(newName);
		userService.createUser(user);

		user = userService.findById(1).get();
		assertThat(user.getName()).isEqualTo(newName);
	}
	
	@Test
	public void testEditarJugadorMismoNombre(){
		String email = userService.findById(1).get().getEmail();

		User  user= new User();
		user.setEmail(email);
		user.setName("Jose");
		user.setLastname("Sota");
		user.setUsername("Jose7");
		user.setPassword("1234");
		
		try {
			userService.createUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Assertions.assertThrows(DuplicatedUserEmailException.class, () ->{
			user.setEmail("Jose1@gmail.com");
			userService.createUser(user);
		});	

		
	}

}

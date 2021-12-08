package org.springframework.samples.notimeforheroes.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.notimeforheroes.user.exceptions.DuplicatedUserEmailException;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class UsersServiceTests {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testNingunJugador(){
		Collection<User> users=userService.findAll();
		for(User u : users) {
			userService.deleteUser(u);
		}
		assertThat(userService.findAll().isEmpty()).isTrue();
	}
	
	@Test
	public void testUnJugador() throws DataAccessException, DuplicatedUserEmailException{
		Collection<User> users=userService.findAll();
		for(User u : users) {
			userService.deleteUser(u);
		}		
		User user= new User();
		user.setName("Juan");
		user.setEmail("juan@gmail.com");
		user.setLastname("Soto");
		user.setUsername("Juanillo7");
		user.setPassword("1234");
		userService.saveUser(user);
		assertThat(userService.findAll().size()).isEqualTo(1);
	}
	
	@Test
	public void testMasJugadores() throws DataAccessException, DuplicatedUserEmailException{
		Collection<User> users=userService.findAll();
		for(User u : users) {
			userService.deleteUser(u);
		}		
		User  user= new User();
		user.setName("Juan");
		user.setEmail("juan@gmail.com");
		user.setLastname("Soto");
		user.setUsername("Juanillo7");
		user.setPassword("1234");
		userService.saveUser(user);
		
		User  user1= new User();
		user1.setName("Jose");
		user1.setEmail("jose@gmail.com");
		user1.setLastname("Sota");
		user1.setUsername("Jose7");
		user1.setPassword("1234");
		userService.saveUser(user1);
		
		assertThat(userService.findAll().size()).isGreaterThan(1);
	}

	@Test
	public void testEditUser() throws DuplicatedUserEmailException{
		User user = userService.findById(1).get();
		String oldName = user.getName();

		String newName = oldName + "X";
		user.setName(newName);
		//userService.saveUser(user);

		user = userService.findById(1).get();
		assertThat(user.getName()).isEqualTo(newName);
	}
	


	@Test
	public void testEditUserSameName() throws DuplicatedUserEmailException{
		String email = userService.findById(1).get().getEmail();

		User  user= new User();
		user.setEmail(email);
		user.setName("Jose");
		user.setLastname("Sota");
		user.setUsername("Jose7");
		user.setPassword("1234");
		
		
		Assertions.assertThrows(DuplicatedUserEmailException.class, () ->{
			userService.saveUser(user);
		});	

		
	}
}
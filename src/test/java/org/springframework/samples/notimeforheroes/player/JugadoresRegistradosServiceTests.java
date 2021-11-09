package org.springframework.samples.notimeforheroes.player;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.notimeforheroes.player.Player;
import org.springframework.samples.notimeforheroes.player.PlayerService;
import org.springframework.samples.notimeforheroes.player.exceptions.DuplicatedPlayerEmailException;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class JugadoresRegistradosServiceTests {
	
	@Autowired
	private PlayerService jugadoresRegistradosService;
	
	@Test
	public void testNingunJugador(){
		Collection<Player> players=jugadoresRegistradosService.findAll();
		players.clear();
		assertThat(players.isEmpty()).isTrue();
	}
	
	@Test
	public void testUnJugador(){
		Collection<Player> players=jugadoresRegistradosService.findAll();
		players.clear();
		
		Player  player= new Player();
		player.setName("Juan");
		player.setEmail("juan@gmail.com");
		player.setLastname("Soto");
		player.setUsername("Juanillo7");
		player.setPassword("1234");
		players.add(player);
		assertThat(players.size()).isEqualTo(1);
	}
	
	@Test
	public void testMasJugadores(){
		Collection<Player> players=jugadoresRegistradosService.findAll();
		players.clear();
		
		Player  player= new Player();
		player.setName("Juan");
		player.setEmail("juan@gmail.com");
		player.setLastname("Soto");
		player.setUsername("Juanillo7");
		player.setPassword("1234");
		players.add(player);
		
		Player  player1= new Player();
		player1.setName("Jose");
		player1.setEmail("jose@gmail.com");
		player1.setLastname("Sota");
		player1.setUsername("Jose7");
		player1.setPassword("1234");
		players.add(player1);
		
		assertThat(players.size()).isGreaterThan(1);
	}

	@Test
	public void testEditarJugador(){
		Player player = jugadoresRegistradosService.findById(1).get();
		String oldName = player.getName();

		String newName = oldName + "X";
		player.setName(newName);
		jugadoresRegistradosService.createPlayer(player);

		player = jugadoresRegistradosService.findById(1).get();
		assertThat(player.getName()).isEqualTo(newName);
	}
	
	@Test
	public void testEditarJugadorMismoNombre(){
		String email = jugadoresRegistradosService.findById(1).get().getEmail();

		Player  player= new Player();
		player.setEmail(email);
		player.setName("Jose");
		player.setLastname("Sota");
		player.setUsername("Jose7");
		player.setPassword("1234");
		
		try {
			jugadoresRegistradosService.createPlayer(player);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Assertions.assertThrows(DuplicatedPlayerEmailException.class, () ->{
			player.setEmail("Jose1@gmail.com");
			jugadoresRegistradosService.createPlayer(player);
		});	

		
	}

}

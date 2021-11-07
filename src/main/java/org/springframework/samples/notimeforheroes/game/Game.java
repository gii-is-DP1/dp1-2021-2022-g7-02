package org.springframework.samples.notimeforheroes.game;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.player.Player;
import org.springframework.samples.notimeforheroes.player.PlayerRepository;
import org.springframework.samples.notimeforheroes.player.PlayerService;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "games")
@Getter
@Setter
@Table(name = "games") 



public class Game{
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	@ManyToOne
	@JoinColumn(name="creator")
	private Player creator;
	
	
	//private List<JugadoresRegistrados> players;			//TODO: Es un one to many, hay que hacer un many to one en el otro lado
	

	@Column(name="duration",columnDefinition = "INT")
	private Integer duration = 0;	//En segundos

	@Column(name = "date", columnDefinition = "DATE")
	private LocalDate date = LocalDate.now();
	
	@Column(name="isInProgress", columnDefinition = "INT")
	private Integer isInProgress = 1;						// 1 = está en progreso. 0 = no está en progreso.
	
	
	
	
	
}

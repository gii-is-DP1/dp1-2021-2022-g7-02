package org.springframework.samples.notimeforheroes.game;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.notimeforheroes.jugadoresregistrados.JugadoresRegistrados;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "games") 
public class Game{
	
	@Id
	@Column(name="id",columnDefinition = "INT")
	@NotEmpty
	private int id;
	
	
	@Column(name="creator",columnDefinition = "INT")
	@NotEmpty
	private int creator;		//Id del creador
	
	
	//private List<JugadoresRegistrados> players;			//TODO: Puede que no sea una propiedad
	

	@Column(name="duration",columnDefinition = "INT")
	@NotEmpty
	private Integer duration;	//En segundos

	@Column(name="date",columnDefinition = "DATETIME")
	@NotEmpty
	private LocalDate date;
	
	@Column(name="isInProgress",columnDefinition = "INT")
	@NotEmpty
	private Integer isInProgress;						// 1 = está en progreso. 0 = no está en progreso.
	
}

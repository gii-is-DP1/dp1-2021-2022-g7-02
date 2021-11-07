package org.springframework.samples.notimeforheroes.player;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="players")
public class Player extends NamedEntity{

	@Column(name="lastname",columnDefinition = "LONGTEXT")
    @NotEmpty
	private String lastname;
	
	@Column(name="username",columnDefinition = "LONGTEXT")
    @NotEmpty
	private String username;
	
	@Column(name="email",columnDefinition = "LONGTEXT")
    @NotEmpty
	private String email;
	
	@Column(name="password",columnDefinition = "LONGTEXT")
    @NotEmpty
	private String password;
	
}

package org.springframework.cartas;

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
@Table(name="usuarios_registrados")
public class Cartas extends NamedEntity{

	@Column(name="type",columnDefinition = "LONGTEXT")
    @NotEmpty
	private String type;
	
	@Column(name="life",columnDefinition = "LONGTEXT")
    @NotEmpty
	private Integer life;
	
	@Column(name="available",columnDefinition = "LONGTEXT")
    @NotEmpty
	private Boolean available;
	
	@Column(name="glory",columnDefinition = "LONGTEXT")
    @NotEmpty
	private Integer glory;

	@Column(name="gold",columnDefinition = "LONGTEXT")
    @NotEmpty
	private Integer gold;
	
	@Column(name="skill",columnDefinition = "LONGTEXT")
    @NotEmpty
	private String skill;
	
	@Column(name="extraglory",columnDefinition = "LONGTEXT")
    @NotEmpty
	private Integer extraglory;
	
	@Column(name="color",columnDefinition = "LONGTEXT")
    @NotEmpty
	private String color;
	
	@Column(name="deckId",columnDefinition = "LONGTEXT")
    @NotEmpty
	private Integer deckId;
	
}

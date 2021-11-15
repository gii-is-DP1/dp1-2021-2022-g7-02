package org.springframework.samples.notimeforheroes.cards;

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
@Table(name="cards")
public class Cards extends NamedEntity{

	@Column(name="type",columnDefinition = "LONGTEXT")
    @NotEmpty
	private String type;
	
	@Column(name="life",columnDefinition = "LONGTEXT")
    
	private Integer life;
	
	@Column(name="glory",columnDefinition = "LONGTEXT")
    
	private Integer glory;

	@Column(name="gold",columnDefinition = "LONGTEXT")
    
	private Integer gold;
	
	@Column(name="skill",columnDefinition = "LONGTEXT")
    @NotEmpty
	private String skill;
	
	@Column(name="extraglory",columnDefinition = "LONGTEXT")
    
	private Integer extraglory;
	
	@Column(name="color",columnDefinition = "LONGTEXT")
    @NotEmpty
	private String color;
	
	@Column(name="deckid",columnDefinition = "LONGTEXT")
    
	private Integer deckid;
	
}

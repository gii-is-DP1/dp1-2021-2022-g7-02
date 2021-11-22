package org.springframework.samples.notimeforheroes.heroecard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.Card;
import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="heroes")
public class HeroeCards extends Card{

	private Integer life;
	
	@Column(columnDefinition = "LONGTEXT")
	private String skill;
	
	private String color;
	
	private Integer deckid;
	
	
}

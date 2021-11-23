package org.springframework.samples.notimeforheroes.skillcard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.Card;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "skills")
public class SkillCards extends Card{

	@Column(columnDefinition = "LONGTEXT")
	private String description;
	
	private Integer deckid;
}

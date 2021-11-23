package org.springframework.samples.notimeforheroes.cards.skillcard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "skills")
public class SkillCard extends org.springframework.samples.notimeforheroes.cards.Card{

	@Column(columnDefinition = "LONGTEXT")
	private String description;
	
	private Integer deckid;
}

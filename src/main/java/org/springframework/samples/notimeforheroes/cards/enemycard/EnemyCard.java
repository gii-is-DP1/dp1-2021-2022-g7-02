package org.springframework.samples.notimeforheroes.cards.enemycard;

import javax.persistence.Entity;

import javax.persistence.Table;

import org.springframework.samples.notimeforheroes.cards.Card;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="enemies")


public class EnemyCard extends Card {
	
	private Boolean isboss; //false no es boss, true si es boss

	private Integer life;
	
	private Integer glory;
	
	private Integer extraGlory;
	
	private Integer extraGold;

}

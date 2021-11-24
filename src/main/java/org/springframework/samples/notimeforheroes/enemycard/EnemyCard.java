package org.springframework.samples.notimeforheroes.enemycard;

import javax.persistence.Column;
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
	
	private Integer life;
	
	private Integer glory;
	
	private Integer extraglory;
	
	private Integer extragold;

}

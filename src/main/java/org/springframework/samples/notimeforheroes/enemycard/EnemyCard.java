package org.springframework.samples.notimeforheroes.enemycard;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="enemies")


public class EnemyCard extends NamedEntity {
	
    @Column()
    public String url;

    @Column(name = "enemyState")
    public EnemyState enemyState = EnemyState.ONDESCK;
	
	private Boolean isboss; //false no es boss, true si es boss

	private Integer maxHealth;
	
	private Integer glory;
	
	private Integer extraGlory;
	
	private Integer extraGold;

}

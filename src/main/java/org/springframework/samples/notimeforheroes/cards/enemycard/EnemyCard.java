package org.springframework.samples.notimeforheroes.cards.enemycard;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.samples.notimeforheroes.actions.Actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.cards.enemycard.gamesEnemies.GamesEnemies;
import org.springframework.samples.notimeforheroes.cards.enemycard.gamesEnemies.GamesEnemiesRepository;
import org.springframework.samples.notimeforheroes.cards.enemycard.gamesEnemies.GamesEnemiesService;
import org.springframework.samples.notimeforheroes.game.Game;
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
	
	private Boolean isBoss; //false no es boss, true si es boss

	private Integer maxHealth;

	private Integer healthInGame;
	
	private Integer glory;
	
	private Integer extraGlory;
	
	private Integer extraGold;

	@ManyToMany
	@JoinTable(name = "actions_card",
		joinColumns = {@JoinColumn(name = "fk_skillcard")},
		inverseJoinColumns = {@JoinColumn(name = "fk_actions")})
	private Collection<Actions> actions;

}

package org.springframework.samples.notimeforheroes.cards.enemycard;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.springframework.samples.notimeforheroes.actions.Action;
import org.springframework.samples.notimeforheroes.model.NamedEntity;

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
	@JoinTable(name = "actions_enemyCard",
		joinColumns = {@JoinColumn(name = "fk_skillcard")},
		inverseJoinColumns = {@JoinColumn(name = "fk_actions")})
	private Collection<Action> actions;

}

package org.springframework.samples.notimeforheroes.cards.heroecard;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.samples.notimeforheroes.actions.Action;
import org.springframework.samples.notimeforheroes.cards.skillcard.SkillCard;
import org.springframework.samples.notimeforheroes.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="heroes")
public class HeroeCard extends NamedEntity{
	
    @Column()
    public String url;

	private Integer maxHealth;
	
	@Column(columnDefinition = "LONGTEXT")
	private String skill;
	
	private String color;

	@ManyToMany
	@JoinTable(name = "heroes_skills",
		joinColumns = {@JoinColumn(name = "fk_heroe")},
		inverseJoinColumns = {@JoinColumn(name = "fk_skill")})
	private Collection<SkillCard> skills;

	@ManyToMany
	@JoinTable(name = "actions_heroeCard",
		joinColumns = {@JoinColumn(name = "fk_skillcard")},
		inverseJoinColumns = {@JoinColumn(name = "fk_actions")})
	private Collection<Action> actions;
	
	
	
}

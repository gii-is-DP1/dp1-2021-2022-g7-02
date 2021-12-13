package org.springframework.samples.notimeforheroes.heroecard;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.springframework.samples.notimeforheroes.skillcard.SkillCard;
import org.springframework.samples.petclinic.model.NamedEntity;

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
	

	
	
}

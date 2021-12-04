package org.springframework.samples.notimeforheroes.skillcard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "skills")
public class SkillCard extends NamedEntity{
	
    @Column()
    public String url;

    @Column(name = "skillState")
    public SkillState skillState = SkillState.ONDESCK;

	@Column(columnDefinition = "LONGTEXT")
	private String description;
	
	private Integer deckid;
}

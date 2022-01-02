package org.springframework.samples.notimeforheroes.cards.scenecard;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.samples.notimeforheroes.actions.Actions;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="scenes")
public class SceneCard extends NamedEntity{
	
    @Column()
    public String url;

    @Column(name = "cardState")
    public Boolean inGame = false;
    
	@Column(columnDefinition = "LONGTEXT")
	private String description;

    @ManyToMany
	@JoinTable(name = "actions_card",
		joinColumns = {@JoinColumn(name = "fk_skillcard")},
		inverseJoinColumns = {@JoinColumn(name = "fk_actions")})
	private Collection<Actions> actions;
}

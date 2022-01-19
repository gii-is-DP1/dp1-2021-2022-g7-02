package org.springframework.samples.notimeforheroes.cards.marketcard;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.samples.notimeforheroes.actions.Action;
import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="market")
public class MarketCard extends NamedEntity{
	
    @Column()
    public String url;

	private Integer cost;
		
	@Column(columnDefinition = "LONGTEXT")
	private String description;
 
	@ManyToMany
	@JoinTable(name = "actions_marketCard",
		joinColumns = {@JoinColumn(name = "fk_skillcard")},
		inverseJoinColumns = {@JoinColumn(name = "fk_actions")})
	private Collection<Action> actions;
	
}

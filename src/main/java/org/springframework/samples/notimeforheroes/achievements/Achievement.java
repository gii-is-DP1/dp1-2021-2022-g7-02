package org.springframework.samples.notimeforheroes.achievements;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCard;
import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="achievement" /*, uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) }*/)
public class Achievement extends NamedEntity{

	@Column(name="description",columnDefinition = "LONGTEXT")
    @NotEmpty
	private String description;
	
	@ManyToMany
	@JoinTable(name = "achievements_users",
		joinColumns = {@JoinColumn(name = "fk_achievement")},
		inverseJoinColumns = {@JoinColumn(name = "fk_users")})
	private List<Achievement> achievements;	
	
}
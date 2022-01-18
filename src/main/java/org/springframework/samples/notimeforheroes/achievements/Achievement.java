package org.springframework.samples.notimeforheroes.achievements;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

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
	
	@Column(name="number")
    @NotEmpty
	private Integer numberAchievement;

	@Column(name="type")
    @NotEmpty
	private AchievementType type;
}

package org.springframework.samples.notimeforheroes.achievements;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.notimeforheroes.model.NamedEntity;

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
    @NotNull
	private Integer numberAchievement;

	@Column(name="type")
    @NotNull
	private AchievementType type;
}

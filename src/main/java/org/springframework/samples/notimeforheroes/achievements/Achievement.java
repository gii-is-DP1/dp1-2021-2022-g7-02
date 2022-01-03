package org.springframework.samples.notimeforheroes.achievements;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.ManyToAny;
import org.springframework.samples.petclinic.model.NamedEntity;

import antlr.collections.List;

import org.springframework.samples.notimeforheroes.user.User;

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
	private Collection<User> user;
	
}

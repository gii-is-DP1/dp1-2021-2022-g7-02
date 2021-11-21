package org.springframework.samples.notimeforheroes.cards;

import javax.persistence.Entity;

import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="cards")
public class Cards extends NamedEntity{

    @NotEmpty
	private String url;
	
}

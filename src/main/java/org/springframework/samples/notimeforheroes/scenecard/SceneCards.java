package org.springframework.samples.notimeforheroes.scenecard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.Card;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="scenes")
public class SceneCards extends Card{
	
	@Column(columnDefinition = "LONGTEXT")
	private String description;

}

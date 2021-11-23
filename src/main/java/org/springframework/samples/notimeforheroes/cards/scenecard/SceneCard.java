package org.springframework.samples.notimeforheroes.cards.scenecard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.notimeforheroes.cards.Card;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="scenes")
public class SceneCard extends Card{
	
	@Column(columnDefinition = "LONGTEXT")
	private String description;

}

package org.springframework.samples.notimeforheroes.heroecard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="heroes")
public class HeroeCard extends NamedEntity{
	
    @Column()
    public String url;

	private Integer life;
	
	@Column(columnDefinition = "LONGTEXT")
	private String skill;
	
	private String color;
	
	private Integer deckid;
	

	
	
}

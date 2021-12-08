package org.springframework.samples.notimeforheroes.scenecard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

}

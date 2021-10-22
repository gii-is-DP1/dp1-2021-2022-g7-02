package org.springframework.samples.notimeforheroes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.NamedEntity;

public class CartaLocalizacion {
	
	@Entity
	@Table(name="Carta Localizacion")
	public class Disease extends NamedEntity{

	    @Column(name="description",columnDefinition = "LONGTEXT")
	    @NotEmpty
	    private String description;


	    public String getDescription() {
	        return description;
	    }
	}
	
	

}
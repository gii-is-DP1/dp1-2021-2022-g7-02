package org.springframework.samples.notimeforheroes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.NamedEntity;


	
@Entity
@Table(name="cartalocalizacion")
public class CartaLocalizacion extends NamedEntity{

	@Column(name="description",columnDefinition = "LONGTEXT")
	@NotEmpty
	private String description;


    public String getDescription() {
	        return description;
	    }
    
	}
	
	
  



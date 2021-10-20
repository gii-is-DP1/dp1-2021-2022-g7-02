package org.springframework.samples.petclinic.disease;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.NamedEntity;


@Entity
@Table(name="diseases")
public class Disease extends NamedEntity{
	
	@Column(name="description",columnDefinition = "LONGTEXT")
	@NotEmpty
	private String description;
}

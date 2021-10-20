package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name="diseases")
public class Disease extends NamedEntity{
	
	@Column(name="description",columnDefinition = "LONGTEXT")
	@NotEmpty
	private String description;
}

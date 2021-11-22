package org.springframework.samples.petclinic.model;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

@MappedSuperclass
public class Card extends NamedEntity{

	@NotEmpty
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	@Override
	public String toString() {
		return this.getUrl();
	}
	
}

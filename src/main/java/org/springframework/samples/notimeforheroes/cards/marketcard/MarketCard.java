package org.springframework.samples.notimeforheroes.cards.marketcard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="market")
public class MarketCard extends NamedEntity{
	
    @Column()
    public String url;

	private Integer cost;
		
	@Column(columnDefinition = "LONGTEXT")
	private String description;

	
}

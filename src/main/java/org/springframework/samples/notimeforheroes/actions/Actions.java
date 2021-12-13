package org.springframework.samples.notimeforheroes.actions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="actions")

public class Actions extends NamedEntity{
	
    @Column()
    public TypesActions type;
    
    @Column()
    public Integer cantidad;
}

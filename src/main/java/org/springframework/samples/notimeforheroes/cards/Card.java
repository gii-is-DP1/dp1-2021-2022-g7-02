package org.springframework.samples.notimeforheroes.cards;

import javax.persistence.Column;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class Card extends NamedEntity{

    @Column()
    public String url;

    @Column(name = "cardState")
    public CardState cardState = CardState.DISCARDED;


}

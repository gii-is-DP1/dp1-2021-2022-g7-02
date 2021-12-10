package org.springframework.samples.notimeforheroes.gamesMarket;

import javax.persistence.Entity;

import javax.persistence.Table;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="games_markets")
public class GameMarket extends BaseEntity{

    /*@ManyToMany
    private List<MarketCard> items;*/
    
}
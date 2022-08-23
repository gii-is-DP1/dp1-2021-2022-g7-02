package org.springframework.samples.notimeforheroes.cards.marketcard.gamesMarket;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samples.notimeforheroes.cards.marketcard.ItemState;
import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCard;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="games_markets")
public class GameMarket extends BaseEntity{

	
	
    private ItemState itemState = ItemState.ONDECK;

}
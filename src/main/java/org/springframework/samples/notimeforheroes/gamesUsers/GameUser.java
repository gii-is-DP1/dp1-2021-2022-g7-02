package org.springframework.samples.notimeforheroes.gamesUsers;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.samples.notimeforheroes.heroecard.HeroeCard;
import org.springframework.samples.notimeforheroes.marketcard.MarketCard;
import org.springframework.samples.notimeforheroes.skillcard.SkillCard;
import org.springframework.samples.petclinic.model.BaseEntity;

import java.util.Collection;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="games_users")
public class GameUser extends BaseEntity{
    @ManyToOne
    private HeroeCard heroe;

    @ManyToMany
    @JoinTable(name="games_users_skill_cards")
    private Collection<SkillCard> skillCards;

    @ManyToMany
    private List<MarketCard> items;

    private Boolean hasEscapeToken = true;

    private Integer gold = 0;

    private Integer glory = 0;

    private Boolean winner;
    
}
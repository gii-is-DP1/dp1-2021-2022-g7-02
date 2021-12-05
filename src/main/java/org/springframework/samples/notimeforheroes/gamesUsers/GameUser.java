package org.springframework.samples.notimeforheroes.gamesUsers;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCard;
import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCard;
import org.springframework.samples.notimeforheroes.cards.skillcard.SkillCard;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.samples.petclinic.model.BaseEntity;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="games_users")
public class GameUser extends BaseEntity{
   /*
    @ManyToOne 
    private User user;

    @ManyToOne
    private Game game;
*/
    @ManyToOne
    private HeroeCard heroe;

    @ManyToMany
    private List<SkillCard> skillCards;

    @ManyToMany
    private List<MarketCard> items;

    private Boolean hasEscapeToken;

    private Integer gold;

    private Integer glory;

    private Boolean winner;
    
}

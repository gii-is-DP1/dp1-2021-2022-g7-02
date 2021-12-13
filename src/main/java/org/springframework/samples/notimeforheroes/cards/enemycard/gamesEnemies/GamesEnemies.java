package org.springframework.samples.notimeforheroes.cards.enemycard.gamesEnemies;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyState;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="games_enemies")
public class GamesEnemies extends BaseEntity{
    
    private Integer health;

    private EnemyState enemyState;


}

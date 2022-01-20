package org.springframework.samples.notimeforheroes.cards.skillcard.gamesUsersSkillcards;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.notimeforheroes.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="games_users_skill_cards")
public class GamesUsersSkillCards extends BaseEntity{

    public SkillState skillState = SkillState.ONDECK;
    
}

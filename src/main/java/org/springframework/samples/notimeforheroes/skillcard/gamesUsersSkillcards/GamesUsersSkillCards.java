package org.springframework.samples.notimeforheroes.skillcard.gamesUsersSkillcards;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="games_users_skill_cards")
public class GamesUsersSkillCards extends BaseEntity{

    public SkillState skillState = SkillState.ONDECK;
    
}

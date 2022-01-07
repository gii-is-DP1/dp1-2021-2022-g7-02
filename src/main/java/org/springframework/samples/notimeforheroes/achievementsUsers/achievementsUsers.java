package org.springframework.samples.notimeforheroes.achievementsUsers;


import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="achievements_users")

public class achievementsUsers extends BaseEntity {

	
}

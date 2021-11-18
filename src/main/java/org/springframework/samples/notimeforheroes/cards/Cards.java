package org.springframework.samples.notimeforheroes.cards;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="cards")
public class Cards extends NamedEntity{

	@Column(name="type")
    @NotEmpty
	private String type;
	
	@Column(name="life")
	private Integer life;
	
	@Column(name="glory")
	private Integer glory;

	@Column(name="gold")
	private Integer gold;
	
	@Column(name="skill",columnDefinition = "LONGTEXT")
    @NotEmpty
	private String skill;
	
	@Column(name="extraglory")
	private Integer extraglory;
	
	@Column(name="color")
    @NotEmpty
	private String color;
	
	@Column(name="deckid")
	private Integer deckid;
	
	@OneToOne
	@JoinTable(name = "card_users", joinColumns = {@JoinColumn(name = "fk_user")}, inverseJoinColumns = {@JoinColumn(name = "fk_card")})
	private User user;
	
}

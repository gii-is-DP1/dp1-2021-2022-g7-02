package org.springframework.samples.notimeforheroes.user;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="users"/*, uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) }*/)
public class User extends NamedEntity implements Serializable{

    @NotEmpty
	private String lastname;
	
	@Column(name="username",columnDefinition = "LONGTEXT")
    @NotEmpty
	private String username;

    @NotEmpty
	private String email;
	
    @NotEmpty
	private String password;

	boolean enabled = true;

	@ManyToMany(mappedBy="users")
    private List<Game> games;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities;


	public String toString(){
		return this.username;
	}

	public Boolean isAdmin(){
		UserService userService = new UserService();
		return userService.isUserAdmin(this);
	}
	
}

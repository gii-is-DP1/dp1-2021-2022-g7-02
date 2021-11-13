package org.springframework.samples.notimeforheroes.user;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.core.sym.Name;

import org.hibernate.validator.constraints.UniqueElements;
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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities;


		
	
}

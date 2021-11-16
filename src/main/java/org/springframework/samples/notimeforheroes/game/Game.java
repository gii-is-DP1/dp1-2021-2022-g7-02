package org.springframework.samples.notimeforheroes.game;
import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "games")
@Getter
@Setter
@Table(name = "games") 



public class Game extends BaseEntity{
	
	
	
	@ManyToOne
	@JoinColumn(name="creator")
	private User creator;				
	
	/*
	@ManyToMany
    @JoinTable(name = "book_author",
        joinColumns = { @JoinColumn(name = "fk_book") },
        inverseJoinColumns = { @JoinColumn(name = "fk_author") })
    private List<Author> authors = new ArrayList<Author>();
	*/
	@ManyToMany
	@JoinTable(name = "games_users",
		joinColumns = {@JoinColumn(name = "fk_game")},
		inverseJoinColumns = {@JoinColumn(name = "fk_user")})
	private Collection<User> users;						
	

	@Column(name="duration",columnDefinition = "INT")
	private Integer duration = 0;	//En segundos

	@Column(name = "date", columnDefinition = "DATE")
	private LocalDate date = LocalDate.now();
	
	@Column()
	private Boolean isInProgress = false;						

	@Column()
	private String joinCode = UUID.randomUUID().toString();



	//METODOS EQUALS TOSTRING Y HASHCODE


	@Override
	public String toString() {
		return "Game [id=" + id + ", creator=" + creator + ", duration=" + duration + ", date=" + date
				+ ", isInProgress=" + isInProgress + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + id;
		result = prime * result + ((isInProgress == null) ? 0 : isInProgress.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (id != other.id)
			return false;
		if (isInProgress == null) {
			if (other.isInProgress != null)
				return false;
		} else if (!isInProgress.equals(other.isInProgress))
			return false;
		return true;
	}
	
	
	
	
	
}
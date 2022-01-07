package org.springframework.samples.notimeforheroes.achievementsUsers;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.notimeforheroes.user.User;

public interface achievementsUsersRepository extends CrudRepository<achievementsUsers,Integer>{

	
	Collection<achievementsUsers> findAll();
	
	

	Collection<achievementsUsers> findAllByUser(User user);
}

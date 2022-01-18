package org.springframework.samples.notimeforheroes.achievementsUsers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;


public class achievementsUsersService {

	
	@Autowired
	achievementsUsersRepository achievementsUsersRepository;
	
	public Collection<achievementsUsers> findAll(){
		return achievementsUsersRepository.findAll();
	}
	
	/*public Collection<achievementsUsers> findAllbyUser(User user){
		return achievementsUsersRepository.findAllByUser(user);
	}*/
}

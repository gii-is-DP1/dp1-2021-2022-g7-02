package org.springframework.samples.notimeforheroes.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.notimeforheroes.user.exceptions.DuplicatedUserEmailException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public Collection<User> findAll(){
		return userRepository.findAll();
	}
	
	@Transactional
	public Optional<User> findById(Integer id){
		return userRepository.findById(id);
	}
	
	@Transactional
	public void deleteUser(User user) {
		userRepository.deleteById(user.getId());
		}
	
	@Transactional
	public void createUser(@Valid User user) {
		userRepository.save(user);
	}
	
	@Transactional(rollbackOn = DuplicatedUserEmailException.class)
	public void savePlayer(User player) throws DataAccessException,DuplicatedUserEmailException { // 
			List<String> emails=new ArrayList<String>();
			List<User> jugadores= (List<User>) this.findAll();
			for (int i=0; i<jugadores.size(); i++) {
				emails.add(jugadores.get(i).getEmail());				
			}
            if (emails.contains(player.getEmail())) {            	
            	throw new DuplicatedUserEmailException();
            }else
                userRepository.save(player);                
	}
}

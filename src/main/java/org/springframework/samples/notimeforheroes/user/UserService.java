package org.springframework.samples.notimeforheroes.user;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthoritiesService authoritiesService;
	
	@Transactional
	public Collection<User> findAll(){
		return userRepository.findAll();
	}
	
	@Transactional
	public Optional<User> findById(Integer id){
		return userRepository.findById(id);
	}

	public Optional<User> findByUsername(String username){
		return userRepository.findByUsername(username);
	}
	
	@Transactional
	public void deleteUser(User user) {
		userRepository.deleteById(user.getId());
		}
	
	@Transactional
	public void createUser(@Valid User user) {
		user.setEnabled(true);
		userRepository.save(user);
		authoritiesService.saveAuthorities(user.getUsername(), "player");
	}
}

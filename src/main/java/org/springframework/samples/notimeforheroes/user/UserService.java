package org.springframework.samples.notimeforheroes.user;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
}

package org.springframework.samples.notimeforheroes.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.game.GameService;
import org.springframework.samples.notimeforheroes.user.exceptions.DuplicatedUserEmailException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthoritiesService authoritiesService;

	@Autowired
	GameService gameService;
	
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

	public Collection<User> findAllInGame(Game game){
		Collection<User> users = userRepository.findAll();
		List<User> res = new ArrayList<>();
		users.stream().forEach(user -> {
			if(user.getGames().contains(game))
				res.add(user);
		});
		return res;
 	}
	
	@Transactional
	public void deleteUser(User user) {
		Collection<Game> gamesOfUser = gameService.findAllByCreator(user);
		for(Game game : gamesOfUser){
			System.out.println(game);
			gameService.deleteGame(game);
		}
		userRepository.deleteById(user.getId());
		}
	
	
	@Transactional(rollbackOn = DuplicatedUserEmailException.class)
	public void saveUser(@Valid User user) throws DataAccessException,DuplicatedUserEmailException { // 
			List<String> emails=new ArrayList<String>();
			List<User> jugadores= (List<User>) this.findAll();
			for (int i=0; i<jugadores.size(); i++) {
				if(!jugadores.get(i).getId().equals(user.getId()))
					emails.add(jugadores.get(i).getEmail());				
			}
            if (emails.contains(user.getEmail())) {            	
            	throw new DuplicatedUserEmailException();
            }else{
				user.setEnabled(true);
				userRepository.save(user);
				authoritiesService.saveAuthorities(user.getUsername(), "player");
			}           
	}
	
}

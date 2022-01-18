package org.springframework.samples.notimeforheroes.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyCard;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.game.GameService;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUser;
import org.springframework.samples.notimeforheroes.user.exceptions.DuplicatedUserEmailException;
import org.springframework.security.core.context.SecurityContextHolder;
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
	public Collection<User> findAllPage(Integer pageNo, Integer pageSize){
		Pageable pagin = PageRequest.of(pageNo, pageSize);
		Page<User> pageResult = userRepository.findAll(pagin);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		} else {
			return new ArrayList<User>();
		}
	}
	
	//
	@Transactional
	public Collection<User> findAll(){
		return userRepository.findAll();
	}
	
	//
	@Transactional
	public Optional<User> findById(Integer id){
		return userRepository.findById(id);
	}

	//
	public Optional<User> findByUsername(String username){
		return userRepository.findByUsername(username);
	}

	public User findByGameUser(GameUser gameUser){
		return userRepository.findByGameUser(gameUser).get();
	}

	public Collection<User> findAllInGameWithHeroeSelected(Game game){
		return userRepository.findAllInGameWithHeroeSelected(game);
	}

	public User getLoggedUser(){
		org.springframework.security.core.userdetails.User user_aux = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User loggedUser = this.findByUsername(user_aux.getUsername()).get();
		return loggedUser;
	}

	//
	public Boolean isUserAdmin(User user){
		Set<Authorities> authorities = user.getAuthorities();
		return authorities.stream().anyMatch(auth -> auth.getAuthority().equals("admin"));
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

	public Map<User, Integer> getListOfOpponents(User user){
		Map<User, Integer> players = new HashMap<User, Integer>();

		for (Game game : gameService.findByUser(user)) {
			for (User player : game.getUsers()) {
				if(player != user){
					if(!players.containsKey(player)){
						players.put(player, 1);
					}
					else{
						players.replace(player, players.get(player)+1);
					}
				}
			}
		}

		return players;
	}

	@Transactional
	public void deleteUser(User user) {
		Collection<Game> gamesOfUser = gameService.findAllByCreator(user);
		Collection<Game> gamesWhereUser = gameService.findByUser(user);
		for(Game game : gamesOfUser){
			gameService.deleteGame(game);
		}
		for(Game game : gamesWhereUser){
			game.getUsers().remove(user);
		}

		userRepository.deleteById(user.getId());
	}
	
	@Transactional(rollbackOn = DuplicatedUserEmailException.class)
	public void saveUser(@Valid User user) throws DataAccessException,DuplicatedUserEmailException { 
			Optional<User> userFindedByEmail = userRepository.findByEmail(user.getEmail());
			if(userFindedByEmail.isPresent()){
				throw new DuplicatedUserEmailException();
			}else{
				user.setEnabled(true);
				userRepository.save(user);
				authoritiesService.saveAuthorities(user.getUsername(), "player");
			}

	}
	
}

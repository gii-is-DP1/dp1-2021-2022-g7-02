package org.springframework.samples.notimeforheroes.game;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class GameService {

	@Autowired
	GameRepository gameRepository;

	@Autowired
	UserService userService;
	
	public Collection<Game> findAll(){
		return gameRepository.findAll();
	}
	
	public Optional<Game> findById(Integer id){
		return gameRepository.findById(id);
	}
	
	
	public Collection<Game> findAllEnded(){
		return gameRepository.findAllEnded();
	}
	
	public Collection<Game> findAllByIsInProgress(){
		return gameRepository.findAllByIsInProgress(true);
	}

	public Collection<Game> findAllByCreator(User user){
		return gameRepository.findAllByCreator(user);
	}

	public Optional<Game> findByJoinCode(String joinCode){
		return gameRepository.findByJoinCode(joinCode.trim());
	}
	
	@Transactional
	public void createGame(@Valid Game game){	
		
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User creator;
			if(auth == null){
				creator = userService.findById(1).get();	//Esto se hace para que los juegos que se crean en los tests tengan creador
			}else{
				String creatorUsername = ((org.springframework.security.core.userdetails.User)auth.getPrincipal()).getUsername();
				creator = userService.findByUsername(creatorUsername).get();
			}
			
			game.setCreator(creator);

			game.setUsers(List.of(creator));

			gameRepository.save(game);
	}

	@Transactional
	public void updateGame(@Valid Game game){
		gameRepository.save(game);
	}
	
	@Transactional
	public void deleteGame(Game game) {
		gameRepository.deleteById(game.getId());
	}
}

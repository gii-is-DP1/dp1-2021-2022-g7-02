package org.springframework.samples.notimeforheroes.gamesUsers;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.stereotype.Service;

@Service
public class GameUserService {

	@Autowired
	GameUserRepository gameUserRepository;

	@Autowired
	GameUserService gameUserService;
	
	

	public Collection<GameUser> findAll(){
		return gameUserRepository.findAll();
	}

	public Optional<GameUser> findById(Integer id){
		return gameUserRepository.findById(id);
	}

	public Optional<GameUser> findByGameAndUser(Game game, User user){
		return gameUserRepository.findByGameAndUser(game.getId(), user.getId());
	}
	
	
	@Transactional
	public void deleteGameUser(GameUser gameUser) {
		gameUserRepository.deleteById(gameUser.getId());
		}
	
	@Transactional
	public void createGameUser(@Valid GameUser gameUser) {
		gameUserRepository.save(gameUser);
	}
	
	
	
}



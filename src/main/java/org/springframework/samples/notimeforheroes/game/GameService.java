package org.springframework.samples.notimeforheroes.game;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.player.Player;
import org.springframework.stereotype.Service;

@Service
public class GameService {

	@Autowired
	GameRepository gameRepository;
	
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
		return gameRepository.findAllByIsInProgress(1);
	}
	
	@Transactional
	public void createGame(@Valid Game game) {
		gameRepository.save(game);
	}
	
	@Transactional
	public void deleteGame(Game game) {
		gameRepository.deleteById(game.getId());
	}
}

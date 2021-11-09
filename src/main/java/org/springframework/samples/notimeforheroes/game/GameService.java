package org.springframework.samples.notimeforheroes.game;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.player.Player;
import org.springframework.samples.notimeforheroes.player.PlayerService;
import org.springframework.stereotype.Service;

@Service
public class GameService {

	@Autowired
	GameRepository gameRepository;

	@Autowired
	PlayerService playerService;
	
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
		//game.setPlayers(List.of(playerService.findById(1).get()));	//TODO: Cambiar el 1 por la id del creador
		gameRepository.save(game);
	}
	
	@Transactional
	public void deleteGame(Game game) {
		gameRepository.deleteById(game.getId());
	}
}

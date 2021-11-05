package org.springframework.samples.notimeforheroes.game;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

	@Autowired
	GameRepository GameRepo;
	
	public Collection<Game> findAll(){
		return GameRepo.findAll();
	}
	
	public Optional<Game> findById(Integer id){
		return GameRepo.findById(id);
	}
}

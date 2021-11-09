package org.springframework.samples.notimeforheroes.player;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class PlayerService {

	@Autowired
	PlayerRepository playerRepository;
	
	@Transactional
	public Collection<Player> findAll(){
		return playerRepository.findAll();
	}
	
	@Transactional
	public Optional<Player> findById(Integer id){
		return playerRepository.findById(id);
	}
	
	@Transactional
	public void deletePlayer(Player player) {
		playerRepository.deleteById(player.getId());
		}
	
	@Transactional
	public void createPlayer(@Valid Player player) {
		playerRepository.save(player);
	}
}

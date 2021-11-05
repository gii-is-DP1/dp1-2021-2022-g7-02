package org.springframework.samples.notimeforheroes.jugadoresregistrados;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class RegisterPlayerService {

	@Autowired
	RegisterPlayerRepository registerPlayerRepo;
	
	@Transactional
	public Collection<RegisterPlayer> findAll(){
		return registerPlayerRepo.findAll();
	}
	
	@Transactional
	public Optional<RegisterPlayer> findById(Integer id){
		return registerPlayerRepo.findById(id);
	}
	
	@Transactional
	public void deleteRegisterPlayer(RegisterPlayer player) {
		registerPlayerRepo.deleteById(player.getId());
		}
	
	@Transactional
	public void createRegisterPlayer(@Valid RegisterPlayer player) {
		registerPlayerRepo.save(player);
	}
}

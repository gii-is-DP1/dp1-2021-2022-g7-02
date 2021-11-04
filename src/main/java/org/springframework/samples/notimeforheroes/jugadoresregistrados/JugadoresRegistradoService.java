package org.springframework.samples.notimeforheroes.jugadoresregistrados;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JugadoresRegistradoService {

	@Autowired
	JugadoresRegistradoRepository jugadoresRegistradoRepo;
	
	public Collection<JugadoresRegistrados> findAll(){
		return jugadoresRegistradoRepo.findAll();
	}
	
	public Optional<JugadoresRegistrados> findById(Integer id){
		return jugadoresRegistradoRepo.findById(id);
	}
}

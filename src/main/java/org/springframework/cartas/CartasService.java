package org.springframework.cartas;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartasService {

	@Autowired
	CartasRepository cartasrepo;
	
	public Collection<Cartas> findAll(){
		return cartasrepo.findAll();
	}
	
	public Optional<Cartas> findById(Integer id){
		return cartasrepo.findById(id);
	}
}

package org.springframework.samples.petclinic.cartalocalizacion;


import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/cartaloc")
public class CartaLocalizacionController{
	
	@Autowired
	private CartaLocalizacionService cartaLocalizacionService;
	
	@GetMapping()
	public String listadoCartasLocalizacion(ModelMap modelMap) {
		String vista = "cartaloc/listadoCartasLoc";
		Iterable<CartaLocalizacion> cartas = cartaLocalizacionService.findAll();
		modelMap.addAttribute("cartasLoc", cartas);
		return vista;
	}
	
	public static void main(String[] args) {
		
	}
	
}
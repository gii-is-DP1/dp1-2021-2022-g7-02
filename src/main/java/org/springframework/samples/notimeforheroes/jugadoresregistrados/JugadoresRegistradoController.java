package org.springframework.samples.notimeforheroes.jugadoresregistrados;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jugadores")
public class JugadoresRegistradoController {

	public static final String JUGADORES_REGISTRADOS_LISING = "jugadoresRegistrados/JugadoresRegistradosLising";
	public static final String JUGADORES_REGISTRADOS_FORM =  "jugadoresRegistrados/createOrUpdateJugadoresForm";
	
	@Autowired
	JugadoresRegistradoService jugadorRegistradoService;
	
	@GetMapping
	public String listJugadoresRegistrados(ModelMap model) {
		model.addAttribute("jugadores", jugadorRegistradoService.findAll());
		return JUGADORES_REGISTRADOS_LISING;
	}
}

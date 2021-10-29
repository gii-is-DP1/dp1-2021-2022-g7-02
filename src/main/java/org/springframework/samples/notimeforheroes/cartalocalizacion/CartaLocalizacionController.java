package org.springframework.samples.notimeforheroes.cartalocalizacion;


import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping()
public class CartaLocalizacionController{
	
	public static final String CARTA_LOCALIZACION_FORM="cartaloc/createOrUpdateCartaLocForm";
	public static final String CARTA_LOCALIZACION_LISTING="cartaloc/listadoCartasLoc";
	
	@Autowired
	private CartaLocalizacionService cartaLocalizacionService;
	
	@GetMapping({"/", "/cartasloc"})
	public String listadoCartasLocalizacion(ModelMap modelMap) {
		Iterable<CartaLocalizacion> cartas = cartaLocalizacionService.findAll();
		modelMap.addAttribute("cartasloc", cartas);
		return CARTA_LOCALIZACION_LISTING;
	}
	
	@GetMapping(value="cartasloc/{id}/edit")
	public String initUpdateCartaLocalizacionForm(@PathVariable("id") int id,ModelMap model) {
		
		Optional<CartaLocalizacion> carta=cartaLocalizacionService.findById(id);
		if(carta.isPresent()) {
			model.addAttribute("cartaloc",carta.get());
			return CARTA_LOCALIZACION_FORM;
		}else {
			model.addAttribute("message","No encontramos la carta que intentas editar!");
			return listadoCartasLocalizacion(model);
		}
	}
	
	@PostMapping(value="cartasloc/{id}/edit")
	public String processUpdateCartaLocalizacionForm(@PathVariable("id") int id, 
			@Valid CartaLocalizacion cartaModificada, BindingResult binding, ModelMap model) {
		Optional<CartaLocalizacion> cartaLocalizacion = cartaLocalizacionService.findById(id);
		if(binding.hasErrors()) {
			return CARTA_LOCALIZACION_FORM;
		}else {
			cartaModificada.setId(id);
			this.cartaLocalizacionService.save(cartaModificada);
			return "redirect:/cartasloc";
	}
	
	
}
	

}

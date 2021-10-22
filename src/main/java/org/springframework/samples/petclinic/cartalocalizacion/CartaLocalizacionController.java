package org.springframework.samples.petclinic.cartalocalizacion;


import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/cartasloc")
public class CartaLocalizacionController{
	
	public static final String CARTA_LOCALIZACION_FORM="cartaloc/createOrUpdateCartaLocForm";
	public static final String CARTA_LOCALIZACION_LISTING="cartaloc/listadoCartasLoc";
	
	@Autowired
	private CartaLocalizacionService cartaLocalizacionService;
	
	@GetMapping()
	public String listadoCartasLocalizacion(ModelMap modelMap) {
		System.out.println("EN PAGINA PRINCIPAL");
		Iterable<CartaLocalizacion> cartas = cartaLocalizacionService.findAll();
		modelMap.addAttribute("cartasloc", cartas);
		return CARTA_LOCALIZACION_LISTING;
	}
	
	@GetMapping("/{id}/edit")
	public String editCartaLoc(@PathVariable("id") int id,ModelMap model) {
		System.out.println("Hola");
		Optional<CartaLocalizacion> carta=cartaLocalizacionService.findById(id);
		if(carta.isPresent()) {
			model.addAttribute("carta",carta.get());
			return CARTA_LOCALIZACION_FORM;
		}else {
			model.addAttribute("message","No encontramos la carta que intentas editar!");
			return listadoCartasLocalizacion(model);
		}
	}
	
	@PostMapping("{id}/edit")
	public String editCartaLocalizacion(@PathVariable("id") int id, @Valid CartaLocalizacion cartaModificada, BindingResult binding, ModelMap model) {
		Optional<CartaLocalizacion> cartaLocalizacion = cartaLocalizacionService.findById(id);
		if(binding.hasErrors()) {
			return CARTA_LOCALIZACION_FORM;
		}else {
			BeanUtils.copyProperties(cartaModificada, cartaLocalizacion.get(), "id");
			cartaLocalizacionService.save(cartaLocalizacion.get());
			model.addAttribute("message","Carta modificada con éxito!");
			return listadoCartasLocalizacion(model);
		}
	}
	
}
/*


	@PostMapping("/{id}/edit")
	public String editDisease(@PathVariable("id") int id, @Valid Disease modifiedDisease, BindingResult binding, ModelMap model) {
		Optional<Disease> disease=diseasesService.findById(id);
		if(binding.hasErrors()) {			
			return DISEASES_FORM;
		}else {
			BeanUtils.copyProperties(modifiedDisease, disease.get(), "id");
			diseasesService.save(disease.get());
			model.addAttribute("message","Disease updated succesfully!");
			return listDiseases(model);
		}
	}

	@GetMapping("/{id}/delete")
	public String deleteDisease(@PathVariable("id") int id,ModelMap model) {
		Optional<Disease> disease=diseasesService.findById(id);
		if(disease.isPresent()) {
			diseasesService.delete(disease.get());
			model.addAttribute("message","The disease was deleted successfully!");
			return listDiseases(model);
		}else {
			model.addAttribute("message","We cannot find the disease you tried to delete!");
			return listDiseases(model);
		}
	}
}
 */

package org.springframework.samples.notimeforheroes.cards;

import java.util.Map;
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
@RequestMapping("/cartas")
public class CardsController {

	public static final String CARTAS_LISTING = "cartas/cartasListing";
	public static final String CARTAS_FORM =  "cartas/createOrUpdatecartasForm";
	
	@Autowired
	CardsService cartasService;
	
	@GetMapping
	public String listCartas(ModelMap model) {
		model.addAttribute("cartas", cartasService.findAll());
		return CARTAS_LISTING;
	}
	
	@GetMapping("/{id}/edit")
	public String editCarta(ModelMap model, @PathVariable("id") int id) {
		Optional<Cards> carta = cartasService.findById(id);
		if(carta.isPresent()) {
			model.addAttribute("cartas", carta.get());
			return CARTAS_FORM;
		} else {
			model.addAttribute("message", "This card doesn't exist");
			return listCartas(model);
		}
	}
	
	@PostMapping("/{id}/edit")
	public String editCarta(ModelMap model, @PathVariable("id") int id, @Valid Cards modifiedCartas, BindingResult result) {
		Optional<Cards> carta = cartasService.findById(id);
		if(result.hasErrors()) {
			model.addAttribute("message", "The card has errors");
			return CARTAS_FORM;
		} else {
			BeanUtils.copyProperties(modifiedCartas, carta.get(), "id");
			model.addAttribute("cartas", carta.get());
			return listCartas(model);
		}
	}
	
	@GetMapping("/new")
	public String newCarta(Map<String, Object> map) {
		Cards cartas= new Cards();
		map.put("cartas", cartas);
		return CARTAS_FORM;
	}
	
	@PostMapping("/new")
	public String newCarta(@Valid Cards carta,BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return CARTAS_FORM;
		} else {
			cartasService.createCarta(carta);
			model.addAttribute("message", "Carta created");
			return listCartas(model);
		}
	}
	
	@GetMapping("/{id}/delete")
	public String deleteCarta(ModelMap model, @PathVariable("id") int id) {
		Optional<Cards> carta = cartasService.findById(id);
		cartasService.deleteCarta(carta.get());
		model.addAttribute("message", "Card Deleted");
		return listCartas(model);
	}
	
	
}
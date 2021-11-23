package org.springframework.samples.notimeforheroes.heroecard;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/heroes")
public class HeroeCardsController {

	public static final String HEROE_CARD_LISTING = "heroes/heroeCardsListing";
	public static final String HEROE_CARD_FORM =  "heroes/createOrUpdateHeroeCardsForm";
	
	@Autowired
	HeroeCardsService HeroeCardService;
	
	
	@GetMapping
	public String listHeroesCards(ModelMap model) {
		model.addAttribute("heroes", HeroeCardService.findAll());
		return HEROE_CARD_LISTING;
	}
	
	
	@GetMapping("/{id}/edit")
	public String editHeroeCard(ModelMap model, @PathVariable("id") int id) {
		Optional<HeroeCards> heroe = HeroeCardService.findById(id);
		if(heroe.isPresent()) {
			model.addAttribute("heroes", heroe.get());
			return HEROE_CARD_FORM;
		} else {
			model.addAttribute("message", "This Heroe doesn't exist");
			return listHeroesCards(model);
		}
	}
	
	@PostMapping("/{id}/edit")
	public String editHeroeCard(RedirectAttributes redirect, ModelMap model, @PathVariable("id") int id, @Valid HeroeCards modifiedHeroe, BindingResult result) {
		Optional<HeroeCards> heroe = HeroeCardService.findById(id);
		if(result.hasErrors()) {
			model.addAttribute("message", "The heroe has errors");
			return HEROE_CARD_FORM;
		} else {
			BeanUtils.copyProperties(modifiedHeroe, heroe.get(), "id");
			model.addAttribute("heroes", heroe.get());
			listHeroesCards(model);
			redirect.addFlashAttribute("message", "Heroe modified");
			return "redirect:/heroes";
		}
	}
	
	@GetMapping("/new")
	public String newHeroeCard(Map<String, Object> map) {
		HeroeCards heroe= new HeroeCards();
		map.put("heroes", heroe);
		return HEROE_CARD_FORM;
	}
	
	@PostMapping("/new")
	public String newHeroeCard(RedirectAttributes redirect, @Valid HeroeCards heroe,BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return HEROE_CARD_FORM;
		} else {
			HeroeCardService.createHeroeCard(heroe);
			model.addAttribute("message", "Heroe created");
			listHeroesCards(model);
			redirect.addFlashAttribute("message", "Heroe created");
			return "redirect:/heroes";
		}
	}
	
	@GetMapping("/{id}/delete")
	public String deleteHeroeCard(RedirectAttributes redirect,ModelMap model, @PathVariable("id") int id) {
		Optional<HeroeCards> heroe = HeroeCardService.findById(id);
		HeroeCardService.deleteHeroeCard(heroe.get());
		listHeroesCards(model);
		redirect.addFlashAttribute("message", "Heroe deleted");
		return "redirect:/heroes";

	}
	
	
}
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cards")
public class CardsController {

	public static final String CARDS_LISTING = "cards/cardsListing";
	public static final String CARDS_FORM =  "cards/createOrUpdatecardsForm";
	
	@Autowired
	CardsService cardsService;
	
	
	@GetMapping
	public String listCards(ModelMap model) {
		model.addAttribute("cards", cardsService.findAll());
		return CARDS_LISTING;
	}
	
	@GetMapping("/{type}")
	public String listCardsType(ModelMap model, @PathVariable("type") String type) {
		model.addAttribute("cards", cardsService.findByType(type));
		return CARDS_LISTING;
	}
	
	@GetMapping("/{id}/edit")
	public String editCard(ModelMap model, @PathVariable("id") int id) {
		Optional<Cards> card = cardsService.findById(id);
		if(card.isPresent()) {
			model.addAttribute("cards", card.get());
			return CARDS_FORM;
		} else {
			model.addAttribute("message", "This card doesn't exist");
			return listCards(model);
		}
	}
	
	@PostMapping("/{id}/edit")
	public String editCard(RedirectAttributes redirect, ModelMap model, @PathVariable("id") int id, @Valid Cards modifiedCards, BindingResult result) {
		Optional<Cards> card = cardsService.findById(id);
		if(result.hasErrors()) {
			model.addAttribute("message", "The card has errors");
			return CARDS_FORM;
		} else {
			BeanUtils.copyProperties(modifiedCards, card.get(), "id");
			model.addAttribute("cards", card.get());
			listCards(model);
			redirect.addFlashAttribute("message", "Card modified");
			return "redirect:/cards";
		}
	}
	
	@GetMapping("/new")
	public String newCard(Map<String, Object> map) {
		Cards cards= new Cards();
		map.put("cards", cards);
		return CARDS_FORM;
	}
	
	@PostMapping("/new")
	public String newCard(RedirectAttributes redirect, @Valid Cards card,BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return CARDS_FORM;
		} else {
			cardsService.createCard(card);
			model.addAttribute("message", "Card created");
			listCards(model);
			redirect.addFlashAttribute("message", "Card created");
			return "redirect:/cards";
		}
	}
	
	@GetMapping("/{id}/delete")
	public String deleteCarta(RedirectAttributes redirect,ModelMap model, @PathVariable("id") int id) {
		Optional<Cards> card = cardsService.findById(id);
		cardsService.deleteCard(card.get());
		listCards(model);
		redirect.addFlashAttribute("message", "Card deleted");
		return "redirect:/cards";

	}
	
	
}
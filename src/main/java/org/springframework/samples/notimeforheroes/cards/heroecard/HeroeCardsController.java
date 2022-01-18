package org.springframework.samples.notimeforheroes.cards.heroecard;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyCard;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cards/heroes")
public class HeroeCardsController {

	public static final String HEROE_CARD_LISTING = "cards/heroes/heroeCardsListing";
	public static final String HEROE_CARD_FORM =  "cards/heroes/createOrUpdateHeroeCardsForm";
	
	@Autowired
	HeroeCardsService HeroeCardService;
	
	
	@GetMapping("/{pageNo}")
	public String getAll(ModelMap model, @PathVariable("pageNo") Integer pageNo){
		Integer lastPage = HeroeCardService.findAll().size()/4;
		model.addAttribute("lastPag", lastPage);
		Collection<HeroeCard> lista = HeroeCardService.findAllPage(pageNo, 4);
		model.addAttribute("heroes", lista);
		model.addAttribute("pag", pageNo);

		return HEROE_CARD_LISTING;
	}
	
	@GetMapping
	public String listHeroesCards(ModelMap model) {
		model.addAttribute("heroes", HeroeCardService.findAll());
		return HEROE_CARD_LISTING;
	}
	
	
	@GetMapping("/{id}/edit")
	public String editHeroeCard(ModelMap model, @PathVariable("id") int id) {
		Optional<HeroeCard> heroe = HeroeCardService.findById(id);
		if(heroe.isPresent()) {
			model.addAttribute("heroes", heroe.get());
			return HEROE_CARD_FORM;
		} else {
			model.addAttribute("message", "This Heroe doesn't exist");
			return listHeroesCards(model);
		}
	}
	
	@PostMapping("/{id}/edit")
	public String editHeroeCard(RedirectAttributes redirect, ModelMap model, @PathVariable("id") int id, @Valid HeroeCard modifiedHeroe, BindingResult result) {
		Optional<HeroeCard> heroe = HeroeCardService.findById(id);
		if(result.hasErrors()) {
			model.addAttribute("message", "The heroe has errors");
			return HEROE_CARD_FORM;
		} else {
			BeanUtils.copyProperties(modifiedHeroe, heroe.get(), "id");
			model.addAttribute("heroes", heroe.get());
			listHeroesCards(model);
			redirect.addFlashAttribute("message", "Heroe modified");
			return "redirect:/cards/heroes";
		}
	}
	
	@GetMapping("/new")
	public String newHeroeCard(Map<String, Object> map) {
		HeroeCard heroe= new HeroeCard();
		map.put("heroes", heroe);
		return HEROE_CARD_FORM;
	}
	
	@PostMapping("/new")
	public String newHeroeCard(RedirectAttributes redirect, @Valid HeroeCard heroe,BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return HEROE_CARD_FORM;
		} else {
			HeroeCardService.createHeroeCard(heroe);
			model.addAttribute("message", "Heroe created");
			listHeroesCards(model);
			redirect.addFlashAttribute("message", "Heroe created");
			return "redirect:/cards/heroes";
		}
	}
	
	@GetMapping("/{id}/delete")
	public String deleteHeroeCard(RedirectAttributes redirect,ModelMap model, @PathVariable("id") int id) {
		Optional<HeroeCard> heroe = HeroeCardService.findById(id);
		HeroeCardService.deleteHeroeCard(heroe.get());
		listHeroesCards(model);
		redirect.addFlashAttribute("message", "Heroe deleted");
		return "redirect:/cards/heroes";

	}
	
	
}
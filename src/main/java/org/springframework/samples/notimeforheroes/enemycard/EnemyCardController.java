package org.springframework.samples.notimeforheroes.enemycard;

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
@RequestMapping("/enemies")
public class EnemyCardController {

	public static final String ENEMY_CARD_LISTING = "enemies/enemiesCardsListing";
	public static final String ENEMY_CARD_FORM =  "enemies/createOrUpdateEnemiesCardsForm";
	
	@Autowired
	EnemyCardService EnemyCardService;
	
	
	@GetMapping
	public String listEnemiesCards(ModelMap model) {
		model.addAttribute("enemies", EnemyCardService.findAll());
		return ENEMY_CARD_LISTING;
	}
	
	
	@GetMapping("/{id}/edit")
	public String editEnemyCard(ModelMap model, @PathVariable("id") int id) {
		Optional<EnemyCard> enemy = EnemyCardService.findById(id);
		if(enemy.isPresent()) {
			model.addAttribute("enemies", enemy.get());
			return ENEMY_CARD_FORM;
		} else {
			model.addAttribute("message", "This Heroe doesn't exist");
			return listEnemiesCards(model);
		}
	}
	
	@PostMapping("/{id}/edit")
	public String editHeroeCard(RedirectAttributes redirect, ModelMap model, @PathVariable("id") int id, @Valid EnemyCard modifiedHeroe, BindingResult result) {
		Optional<EnemyCard> enemy = EnemyCardService.findById(id);
		if(result.hasErrors()) {
			model.addAttribute("message", "The enemy has errors");
			return ENEMY_CARD_FORM;
		} else {
			BeanUtils.copyProperties(modifiedHeroe, enemy.get(), "id");
			model.addAttribute("enemies", enemy.get());
			listEnemiesCards(model);
			redirect.addFlashAttribute("message", "enemy modified");
			return "redirect:/enemies";
		}
	}
	
	@GetMapping("/new")
	public String newEnemyCard(Map<String, Object> map) {
		EnemyCard enemy= new EnemyCard();
		map.put("enemies", enemy);
		return ENEMY_CARD_FORM;
	}
	
	@PostMapping("/new")
	public String newEnemyCard(RedirectAttributes redirect, @Valid EnemyCard enemy,BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return ENEMY_CARD_FORM;
		} else {
			EnemyCardService.createEnemyCard(enemy);
			model.addAttribute("message", "enemy created");
			listEnemiesCards(model);
			redirect.addFlashAttribute("message", "enemy created");
			return "redirect:/enemies";
		}
	}
	
	@GetMapping("/{id}/delete")
	public String deleteEnemyCard(RedirectAttributes redirect,ModelMap model, @PathVariable("id") int id) {
		Optional<EnemyCard> enemy = EnemyCardService.findById(id);
		EnemyCardService.deleteEnemyCard(enemy.get());
		listEnemiesCards(model);
		redirect.addFlashAttribute("message", "Heroe deleted");
		return "redirect:/enemies";

	}
	
	
}

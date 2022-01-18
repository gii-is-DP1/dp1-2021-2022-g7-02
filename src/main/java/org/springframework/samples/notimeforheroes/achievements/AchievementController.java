package org.springframework.samples.notimeforheroes.achievements;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCard;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/achievements")
public class AchievementController {


	public static final String ACHIEVEMENTS_LISTING = "achievements/achievementsListing";
	public static final String ACHIEVEMENTS_FORM =  "achievements/createOrUpdateAchievementsForm";
	
	@Autowired
	AchievementService achievementService;
	
	
	@GetMapping("/{pageNo}")
	public String getAll(ModelMap model, @PathVariable("pageNo") Integer pageNo){
		Collection<Achievement> lista = achievementService.findAllPage(pageNo, 3);
		Integer lastPage = achievementService.findAll().size()/3;
		model.addAttribute("achievement", lista);
		model.addAttribute("pag", pageNo);
		model.addAttribute("lastPag", lastPage);
		model.addAttribute("results", achievementService.achievedAchievement());
		return ACHIEVEMENTS_LISTING;
	}
	
	
	@GetMapping
	public String listAchievements(ModelMap model) {
		model.addAttribute("achievement", achievementService.findAll());
		model.addAttribute("results", achievementService.achievedAchievement());
		return ACHIEVEMENTS_LISTING;
		
	}
	
	@GetMapping("/{id}/edit")
	public String editAchievement(ModelMap model, @PathVariable("id") int id) {
		Optional<Achievement> achievement = achievementService.findById(id);
		if(achievement.isPresent()) {
			model.addAttribute("achievement", achievement.get());
			return ACHIEVEMENTS_FORM;
		} else {
			model.addAttribute("message", "This achievement doesn't exits");
			return listAchievements(model);
		}
	}
	
	@PostMapping("/{id}/edit")
	public String editAchievement(RedirectAttributes redirect,ModelMap model, @PathVariable("id") int id, @Valid Achievement modifiedAchivement, BindingResult result) {
		Optional<Achievement> achievement = achievementService.findById(id);
		if(result.hasErrors()) {
			model.addAttribute("message", "The achievement has error");
			return ACHIEVEMENTS_FORM;
		} else {
			BeanUtils.copyProperties(modifiedAchivement, achievement.get(), "id");
			model.addAttribute("achievement", achievement.get());
			listAchievements(model);
			redirect.addFlashAttribute("message", "Achievement modified");
			return "redirect:/achievements";
		}
	}
	
	@GetMapping("/new")
	public String newAchievement(Map<String, Object> map) {
		Achievement achievement = new Achievement();
		map.put("achievement", achievement);
		return ACHIEVEMENTS_FORM;
	}
	
	@PostMapping("/new")
	public String newAchievement(RedirectAttributes redirect,@Valid Achievement achievemets, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return ACHIEVEMENTS_FORM;
		} else {
			achievementService.createAchievement(achievemets);
			redirect.addFlashAttribute("message", "Achievement created");
			return "redirect:/achievements";
		}
	}
	
	@GetMapping("/{id}/delete")
	public String deleteAchievement(RedirectAttributes redirect,ModelMap model, @PathVariable("id") int id) {
		Optional<Achievement> achievement = achievementService.findById(id);
		achievementService.deleteAchievement(achievement.get());
		listAchievements(model);
		redirect.addFlashAttribute("message", "Achievement deleted");
		return "redirect:/achievements";
	}
	
}


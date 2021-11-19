package org.springframework.samples.notimeforheroes.achievements;

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
@RequestMapping("/achievements")
public class AchievementControler {


	public static final String ACHIEVEMENTS_LISTING = "achievements/achievementsListing";
	public static final String ACHIEVEMENTS_FORM =  "achievements/createOrUpdateAchievementsForm";
	
	@Autowired
	AchievementService achievementService;
	
	@GetMapping
	public String listAchievements(ModelMap model) {
		model.addAttribute("achievements", achievementService.findAll());
		return ACHIEVEMENTS_LISTING;
		
	}
	
	@GetMapping("/{id}/edit")
	public String editAchievement(ModelMap model, @PathVariable("id") int id) {
		Optional<Achievement> achievement = achievementService.findById(id);
		if(achievement.isPresent()) {
			model.addAttribute("achievements", achievement.get());
			return ACHIEVEMENTS_FORM;
		} else {
			model.addAttribute("message", "This achievement doesn't exits");
			return listAchievements(model);
		}
	}
	
	@PostMapping("/{id}/edit")
	public String editAchievement(ModelMap model, @PathVariable("id") int id, @Valid Achievement modifiedAchivement, BindingResult result) {
		Optional<Achievement> achievement = achievementService.findById(id);
		if(result.hasErrors()) {
			model.addAttribute("message", "The achievement has error");
			return ACHIEVEMENTS_FORM;
		} else {
			BeanUtils.copyProperties(modifiedAchivement, achievement.get(), "id");
			model.addAttribute("achievements", achievement.get());
			listAchievements(model);
			return "redirect:/achievements";
		}
	}
	
	@GetMapping("/new")
	public String newAchievement(Map<String, Object> map) {
		Achievement achievement = new Achievement();
		map.put("achievements", achievement);
		return ACHIEVEMENTS_FORM;
	}
	
	@PostMapping("/new")
	public String newAchievement(@Valid Achievement achievemets, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return ACHIEVEMENTS_FORM;
		} else {
			achievementService.createAchievement(achievemets);
			model.addAttribute("message", "Achievement created");
			return "redirect:/achievements";
		}
	}
	
	@GetMapping("/{id}/delete")
	public String deleteAchievement(ModelMap model, @PathVariable("id") int id) {
		Optional<Achievement> achievement = achievementService.findById(id);
		achievementService.deleteAchievement(achievement.get());
		model.addAttribute("message", "Achievement deleted");
		listAchievements(model);
		return "redirect:/achievements";
	}
	
}


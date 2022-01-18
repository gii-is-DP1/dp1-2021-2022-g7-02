package org.springframework.samples.notimeforheroes.achievements;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/achievements")
public class AchievementController {


	public static final String ACHIEVEMENTS_LISTING = "achievements/achievementsListing";
	
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

	
}


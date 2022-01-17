package org.springframework.samples.notimeforheroes.achievements;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
		model.addAttribute("achievement", achievementService.findAll());
		model.addAttribute("results", achievementService.achievedAchievement());
		return ACHIEVEMENTS_LISTING;
	}
	
}


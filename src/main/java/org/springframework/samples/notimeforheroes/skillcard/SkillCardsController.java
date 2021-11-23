package org.springframework.samples.notimeforheroes.skillcard;

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
@RequestMapping("/skills")
public class SkillCardsController {

	public static final String SKILL_CARD_LISTING = "skills/skillCardsListing";
	public static final String SKILL_CARD_FORM =  "skills/createOrUpdateSkillCardsForm";
	
	
	@Autowired
	SkillCardsService SkillService;
	
	@GetMapping
	public String listSkillCard(ModelMap model) {
		model.addAttribute("skills", SkillService.findAll());
		return SKILL_CARD_LISTING;
	}
	
	@GetMapping("/{id}/edit")
	public String editSkillCard(ModelMap model, @PathVariable("id") int id) {
		Optional<SkillCards> skill = SkillService.findById(id);
		if(skill.isPresent()) {
			model.addAttribute("skills", skill.get());
			return SKILL_CARD_FORM;
		} else {
			model.addAttribute("message", "This skill doesn't exist");
			return listSkillCard(model);
		}
	}
	
	@PostMapping("/{id}/edit")
	public String editSceneCard(RedirectAttributes redirect, ModelMap model, @PathVariable("id") int id, @Valid SkillCards modifiedSkill, BindingResult result) {
		Optional<SkillCards> skill = SkillService.findById(id);
		if(result.hasErrors()) {
			model.addAttribute("message", "The skill has errors");
			return SKILL_CARD_FORM;
		} else {
			BeanUtils.copyProperties(modifiedSkill, skill.get(), "id");
			model.addAttribute("skills", skill.get());
			listSkillCard(model);
			redirect.addFlashAttribute("message", "Skill modified");
			return "redirect:/skills";
		}
	}
	
	@GetMapping("/new")
	public String newSceneCard(Map<String, Object> map) {
		SkillCards skill= new SkillCards();
		map.put("skills", skill);
		return SKILL_CARD_FORM;
	}
	
	@PostMapping("/new")
	public String newSceneCard(RedirectAttributes redirect, @Valid SkillCards skill,BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return SKILL_CARD_FORM;
		} else {
			SkillService.saveSkillCard(skill);
			model.addAttribute("message", "Skill created");
			listSkillCard(model);
			redirect.addFlashAttribute("message", "Skill created");
			return "redirect:/skills";
		}
	}
	
	@GetMapping("/{id}/delete")
	public String deleteSceneCard(RedirectAttributes redirect,ModelMap model, @PathVariable("id") int id) {
		Optional<SkillCards> skill = SkillService.findById(id);
		SkillService.deleteSkillCard(skill.get());
		listSkillCard(model);
		redirect.addFlashAttribute("message", "Skill deleted");
		return "redirect:/skills";

	}
	
}

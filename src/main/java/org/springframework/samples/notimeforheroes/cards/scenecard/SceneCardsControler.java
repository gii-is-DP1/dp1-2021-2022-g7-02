package org.springframework.samples.notimeforheroes.cards.scenecard;

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
@RequestMapping("/scenes")
public class SceneCardsControler {

	public static final String SCENE_CARD_LISTING = "scenes/sceneCardsListing";
	public static final String SCENE_CARD_FORM =  "scenes/createOrUpdateSceneCardsForm";
	
	@Autowired
	SceneCardsService SceneService;
	
	@GetMapping
	public String listSceneCard(ModelMap model) {
		model.addAttribute("scenes", SceneService.findAll());
		return SCENE_CARD_LISTING;
	}
	
	@GetMapping("/{id}/edit")
	public String editSceneCard(ModelMap model, @PathVariable("id") int id) {
		Optional<SceneCard> scene = SceneService.findById(id);
		if(scene.isPresent()) {
			model.addAttribute("scenes", scene.get());
			return SCENE_CARD_FORM;
		} else {
			model.addAttribute("message", "This Scene doesn't exist");
			return listSceneCard(model);
		}
	}
	
	@PostMapping("/{id}/edit")
	public String editSceneCard(RedirectAttributes redirect, ModelMap model, @PathVariable("id") int id, @Valid SceneCard modifiedScene, BindingResult result) {
		Optional<SceneCard> scene = SceneService.findById(id);
		if(result.hasErrors()) {
			model.addAttribute("message", "The Scene has errors");
			return SCENE_CARD_FORM;
		} else {
			BeanUtils.copyProperties(modifiedScene, scene.get(), "id");
			model.addAttribute("scene", scene.get());
			listSceneCard(model);
			redirect.addFlashAttribute("message", "Scene modified");
			return "redirect:/scenes";
		}
	}
	
	@GetMapping("/new")
	public String newSceneCard(Map<String, Object> map) {
		SceneCard scene= new SceneCard();
		map.put("scenes", scene);
		return SCENE_CARD_FORM;
	}
	
	@PostMapping("/new")
	public String newSceneCard(RedirectAttributes redirect, @Valid SceneCard scene,BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return SCENE_CARD_FORM;
		} else {
			SceneService.saveSceneCard(scene);
			model.addAttribute("message", "Scene created");
			listSceneCard(model);
			redirect.addFlashAttribute("message", "Scene created");
			return "redirect:/scenes";
		}
	}
	
	@GetMapping("/{id}/delete")
	public String deleteSceneCard(RedirectAttributes redirect,ModelMap model, @PathVariable("id") int id) {
		Optional<SceneCard> scene = SceneService.findById(id);
		SceneService.deleteSceneCard(scene.get());
		listSceneCard(model);
		redirect.addFlashAttribute("message", "Scene deleted");
		return "redirect:/scenes";

	}
	
}

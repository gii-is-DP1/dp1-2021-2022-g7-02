package org.springframework.samples.notimeforheroes.jugadoresregistrados;

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
@RequestMapping("/players")
public class RegisterPlayerController {

	public static final String REGISTER_PLAYER_LISING = "RegisterPlayer/RegisterPlayerLising";
	public static final String REGISTER_PLAYER_FORM =  "RegisterPlayer/createOrUpdatePlayerForm";
	
	@Autowired
	RegisterPlayerService RegisterPlayerService;
	
	@GetMapping
	public String listRegisterPlayer(ModelMap model) {
		model.addAttribute("players", RegisterPlayerService.findAll());
		return REGISTER_PLAYER_LISING;
	}
	
	@GetMapping("/{id}/edit")
	public String editPlayer(ModelMap model, @PathVariable("id") int id) {
		Optional<RegisterPlayer> player = RegisterPlayerService.findById(id);
		if(player.isPresent()) {
			model.addAttribute("players", player.get());
			return REGISTER_PLAYER_FORM;
		} else {
			model.addAttribute("message", "This player doesn't exits");
			return listRegisterPlayer(model);
		}
	}
	
	@PostMapping("/{id}/edit")
	public String editPlayer(ModelMap model, @PathVariable("id") int id, @Valid RegisterPlayer modifiedPlayer, BindingResult result) {
		Optional<RegisterPlayer> player = RegisterPlayerService.findById(id);
		if(result.hasErrors()) {
			model.addAttribute("message", "The player has errors");
			return REGISTER_PLAYER_FORM;
		} else {
			BeanUtils.copyProperties(modifiedPlayer, player.get(), "id");
			model.addAttribute("players", player.get());
			return listRegisterPlayer(model);
		}
	}
	
	@GetMapping("/new")
	public String newPlayer(Map<String, Object> map) {
		RegisterPlayer player = new RegisterPlayer();
		map.put("players", player);
		return REGISTER_PLAYER_FORM;
	}
	
	@PostMapping("/new")
	public String newPlayer(@Valid RegisterPlayer player,BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return REGISTER_PLAYER_FORM;
		} else {
			RegisterPlayerService.createRegisterPlayer(player);
			model.addAttribute("message", "Player created");
			return listRegisterPlayer(model);
		}
	}
	
	@GetMapping("/{id}/delete")
	public String deletePlayer(ModelMap model, @PathVariable("id") int id) {
		Optional<RegisterPlayer> player = RegisterPlayerService.findById(id);
		RegisterPlayerService.deleteRegisterPlayer(player.get());
		model.addAttribute("message", "Player Deleted");
		return listRegisterPlayer(model);
	}
	
	
}

package org.springframework.samples.notimeforheroes.registerplayer;

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
public class PlayerController {

	public static final String PLAYER_LISTING = "players/playerListing";
	public static final String PLAYER_FORM =  "players/createOrUpdatePlayerForm";
	
	@Autowired
	PlayerService playerService;
	
	@GetMapping
	public String listPlayers(ModelMap model) {
		model.addAttribute("players", playerService.findAll());
		return PLAYER_LISTING;
	}
	
	@GetMapping("/{id}/edit")
	public String editPlayer(ModelMap model, @PathVariable("id") int id) {
		Optional<Player> player = playerService.findById(id);
		if(player.isPresent()) {
			model.addAttribute("players", player.get());
			return PLAYER_FORM;
		} else {
			model.addAttribute("message", "This player doesn't exist");
			return listPlayers(model);
		}
	}
	
	@PostMapping("/{id}/edit")
	public String editPlayer(ModelMap model, @PathVariable("id") int id, @Valid Player modifiedPlayer, BindingResult result) {
		Optional<Player> player = playerService.findById(id);
		if(result.hasErrors()) {
			model.addAttribute("message", "The player has errors");
			return PLAYER_FORM;
		} else {
			BeanUtils.copyProperties(modifiedPlayer, player.get(), "id");
			model.addAttribute("players", player.get());
			return listPlayers(model);
		}
	}
	
	@GetMapping("/new")
	public String newPlayer(Map<String, Object> map) {
		Player player = new Player();
		map.put("players", player);
		return PLAYER_FORM;
	}
	
	@PostMapping("/new")
	public String newPlayer(@Valid Player player,BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return PLAYER_FORM;
		} else {
			playerService.createRegisterPlayer(player);
			model.addAttribute("message", "Player created");
			return listPlayers(model);
		}
	}
	
	@GetMapping("/{id}/delete")
	public String deletePlayer(ModelMap model, @PathVariable("id") int id) {
		Optional<Player> player = playerService.findById(id);
		playerService.deleteRegisterPlayer(player.get());
		model.addAttribute("message", "Player Deleted");
		return listPlayers(model);
	}
	
	
}

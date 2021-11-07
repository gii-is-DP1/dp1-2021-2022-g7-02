package org.springframework.samples.notimeforheroes.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.game.GameService;
import org.springframework.samples.notimeforheroes.registerplayer.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class GameController {
	
	public static final String GAMES_LISTING = "games/listadoGames";

	@Autowired
	GameService gameService;
	Player playerService;
	
	@GetMapping("/games")
	public String listGames(ModelMap model) {
		model.addAttribute("games", gameService.findAll());
		return GAMES_LISTING;
	}
	
	@GetMapping("/games/ended")
	public String listEndedGames(ModelMap model) {
		model.addAttribute("games", gameService.findAllEnded());
		return GAMES_LISTING;
	}
}

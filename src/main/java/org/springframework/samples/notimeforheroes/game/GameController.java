package org.springframework.samples.notimeforheroes.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.game.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game")
public class GameController {
	
	public static final String GAMES_LISTING = "games/gamesListing";

	@Autowired
	GameService gameService;
	
	@GetMapping
	public String listGames(ModelMap model) {
		model.addAttribute("game", gameService.findAll());
		return GAMES_LISTING;
	}
}

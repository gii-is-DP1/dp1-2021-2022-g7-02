package org.springframework.samples.notimeforheroes.game;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.game.exceptions.NotAuthenticatedError;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/games")
public class GameController {
	
	public static final String GAMES_LISTING = "games/listadoGames";
	public static final String GAMES_FORM = "games/createOrUpdateGamesForm";
	public static final String GAMES_WAITING_FOR_PLAYERS = "games/waitingForPlayers";


	@Autowired
	GameService gameService;

	@Autowired
	UserService userService;
	
	@GetMapping()
	public String listGames(ModelMap model) {
		model.addAttribute("games", gameService.findAll());
		return GAMES_LISTING;
	}
	
	@GetMapping("/ended")
	public String listEndedGames(ModelMap model) {
		model.addAttribute("games", gameService.findAllEnded());
		return GAMES_LISTING;
	}
	
	@GetMapping("/notEnded")
	public String listNotEndedGames(ModelMap model) {
		model.addAttribute("games", gameService.findAllByIsInProgress());
		return GAMES_LISTING;
	}
	
	@GetMapping("/new")
	public String newGame(Map<String, Object> map) {
		
		Game game = new Game();
		map.put("games", game);
		return GAMES_FORM;
	}
	
	@PostMapping("/new")
	public String newGame(@Valid Game game, BindingResult result, ModelMap model) throws NotAuthenticatedError {
		if(result.hasErrors()) {
			System.out.println("ERRORES: " + result.getErrorCount());
			result.getAllErrors().forEach(error -> System.out.println(error.toString()));
			return GAMES_FORM;
		}else {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(!auth.getPrincipal().toString().equals("anonymousUser")){		//Si estamos logeados
				try {
					gameService.createGame(game);
					model.addAttribute("message", "Game created");
					model.addAttribute("users",userService.findAllInGame(game));
					
				} catch (Exception e) {
					model.addAttribute("message", "ERROR: Partida no creada");
					e.printStackTrace();
				}	
			}else{																//Si no estamos logeados
				model.addAttribute("message", "ERROR: Usuario no identificado");
			}

			
			return GAMES_WAITING_FOR_PLAYERS;
		}
	}
	
}
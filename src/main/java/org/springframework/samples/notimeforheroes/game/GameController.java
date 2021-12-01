package org.springframework.samples.notimeforheroes.game;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.game.exceptions.NotAuthenticatedError;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/games")
public class GameController {
	
	public static final String GAMES_LISTING = "games/listadoGames";
	public static final String GAMES_FORM = "games/createOrUpdateGamesForm";
	public static final String GAMES_WAITING_FOR_PLAYERS = "games/waitingForPlayers";
	public static final String GAMES_JOIN = "games/joinGame";
	public static final String GAMES_DETAILS = "games/gameDetails";
	public static final Integer MAX_NUMBER_PLAYERS = 4;


	@Autowired
	GameService gameService;

	@Autowired
	UserService userService;
	
	@GetMapping()
	public String listGames(ModelMap model) {
		model.addAttribute("games", gameService.findAvailableGames());
		model.addAttribute("user", userService.getLoggedUser());
		return GAMES_LISTING;
	}

	@GetMapping("/details/{gameId}")
	public String gameDetails(ModelMap model, @PathVariable("gameId") int gameId){
		Game game = gameService.findById(gameId).get();
		model.addAttribute("game", game);
		model.addAttribute("users", userService.findAllInGame(game));
		return GAMES_DETAILS;
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
					
				} catch (Exception e) {
					model.addAttribute("message", "ERROR: Partida no creada");
					e.printStackTrace();
					return GAMES_FORM;
				}	
			}else{																//Si no estamos logeados
				model.addAttribute("message", "ERROR: Usuario no identificado");
				return GAMES_FORM;
			}

			
			return "redirect:/games/waiting/" + game.getId();
		}
	}

	@GetMapping("/join")
	public String joinGame(ModelMap model){
		User loggedUser = userService.getLoggedUser();
		model.addAttribute("user", loggedUser);
		return GAMES_JOIN;
	}

	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinGame(ModelMap model, @RequestParam("joinCode") String joinCode){
		Game game = gameService.findByJoinCode(joinCode).orElse(null);

		if(game.getUsers().size() < MAX_NUMBER_PLAYERS){
			User loggedUser = userService.getLoggedUser();
			game.getUsers().add(loggedUser);
			gameService.updateGame(game);
			return "redirect:/games/waiting/" + game.getId();
		}else{
			model.addAttribute("message", "Game full!");
			return GAMES_JOIN;
		}
		
	}

	@GetMapping("/waiting/{gameId}")
	public String waitingGame(ModelMap model, @PathVariable("gameId") int gameId, HttpServletResponse response){
		
		response.addHeader("Refresh", "10");
		Game game = gameService.findById(gameId).get();
		model.addAttribute("game",game);
		model.addAttribute("users",userService.findAllInGame(game));
		return GAMES_WAITING_FOR_PLAYERS;
	}
	
}
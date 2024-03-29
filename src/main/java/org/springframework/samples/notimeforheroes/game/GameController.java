package org.springframework.samples.notimeforheroes.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.swing.Timer;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyCard;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyCardService;
import org.springframework.samples.notimeforheroes.cards.enemycard.gamesEnemies.GamesEnemiesService;
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCard;
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCardsService;
import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCardsService;
import org.springframework.samples.notimeforheroes.cards.marketcard.gamesMarket.GameMarketService;
import org.springframework.samples.notimeforheroes.cards.skillcard.SkillCard;
import org.springframework.samples.notimeforheroes.cards.skillcard.SkillCardsService;
import org.springframework.samples.notimeforheroes.cards.skillcard.gamesUsersSkillcards.GamesUsersSkillCardsService;
import org.springframework.samples.notimeforheroes.game.exceptions.CardNotSelectedException;
import org.springframework.samples.notimeforheroes.game.exceptions.DontHaveEnoughGoldToBuyException;
import org.springframework.samples.notimeforheroes.game.exceptions.GameAlreadyStartedException;
import org.springframework.samples.notimeforheroes.game.exceptions.GameCurrentNotUniqueException;
import org.springframework.samples.notimeforheroes.game.exceptions.GameFullException;
import org.springframework.samples.notimeforheroes.game.exceptions.HeroeNotAvailableException;
import org.springframework.samples.notimeforheroes.game.exceptions.IncorrectNumberOfEnemiesException;
import org.springframework.samples.notimeforheroes.game.exceptions.ItemNotSelectedException;
import org.springframework.samples.notimeforheroes.game.exceptions.NotAuthenticatedError;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUser;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUserService;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.samples.notimeforheroes.user.UserGlory;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/games")
public class GameController { 

	public static final String GAMES_LISTING = "games/listadoGames";
	public static final String GAMES_FORM = "games/createOrUpdateGamesForm";
	public static final String GAMES_WAITING_FOR_PLAYERS = "games/waitingForPlayers";
	public static final String GAMES_JOIN = "games/joinGame";
	public static final String GAMES_DETAILS = "games/gameDetails";
	public static final String GAMES_SELECT_HEROE = "games/selectHeroe";
	public static final String GAMES_PLAYING = "games/gamePlaying";
	public static final String SELECT_PLAYER_TO_START = "games/selectPlayerToStart";
	public static final String ATTACK_VIEW = "games/attackGame";
	public static final String MARKET_VIEW = "games/marketGame";
	public static final String GAMES_WINNER = "games/endGame";
	public static final String RANKING = "games/playersRanking";
	public static final String RANKING_HEROES = "games/playersRankingHeroes";
	public static final String DEFEND_VIEW = "games/defendGame";
	public static Integer TIME_INICIO = 0;

	@Autowired
	GameService gameService;

	@Autowired
	UserService userService;

	@Autowired
	GameUserService gameUserService;

	@Autowired
	HeroeCardsService heroeCardsService;

	@Autowired
	GameRepository gameRepository;

	@Autowired
	MarketCardsService marketService;

	@Autowired
	GameMarketService gameMarketService;

	@Autowired
	SkillCardsService skillCardsService;

	@Autowired
	EnemyCardService enemyCardService;

	@Autowired
	GamesEnemiesService gamesEnemiesService;

	@Autowired
	GamesUsersSkillCardsService gameUserSkillCardsService;
	
	
	public long timer() {
		long startTime = System.currentTimeMillis();
		return startTime;
	}
	
	@GetMapping()
	public String listGames(ModelMap model) {
		model.addAttribute("games", gameService.findAvailableGames());
		model.addAttribute("user", userService.getLoggedUser());
		return GAMES_LISTING;
	}

	@GetMapping("/playersRanking")
	public String listRanking(ModelMap model) {
		model.addAttribute("ranking", gameService.findRanking());
		return RANKING;
	}
	
	@GetMapping("/playersRankingHeroes")
	public String listRankingHeroes(ModelMap model) {
		model.addAttribute("rankingHeroes", gameService.findRankingHeroes());
		return RANKING_HEROES;
	}
	
	
	

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String joinGame(ModelMap model, @RequestParam("joinCode") String joinCode, HttpServletResponse response) {
		Game game = gameService.findByJoinCode(joinCode).orElse(null);
		User loggedUser = userService.getLoggedUser();

		if (game.getUsers().contains(loggedUser)) {
			return "redirect:/games/waiting/" + game.getId();
		} else {
			try {
				gameService.addPlayerToGame(game, loggedUser);
				return "redirect:/games/waiting/" + game.getId();
			} catch (GameAlreadyStartedException e) {
				System.err.println("Excepción gameAlreadyStarted controlada");
				model.addAttribute("message", "Esta partida ya ha comenzado");
				return listGames(model);
			}catch (GameFullException e) {
				System.err.println("Excepción gameFull controlada");
				model.addAttribute("message", "Partida llena!");
				return listGames(model);
			} catch (GameCurrentNotUniqueException e) {
				System.err.println("Excepción GameCurrentNotUnique controlada");
				model.addAttribute("message", "Ya estás jugando otra partida!");
				return listGames(model);
			} catch (Exception e) {
				// e.printStackTrace();
				return listGames(model);
			}
		}
	}

	@GetMapping("/exitGame")
    public String exitGame(ModelMap model) {

		User user = userService.getLoggedUser();
		Game game = gameService.findGameInProgressByUser(user).orElse(null);
		if(game != null){
			Collection<User> lista = game.getUsers();
			GameUser player = gameUserService.findByGameAndUser(game, user).get();
			gameUserService.deleteGameUser(player);
			lista.remove(user);
			game.setUsers(lista);
			gameService.updateGame(game);

			if(lista.isEmpty()){
				gameService.deleteGame(game);
			}
			return "redirect:/games";
		}
		else{
			return "redirect:/games";
		}  
    }

	@GetMapping("/endGame/{gameId}/{hordaDerrotada}")
	public String selectWinner(ModelMap model, @PathVariable("gameId") int gameId, @PathVariable("hordaDerrotada") Boolean hordaDerrotada) {
		Game game = gameService.findById(gameId).orElse(null);

		if(hordaDerrotada == true && enemyCardService.countOnTableEnemiesByGame(game)==0){
			List<UserGlory> players = gameService.getClassification(game);
			model.addAttribute("hordaDerrotada", hordaDerrotada);
			model.addAttribute("players", players);
			return GAMES_WINNER;
		}
		else{
			game.setIsInProgress(false);
			gameService.updateGame(game);
			model.addAttribute("hordaDerrotada", hordaDerrotada);
			return GAMES_WINNER;
		}
	}
	
	@GetMapping("/{gameId}/defense")
	public String listDefendGame(ModelMap model, @PathVariable("gameId") int gameId) throws Exception {

		//Cambia el estado de la partida
		Game game = gameService.findById(gameId).orElse(null);
		game.setGameState(GameState.DEFENDING);
		gameService.updateGame(game);

		gameService.defendHeroe(game, userService.getLoggedUser());
		
		return "redirect:/games/"+gameId;
	}

	@GetMapping("/{gameId}/endTurn")
	public String endTurn(ModelMap model, @PathVariable("gameId") int gameId, HttpServletResponse response) throws Exception {
		Game game = gameService.findById(gameId).get();
		if (game.getUserPlaying().equals(userService.getLoggedUser()) && game.getIsInProgress()) {
			gameService.endTurn(game);
			return "redirect:/games/" + gameId;
		} else {
			model.addAttribute("message", "No puedes cambiar de turno en este momento");
		}

		return gamePlaying(model, gameId, response);
	}

	@RequestMapping("/{gameId}/deletePlayer/{playerId}")
	public String deletePlayer(ModelMap model, @PathVariable("gameId") Integer gameId,@PathVariable("playerId") Integer userId, HttpServletResponse response) throws Exception{
		User user= userService.findById(userId).get();
		Game game = gameService.findById(gameId).get();
		GameUser gameUser= gameUserService.findByGameAndUser(game, user).get();
		List<GameUser> listGameUsers=gameUserService.findAllByGame(game);
		if(listGameUsers.contains(gameUser)){
			gameService.endTurn(game);
			gameUserService.deleteGameUser(gameUser);	
		}
		return "redirect:/games/"+gameId;

	}
	@GetMapping("/{gameId}")
	public String gamePlaying(ModelMap model, @PathVariable("gameId") int gameId, HttpServletResponse response) throws Exception {

		Game game = gameService.findById(gameId).get();
		User user = userService.getLoggedUser();
		User creator= game.getCreator();
		Optional<GameUser> playerOpt = gameUserService.findByGameAndUser(game, user);
		if(enemyCardService.countOnDeckEnemiesByGame(game) == 0 && enemyCardService.countOnTableEnemiesByGame(game)==0){
			Integer time=(int) (timer()-TIME_INICIO);
			game.setDuration(time/1000);
			gameService.updateGame(game);
			return "redirect:/games/endGame/{gameId}/" + true;
		}else if(gameUserService.findByGameUsersAlive(game).size() == 0){
			Integer time=(int) (timer()-TIME_INICIO);
			game.setDuration(time/1000);
			gameService.updateGame(game);
			return "redirect:/games/endGame/{gameId}/" + false;
		}

		if(!playerOpt.isPresent()){		//Si el jugador no pertenece a la partida
			model.addAttribute("message", "You don't belong to this game!");
			return listGames(model);
		}

		GameUser player = playerOpt.get();
		Collection<SkillCard> skillsAvailable = skillCardsService.findAllAvailableSkillsByGameAndUser(game, user);
		Collection<EnemyCard> enemiesOnTable = enemyCardService.findOnTableEnemiesByGame(game);
		enemiesOnTable.stream().forEach(
				enemy -> enemy.setHealthInGame(gamesEnemiesService.findByGameAndEnemy(game, enemy).get().getHealth()));
		model.addAttribute("skillCardsOnHand", skillsAvailable);
		model.addAttribute("skillCardsOnDeck", skillCardsService.findAllOnDeckSkillsByGameAndUser(game, user));
		model.addAttribute("skillCardsDiscarded", skillCardsService.findAllOnDiscardedSkillsByGameAndUser(game, user));
		model.addAttribute("enemies", enemiesOnTable);
		model.addAttribute("game", game);
		model.addAttribute("user", user);
		model.addAttribute("creator",creator);
		model.addAttribute("player", player);
		model.addAttribute("players", gameUserService.findAllByGame(game));
		model.addAttribute("userService", userService);
		model.addAttribute("gameUserService", gameUserService);
		//Si el jugador no está jugando, siempre irá a attackview
		if(!game.getUserPlaying().equals(userService.getLoggedUser())){
			response.addHeader("Refresh", "5");
			return ATTACK_VIEW;
		}
		//Si el jugador está muerto, salta turno y se queda en attackview
		if(player.getHeroeHealth()<=0){
			gameService.endTurn(game);
			if(!game.getUserPlaying().equals(userService.getLoggedUser())){
				response.addHeader("Refresh", "1");
			}
			return ATTACK_VIEW;
		}
		switch (game.getGameState()) {
			case ATTACKING: {
				model.addAttribute("hasEscapeToken",
						gameUserService.findByGameAndUser(game, user).get().getHasEscapeToken());
				return ATTACK_VIEW;
			}
			case DEFENDING:{
				//Coge el tu heroe y le pone la vida que le queda en esa partida
				Optional<HeroeCard> heroe = gameUserService.findHeroeOfGameUser(game, user);
				//numero de cartas que tienes en tu mazo y en la mano para que lo puedas saber
				Integer numberOfSkillCards = gameUserSkillCardsService.findAllAvailableSkillsandOnTableByGameAndUser(game,user).size();

				game.setGameState(GameState.BUYING);
				gameService.updateGame(game);

				
				model.addAttribute("heroes", heroe.get());
				model.addAttribute("enemies", enemiesOnTable);
				model.addAttribute("numberOfSkillCards", numberOfSkillCards);
				model.addAttribute("game",game);
				model.addAttribute("user", user);
					return DEFEND_VIEW;	
				}	
				case BUYING:
					model.addAttribute("market", marketService.findAllByGameAndOnTable(gameService.findById(gameId).get()));
					model.addAttribute("gameUser", gameUserService
							.findByGameAndUser(gameService.findById(gameId).get(), userService.getLoggedUser()).get());
					return MARKET_VIEW;
			default:
				throw new Exception();
		}
	}

	@RequestMapping(value = "/{gameId}", method = RequestMethod.POST)
	public String attackPhase(ModelMap model, @RequestParam(value="skillUsed", required=false) Integer skillCardId,
			@RequestParam(value="enemySelected") Optional<List<Integer>> listEnemyCardsSelectedId, 
			@RequestParam(value="itemSelected", required=false) Optional<Integer> id, 
			HttpServletResponse response, @PathVariable("gameId") Integer gameId) throws Exception {
		Game game = gameService.findById(gameId).get();	
		switch (game.getGameState()) {
			case ATTACKING:
				try {
					// ⬇Esto hace que la lista esté vacía si no se selecciona ningun enemigo⬇
					List<Integer> listEnemys=listEnemyCardsSelectedId.get();
					Object aux = 0;
					listEnemys.remove(aux);
					// ⬆Esto hace que la lista esté vacía si no se selecciona ningun enemigo⬆

					gameService.useCard(skillCardId, gameService.findById(gameId).get(), userService.getLoggedUser(),
							listEnemys);
					return "redirect:" + gameId;

				} catch (CardNotSelectedException e) {
					System.err.println("Excepción CardNotSelected controlada");
					model.addAttribute("message", "Por favor, seleccione una carta, acabe su turno o use su ficha de escape");
					return gamePlaying(model, gameId, response);
				} catch (IncorrectNumberOfEnemiesException e) {
					System.err.println("Excepción IncorrectNumberOfEnemies controlada");
					model.addAttribute("message", "Esa carta no puede aplicarse a ese número de enemigos");
					return gamePlaying(model, gameId, response);
				} catch(DontHaveEnoughGoldToBuyException e){
					System.err.println("Excepción DontHaveEnoughGoldToBuyException controlada");
					model.addAttribute("message", "Necesitas más oro para ejecutar esta acción");
					return gamePlaying(model, gameId, response);
				} catch (Exception e) {
					System.err.println("Excepción controlada");
					e.printStackTrace();
					model.addAttribute("message", "Error desconocido al usar carta");
					return gamePlaying(model, gameId, response);
				}
			case BUYING:
				try {
					gameService.buyMarketItem(gameService.findById(gameId).get(), userService.getLoggedUser(), id);
					model.addAttribute("game", game);
					model.addAttribute("market", marketService.findAllByGameAndOnTable(gameService.findById(gameId).get()));
					model.addAttribute("user", userService.getLoggedUser());
					model.addAttribute("gameUser", gameUserService.findByGameAndUser(gameService.findById(gameId).get(), userService.getLoggedUser()).get());
					return "redirect:/games/{gameId}";
				} catch (DontHaveEnoughGoldToBuyException e) {
					System.err.println("Excepción DontHaveEnoughGoldToBuy controlada");
					model.addAttribute("message", "No tienes suficiente dinero para comprar este item");
					return gamePlaying(model, gameId, response);
				}catch (ItemNotSelectedException e) {
					System.err.println("Excepción ItemNotSelected controlada");
					model.addAttribute("message", "Por Favor selecciona una carta para comprar o finalice su turno");
					return gamePlaying(model, gameId, response);
				}catch (Exception e) {
					System.err.println("Excepción controlada");
					e.printStackTrace();
					model.addAttribute("message", "Error desconocido al comprar carta");
					return gamePlaying(model, gameId, response);
				}
			default:
				throw new Exception();
		}
	}

	@GetMapping("/{gameId}/escape")
	public String escape(ModelMap model, @PathVariable("gameId") int gameId, HttpServletResponse response) throws Exception {
		try {
			Game game = gameService.findById(gameId).get();
			if (game.getUserPlaying().equals(userService.getLoggedUser()) && game.getIsInProgress()) {
				GameUser gu = gameUserService.findByGameAndUser(game, userService.getLoggedUser()).get();
				if (gu.getHasEscapeToken()) {
					gameService.endTurn(game);
					gu.setHasEscapeToken(false);
					gameUserService.saveGameUser(gu);
					return "redirect:/games/" + gameId;
				} else {
					model.addAttribute("message", "Ya has usado tu ficha de escape");
				}

			} else {
				model.addAttribute("message", "No puedes usar tu ficha de escape en este momento");
			}
		} catch (Exception e) {
			System.err.println("Excepción controlada");
			e.printStackTrace();
		}

		return gamePlaying(model, gameId, response);
	}

	@GetMapping("/current")
	public String gameCurrent(ModelMap modelMap) {
		Optional<Game> gameOpt = gameService.findGameInProgressByUser(userService.getLoggedUser());
		if (!gameOpt.isPresent()) {
			modelMap.addAttribute("message", "No estás en ninguna partida en progreso");
			return listGames(modelMap);
		} else {
			Game game = gameOpt.get();
			return "redirect:/games/" + gameService.getGameUrl(game);
		}
	}

	@GetMapping("/selectHeroe/{gameId}")
	public String selectHeroe(ModelMap model, @PathVariable("gameId") int gameId) {

		Game game = gameService.findById(gameId).get();

		if(game.getUsers().size()<=1){
			model.addAttribute("message", "No hay suficientes jugadores");
			return listGames(model);
		}else if(!game.getUsers().contains(userService.getLoggedUser())){
			model.addAttribute("message", "¡No perteneces a esta partida!");
			return listGames(model);
		}else if (!game.getCreator().equals(userService.getLoggedUser()) && !game.getIsInProgress()) {
			return "redirect:/games/waiting/{gameId}";
		}

		if (gameUserService.findHeroeOfGameUser(game, userService.getLoggedUser()).orElse(null) != null) {
			model.addAttribute("hasSelected", true);
			model.addAttribute("message", "You have selected a heroe");
		}

		if (game.getCreator().equals(userService.getLoggedUser())) {
			gameService.updateGame(game);
		}
		if (!model.containsAttribute("hasSelected")) {
			model.addAttribute("hasSelected", false);
		}
		// aqui se coge los ids de los heroes que estan seleccionados de una partida
		List<HeroeCard> heroesSelectedList = (List<HeroeCard>) heroeCardsService.findHeroesOfGame(game);
		for (HeroeCard heroe : heroesSelectedList) {
			// en esta linea encuentro el heroe y cojo el color que tiene
			String color = heroe.getColor();
			if (color.equals("Morado")) {
				model.addAttribute("purpleSelected", true);
			} else if (color.equals("Verde")) {
				model.addAttribute("greenSelected", true);
			} else if (color.equals("Azul")) {
				model.addAttribute("blueSelected", true);
			} else if (color.equals("Rojo")) {
				model.addAttribute("redSelected", true);
			}
		}
		model.addAttribute("game", game);
		return GAMES_SELECT_HEROE;
	}

	@RequestMapping(value = "/selectHeroe/{gameId}", method = RequestMethod.POST)
	public String selectHeroe(@PathVariable("gameId") int gameId, ModelMap model, @RequestParam("heroe") String heroe,
			HttpServletResponse response) {

		try {
			gameService.selectHeroe(gameService.findById(gameId).get(), userService.getLoggedUser(), heroe);
			return "redirect:/games/selectPlayerToStart/" + gameId;
		} catch (HeroeNotAvailableException e) {
			System.err.println("Excepción HeroeNotAvailable controlada");
			model.addAttribute("message", "Este héroe no está disponible");
			return selectHeroe(model, gameId);
		} catch (Exception e) {
			System.err.println("Excepción controlada");
			model.addAttribute("message", "Error al seleccionar héroe");
			e.printStackTrace();
			return selectHeroe(model, gameId);
		}
	}

	@GetMapping("/selectPlayerToStart/{gameId}")
	public String selectPlayerToStart(ModelMap model, @PathVariable("gameId") int gameId, HttpServletResponse response) {

		response.addHeader("Refresh", "1");
		Game game = gameService.findById(gameId).get();

		if(!game.getUsers().contains(userService.getLoggedUser())){
			model.addAttribute("message", "¡No perteneces a esta partida!");
			return listGames(model);
		}

		List<User> usersWithHeroe = (List<User>) userService.findAllInGameWithHeroeSelected(game);
		model.addAttribute("game", game);
		model.addAttribute("users", usersWithHeroe);
		model.addAttribute("loggedUser", userService.getLoggedUser());
		return SELECT_PLAYER_TO_START;
	}

	@PostMapping("/selectPlayerToStart/{gameId}")
	public String selectPlayerToStart(ModelMap model, @PathVariable("gameId") int gameId, RedirectAttributes redirect,
			HttpServletResponse response) {
		TIME_INICIO=(int) timer();
		gameService.selectFirstPlayer(gameId);
		return selectPlayerToStart(model, gameId, response);
	}

	@GetMapping("/details/{gameId}")
	public String gameDetails(ModelMap model, @PathVariable("gameId") int gameId) {
		Game game = gameService.findById(gameId).get();
		model.addAttribute("game", game);
		model.addAttribute("users", game.getUsers());
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
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> System.out.println(error.toString()));
			return GAMES_FORM;
		} else {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!auth.getPrincipal().toString().equals("anonymousUser")) { // Si estamos logeados
				try {
					gameService.createGame(game);
					model.addAttribute("message", "Game created");

				} catch (Exception e) {
					model.addAttribute("message", "ERROR: Partida no creada");
					e.printStackTrace();
					return GAMES_FORM;
				}
			} else { // Si no estamos logeados
				model.addAttribute("message", "ERROR: Usuario no identificado");
				return GAMES_FORM;
			}

			return "redirect:/games/waiting/" + game.getId();
		}
	}

	@GetMapping("/waiting/{gameId}")
	public String waitingGame(ModelMap model, @PathVariable("gameId") int gameId, HttpServletResponse response) {
		response.addHeader("Refresh", "1");
		Game game = gameService.findById(gameId).get();
		if(!game.getUsers().contains(userService.getLoggedUser())){
			model.addAttribute("message", "¡No perteneces a esta partida!");
			return listGames(model);
		}
		model.addAttribute("game", game);
		model.addAttribute("users", game.getUsers());
		model.addAttribute("loggedUser", userService.getLoggedUser());
		return GAMES_WAITING_FOR_PLAYERS;
	}

}
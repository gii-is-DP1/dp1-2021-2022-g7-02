package org.springframework.samples.notimeforheroes.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.swing.Action;
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
import org.springframework.samples.notimeforheroes.game.exceptions.GameCurrentNotUniqueException;
import org.springframework.samples.notimeforheroes.game.exceptions.GameFullException;
import org.springframework.samples.notimeforheroes.game.exceptions.HeroeNotAvailableException;
import org.springframework.samples.notimeforheroes.game.exceptions.IncorrectNumberOfEnemiesException;
import org.springframework.samples.notimeforheroes.game.exceptions.NotAuthenticatedError;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUser;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUserService;
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
	public static final String DEFEND_VIEW = "games/defendGame";

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

	Timer timer;
	int second;
	boolean countOn=false;
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
			} catch (GameFullException e) {
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

	@GetMapping("/endGame/{gameId}")
	public String selectWinner(ModelMap model, @PathVariable("gameId") int gameId) {
		Game game = gameService.findById(gameId).orElse(null);
		Map<Integer, User> players = gameService.getClassification(game);

		User winner = players.get(0);
		players.remove(0);

		game.setWinner(winner);
		gameService.updateGame(game);

		model.addAttribute("winner", winner);
		model.addAttribute("players", players);
		return GAMES_WINNER;
	}


	
	@GetMapping("/{gameId}/defense")
	public String listDefendGame(ModelMap model, @PathVariable("gameId") int gameId) throws Exception {
		
		Game game = gameService.findById(gameId).orElse(null);
		game.setGameState(GameState.DEFENDING);
		gameService.updateGame(game);
		return "redirect:/games/"+gameId;
	}

	@GetMapping("/{gameId}/marketGame")
	public String listMarketGame(ModelMap model, @PathVariable("gameId") int gameId) {

		/*
		 * Todo esto debería ir en el case BUYING
		 * del switch del GetMapping(/{gameId})
		 */
		Game game = gameService.findById(gameId).get();
		model.addAttribute("game", game);
		model.addAttribute("market", marketService.findAllByGameAndOnTable(gameService.findById(gameId).get()));
		model.addAttribute("user", userService.getLoggedUser());
		model.addAttribute("gameUser", gameUserService
				.findByGameAndUser(gameService.findById(gameId).get(), userService.getLoggedUser()).get());
		return MARKET_VIEW;
	}

	@GetMapping("/{gameId}/endTurn")
	public String endTurn(ModelMap model, @PathVariable("gameId") int gameId) throws Exception {
		Game game = gameService.findById(gameId).get();
		if (game.getUserPlaying().equals(userService.getLoggedUser()) && game.getIsInProgress()) {
			gameService.endTurn(game);
			timer.stop();
			countOn=false;
			return "redirect:/games/" + gameId;
		} else {
			model.addAttribute("message", "No puedes cambiar de turno en este momento");
		}

		return gamePlaying(model, gameId);
	}

	@GetMapping("/{gameId}")
	public String gamePlaying(ModelMap model, @PathVariable("gameId") int gameId) throws Exception {

		Game game = gameService.findById(gameId).get();
		User user = userService.getLoggedUser();
		GameUser player= gameUserService.findByGameAndUser(game, user).get();

		if(game.getUsers().size()<2){
			//metodo de acabar la partida
		}
		if(player.getHeroeHealth()==0){
			gameService.endTurn(game);
			countOn=false;
		}
		Collection<SkillCard> skillsAvailable = skillCardsService.findAllAvailableSkillsByGameAndUser(game, user);
		Collection<EnemyCard> enemiesOnTable = enemyCardService.findOnTableEnemiesByGame(game);
		enemiesOnTable.stream().forEach(
				enemy -> enemy.setHealthInGame(gamesEnemiesService.findByGameAndEnemy(game, enemy).get().getHealth()));
		model.addAttribute("skillCardsOnHand", skillsAvailable);
		model.addAttribute("enemies", enemiesOnTable);
		model.addAttribute("game", game);
		model.addAttribute("user", user);
		model.addAttribute("player", player);
		if(user==game.getUserPlaying()&& !countOn){
			fiveMinutesTimer(model,game,user,player);
				
		}
		switch (game.getGameState()) {
			case ATTACKING: {
				model.addAttribute("hasEscapeToken",
						gameUserService.findByGameAndUser(game, user).get().getHasEscapeToken());
				return ATTACK_VIEW;
			}
			case DEFENDING:{
				//Aplica el daño
				Integer daño = enemyCardService.findOnTableEnemiesByGame(game).stream().map(enemyCard -> gamesEnemiesService.findByGameAndEnemy(game, enemyCard).get().getHealth()).collect(Collectors.summingInt(Integer::intValue));
				if(gameUserService.findByGameAndUser(game, user).get().getDamageShielded() != null){
					if(daño - gameUserService.findByGameAndUser(game, user).get().getDamageShielded() >= 0){
						daño -= gameUserService.findByGameAndUser(game, user).get().getDamageShielded();
					}else{
						daño = 0;
					}
				}
				GameUser gameUser = gameUserService.findByGameAndUser(game, user).get();
				gameUserSkillCardsService.discardCards(game, user, daño);
				gameUser.setDamageShielded(0);
				gameUserService.saveGameUser(gameUser);

				//Coge el tu heroe y le pone la vida que le queda en esa partida
				Optional<HeroeCard> heroe = gameUserService.findHeroeOfGameUser(game, user);
				heroe.get().setMaxHealth(gameUser.getHeroeHealth());
				
				//numero de cartas que tienes en tu mazo y en la mano para que lo puedas saber
				Integer numberOfSkillCards = gameUserSkillCardsService.findAllAvailableSkillsandOnTableByGameAndUser(game,user).size();


				
				model.addAttribute("heroes", heroe.get());
				model.addAttribute("enemies", enemiesOnTable);
				model.addAttribute("numberOfSkillCards", numberOfSkillCards);
				model.addAttribute("game",game);
				model.addAttribute("user", user);
					return DEFEND_VIEW;	
				}	
				case BUYING:
					model.addAttribute("market", marketService.findByGameOnDeck(gameService.findById(gameId).get()));
					model.addAttribute("user", gameUserService.findByGameAndUser(gameService.findById(gameId).get(), userService.getLoggedUser()).get());
					return MARKET_VIEW;
			default:
				throw new Exception();
		}
	}

	private void fiveMinutesTimer(ModelMap model,Game game, User user, GameUser player) throws InterruptedException {
		second=300;
		simpleTimer(model,player,game);
		timer.start();
		countOn=true;
	}

	private void simpleTimer(ModelMap model,GameUser player, Game game) {
		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				second--;
				System.out.println(second);
				if(second==0){
					player.setHeroeHealth(0);
					gameUserService.saveGameUser(player);
					timer.stop();					
				}
			}
		});

	}
	@RequestMapping(value = "/{gameId}", method = RequestMethod.POST)
	public String attackPhase(ModelMap model, @RequestParam("skillUsed") Integer skillCardId,
			@RequestParam("enemySelected") List<Integer> listEnemyCardsSelectedId, HttpServletResponse response,
			@PathVariable("gameId") Integer gameId) throws Exception {
		try {
			// Esto hace que la lista esté vacía si no se selecciona ningun enemigo
			Object aux = 0;
			listEnemyCardsSelectedId.remove(aux);

			gameService.useCard(skillCardId, gameService.findById(gameId).get(), userService.getLoggedUser(),
					listEnemyCardsSelectedId);
			return "redirect:" + gameId;

		} catch (CardNotSelectedException e) {
			model.addAttribute("message", "Por favor, seleccione una carta, acabe su turno o use su ficha de escape");
			return gamePlaying(model, gameId);
		} catch (IncorrectNumberOfEnemiesException e) {
			model.addAttribute("message", "Esa carta no puede aplicarse a ese número de enemigos");
			return gamePlaying(model, gameId);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Error desconocido al usar carta");
			return gamePlaying(model, gameId);
		}

	}

	@GetMapping("/{gameId}/escape")
	public String escape(ModelMap model, @PathVariable("gameId") int gameId) throws Exception {
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
			e.printStackTrace();
		}

		return gamePlaying(model, gameId);
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

		if (!game.getUsers().contains(userService.getLoggedUser())) {
			model.addAttribute("message", "You don't belong to this game!");
			return listGames(model);
		} else if (!game.getCreator().equals(userService.getLoggedUser()) && !game.getIsInProgress()) {
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
			model.addAttribute("message", "Este héroe no está disponible");
			return selectHeroe(model, gameId);
		} catch (Exception e) {
			model.addAttribute("message", "Error al seleccionar héroe");
			e.printStackTrace();
			return selectHeroe(model, gameId);
		}
	}

	@GetMapping("/selectPlayerToStart/{gameId}")
	public String selectPlayerToStart(ModelMap model, @PathVariable("gameId") int gameId,
			HttpServletResponse response) {
		response.addHeader("Refresh", "1");
		Game game = gameService.findById(gameId).get();
		List<User> usersWithHeroe = (List<User>) userService.findAllInGameWithHeroeSelected(game);

		model.addAttribute("game", game);
		model.addAttribute("users", usersWithHeroe);
		model.addAttribute("loggedUser", userService.getLoggedUser());
		return SELECT_PLAYER_TO_START;
	}

	@PostMapping("/selectPlayerToStart/{gameId}")
	public String selectPlayerToStart(ModelMap model, @PathVariable("gameId") int gameId, RedirectAttributes redirect,
			HttpServletResponse response) {

		gameService.selectFirstPlayer(gameId);
		return selectPlayerToStart(model, gameId, response);
	}

	@RequestMapping(value = "/{gameId}/marketGame", method = RequestMethod.POST)
	public String buyMarketItem(@PathVariable("gameId") int gameId, ModelMap model,
			@RequestParam("itemSelected") int id, HttpServletResponse response) {

		try {
			gameService.buyMarketItem(gameService.findById(gameId).get(), userService.getLoggedUser(), id);
			return "redirect:/games/{gameId}/marketGame/";
		} catch (DontHaveEnoughGoldToBuyException e) {
			model.addAttribute("message", "You don´t have enough money to buy this item");
			return listMarketGame(model, gameId);
		}
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

	@GetMapping("/join")
	public String joinGame(ModelMap model) {
		User loggedUser = userService.getLoggedUser();
		model.addAttribute("user", loggedUser);
		return GAMES_LISTING;
	}

	@GetMapping("/waiting/{gameId}")
	public String waitingGame(ModelMap model, @PathVariable("gameId") int gameId, HttpServletResponse response) {

		response.addHeader("Refresh", "1");
		Game game = gameService.findById(gameId).get();
		model.addAttribute("game", game);
		model.addAttribute("users", game.getUsers());
		model.addAttribute("loggedUser", userService.getLoggedUser());
		return GAMES_WAITING_FOR_PLAYERS;
	}

}
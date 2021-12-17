package org.springframework.samples.notimeforheroes.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyCard;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyCardService;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyState;
import org.springframework.samples.notimeforheroes.cards.enemycard.gamesEnemies.GamesEnemies;
import org.springframework.samples.notimeforheroes.cards.enemycard.gamesEnemies.GamesEnemiesService;
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCard;
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCardsService;
import org.springframework.samples.notimeforheroes.cards.marketcard.ItemState;
import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCard;
import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCardsService;
import org.springframework.samples.notimeforheroes.cards.marketcard.gamesMarket.GameMarket;
import org.springframework.samples.notimeforheroes.cards.marketcard.gamesMarket.GameMarketService;
import org.springframework.samples.notimeforheroes.cards.skillcard.SkillCard;
import org.springframework.samples.notimeforheroes.cards.skillcard.SkillCardsService;
import org.springframework.samples.notimeforheroes.cards.skillcard.gamesUsersSkillcards.GamesUsersSkillCards;
import org.springframework.samples.notimeforheroes.cards.skillcard.gamesUsersSkillcards.GamesUsersSkillCardsService;
import org.springframework.samples.notimeforheroes.cards.skillcard.gamesUsersSkillcards.SkillState;
import org.springframework.samples.notimeforheroes.game.exceptions.GameCurrentNotUniqueException;
import org.springframework.samples.notimeforheroes.game.exceptions.GameFullException;
import org.springframework.samples.notimeforheroes.game.exceptions.HeroeNotAvailableException;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUser;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUserService;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class GameService {

	@Autowired
	GameRepository gameRepository;

	@Autowired
	UserService userService;

	@Autowired
	GameUserService gameUserService;
	
	@Autowired
	MarketCardsService marketCardService;

	@Autowired
	HeroeCardsService heroeCardsService;

	@Autowired
	GamesUsersSkillCardsService gamesUsersSkillCardsService;

	@Autowired
	SkillCardsService skillCardsService;

	@Autowired
	GameMarketService gameMarketService;

	@Autowired
	EnemyCardService enemyCardService;

	@Autowired
	GamesEnemiesService gamesEnemiesService;

	public static final Integer MAX_NUMBER_PLAYERS = 4;
	
	public Collection<Game> findAvailableGames(){
		return userService.isUserAdmin(userService.getLoggedUser()) ? gameRepository.findAll() : gameRepository.findPublicAndOwn(userService.getLoggedUser());
	}

	public Collection<Game> findAll(){
		return gameRepository.findAll();
	}
	
	public Optional<Game> findById(Integer id){
		return gameRepository.findById(id);
		
	}
	
	public Collection<Game> findAllEnded(){
		return gameRepository.findAllEnded();
	}

	public Collection<Game> findByWinner(User user){
		return gameRepository.findByWinner(user);
	}
	
	public Collection<Game> findAllByIsInProgress(){
		return gameRepository.findAllByIsInProgress(true);
	}

	public Collection<Game> findAllByCreator(User user){
		return gameRepository.findAllByCreator(user);
	}

	public Optional<Game> findByJoinCode(String joinCode){
		return gameRepository.findByJoinCode(joinCode.trim());
	}

	public Collection<Game> findByUser(User user){
		return gameRepository.findByUser(user);
	}

	public Optional<Game> findGameInProgressByUser(User user){
		return gameRepository.findGameInProgressByUser(user);
	}
	
	public String getGameUrl(Game game) {
		//Si no está en progreso o no se ha elegido heroe, devuelve waiting/{gameId}
		if(!game.getIsInProgress() || !gameUserService.findHeroeOfGameUser(game, userService.getLoggedUser()).isPresent()){
			return "waiting/"+game.getId();
		}else{
		//Si no se ha seleccionado el primer jugador, devuelve selectPlayerToStart/{gameId}
			if(game.getFirstPlayer()==null) return "selectPlayerToStart/"+game.getId();
		//Si la partida está en progreso, el jugador tiene héroe y se ha elegido primer jugador, devuelve /{gameId}
			else{
				return game.getId().toString();
			}
		}

	}

	@Transactional
	public void createGame(@Valid Game game){	
		
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User creator;
			if(auth == null){
				creator = userService.findById(1).get();	//Esto se hace para que los juegos que se crean en los tests tengan creador
			}else{
				String creatorUsername = ((org.springframework.security.core.userdetails.User)auth.getPrincipal()).getUsername();
				creator = userService.findByUsername(creatorUsername).get();
			}
			
			//Determina el creador y lo añade a los jugadores
			game.setCreator(creator);
			List<User> users = new ArrayList<>();
			users.add(creator);
			game.setUsers(users);


			//Añade los enemigos a la partida y los pone todos ONDECK menos 3 que pone ONTABLE
			List<EnemyCard> enemies = (ArrayList<EnemyCard>) enemyCardService.findAllByIsBoss(false);
			List<EnemyCard> bosses = (ArrayList<EnemyCard>) enemyCardService.findAllByIsBoss(true);
			Collections.shuffle(enemies);
			enemies.add(bosses.get(new Random().nextInt(bosses.size())));
			game.setEnemies(enemies);
			gameRepository.save(game);
			
			List<GamesEnemies> enemiesInGame = (List<GamesEnemies>)gamesEnemiesService.findAllInGame(game);

			for(int i = 0; i<enemiesInGame.size(); i++){
				GamesEnemies enemyInGame = enemiesInGame.get(i);
				EnemyState newState = i >= 3 ? EnemyState.ONDECK : EnemyState.ONTABLE;
				enemyInGame.setEnemyState(newState);
				EnemyCard enemyCard = enemyCardService.findEnemyOfGamesEnemies(enemyInGame).get();
				enemyInGame.setHealth(enemyCard.getMaxHealth());
				gamesEnemiesService.createGamesEnemies(enemyInGame);
			}
			
			//Añade los objetos a la partida y los pone todos ONDECK menos 5 que pone ONHAND
			Collection<MarketCard> items = marketCardService.findAll();
			List<MarketCard> shuffledItems = new ArrayList<MarketCard>(items);
			Collections.shuffle(shuffledItems);
			game.setItems(shuffledItems);
			gameRepository.save(game);
			List<GameMarket> itemsInGame = (List<GameMarket>)gameMarketService.findAllInGame(game);
			Collections.shuffle(itemsInGame);

			for(int i = 0; i<itemsInGame.size(); i++){
				GameMarket itemInGame = itemsInGame.get(i);
				ItemState newState = i >= 5 ? ItemState.ONDECK : ItemState.ONTABLE;
				itemInGame.setItemState(newState);
				gameMarketService.createGameMarket(itemInGame);
			}

			
		

	}

	@Transactional
	public void addPlayerToGame(@Valid Game game, User user) throws GameFullException, GameCurrentNotUniqueException{
		
		if(game.getUsers().size() < MAX_NUMBER_PLAYERS){
			if(!this.findGameInProgressByUser(user).isPresent()){
				//Si el jugador no esta en otra partida en curso y la partida no está en curso, 
				//lo añade	

				game.getUsers().add(user);
				try {
					this.updateGame(game);
				} catch (Exception e) {
					System.err.println("SOPKSIODBJEOIRBJEIRUHJUEIRBN");
				}
				
			}else{
				throw new GameCurrentNotUniqueException();	//Lanza excepción si el jugador ya está en otra partida en curso
			}
			
		}else{
			throw new GameFullException();		//Lanza excepción si la partida está llena
		}
		gameRepository.save(game);

	}

	@Transactional
	public void selectHeroe(@Valid Game game, User user, String heroe) throws HeroeNotAvailableException{
		HeroeCard heroeCard = heroeCardsService.findByName(heroe);
		Collection<HeroeCard> cardsOfSameColor = heroeCardsService.findByColorAndGame(heroeCard.getColor(), game);
		if(cardsOfSameColor.size() == 0){
			//Si no hay nadie que haya elegido una carta de su color, asigna el héroe y las habilidades al usuario en la partida (gameUser)
			GameUser gameUser = gameUserService.findByGameAndUser(game, user).get();
			List<SkillCard> skillCards = (List<SkillCard>)skillCardsService.findByColor(heroeCard.getColor());
			gameUser.setHeroe(heroeCard);
			gameUser.setSkillCards(skillCards);
			gameUserService.createGameUser(gameUser);

			//Baraja las cartas de skill y a cuatro al azar las pone como ONHAND (por defecto están ONDECK)
			Collections.shuffle(skillCards);
			for(int i = 0; i<4;i++){
				GamesUsersSkillCards card = gamesUsersSkillCardsService.findByGameUserSkill(game, user, skillCards.get(i)).get();
				card.setSkillState(SkillState.ONHAND);
				gamesUsersSkillCardsService.createGameUserSkillCard(card);
			}
			for(int i = 4; i<skillCards.size(); i++){
				GamesUsersSkillCards card = gamesUsersSkillCardsService.findByGameUserSkill(game, user, skillCards.get(i)).get();
				card.setSkillState(SkillState.ONDECK);
			}
		}else{
			throw new HeroeNotAvailableException();
		}
	}

	@Transactional
	public void updateGame(@Valid Game game){
		gameRepository.save(game);
	}
	
	@Transactional
	public void deleteGame(Game game) {
		gameRepository.deleteById(game.getId());
	}

	@Transactional	
	public void selectFirstPlayer(Integer gameId){		
		//Elige el primer jugador a jugar y pone el estado de la partida en ATTACKING
		Game game = this.findById(gameId).get();
		List<User> usersWithHeroe= new ArrayList<User>();
		List<User> users=(List<User>) userService.findAllInGame(game);
		for(int i=0; i<users.size(); i++){
            Optional<HeroeCard> heroeCard = gameUserService.findHeroeOfGameUser(this.findById(gameId).get(), users.get(i));
            if(heroeCard.isPresent()){
                usersWithHeroe.add(users.get(i));
            }
        }
		Random ran = new Random();
		Integer index = ran.nextInt(users.size());
		User firstUser = users.get(index);
		game.setFirstPlayer(firstUser);
		game.setGameState(GameState.ATTACKING);
		this.updateGame(game);
	}
}

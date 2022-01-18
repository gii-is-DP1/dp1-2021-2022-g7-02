package org.springframework.samples.notimeforheroes.game;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.actions.Action;
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
import org.springframework.samples.notimeforheroes.game.exceptions.CardNotSelectedException;
import org.springframework.samples.notimeforheroes.game.exceptions.DontHaveEnoughGoldToBuyException;
import org.springframework.samples.notimeforheroes.game.exceptions.GameCurrentNotUniqueException;
import org.springframework.samples.notimeforheroes.game.exceptions.GameFullException;
import org.springframework.samples.notimeforheroes.game.exceptions.HeroeNotAvailableException;
import org.springframework.samples.notimeforheroes.game.exceptions.IncorrectNumberOfEnemiesException;
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
	public static final Integer NUMBER_ENEMIES = 3;
	public static final Integer NUMBER_ITEMS_IN_MARKET = 5;

	
	
	public Collection<Game> findAvailableGames(){
		return userService.isUserAdmin(userService.getLoggedUser()) ? gameRepository.findAll() : gameRepository.findPublicAndOwn(userService.getLoggedUser());
	}
	//
	public Collection<Game> findAll(){
		return gameRepository.findAll();
	}
	//
	public List<Tuple> findRanking(){
		return gameRepository.findRanking();
	}
	//
	public Optional<Game> findById(Integer id){
		return gameRepository.findById(id);
	}
	//
	public Collection<Game> findAllEnded(){
		return gameRepository.findAllEnded();
	}
	//
	public Collection<Game> findByWinner(User user){
		return gameRepository.findByWinner(user);
	}
	//
	public Collection<Game> findAllByIsInProgress(){
		return gameRepository.findAllByIsInProgress(true);
	}
	//
	public Collection<Game> findAllByCreator(User user){
		return gameRepository.findAllByCreator(user);
	}
	//
	public Optional<Game> findByJoinCode(String joinCode){
		return gameRepository.findByJoinCode(joinCode.trim());
	}
	//
	public Collection<Game> findByUser(User user){
		return gameRepository.findByUser(user);
	}
	
	//
	public Integer findBetweenDates(User user, LocalDate LocalDate1, LocalDate LocalDate2){
		Date date1=Date.valueOf(LocalDate1);
		Date date2=Date.valueOf(LocalDate2);
		return gameRepository.findBetweenDates(user, date1, date2);
	}
	//
	public Optional<Game> findGameInProgressByUser(User user){
		return gameRepository.findGameInProgressByUser(user);
	}

	//HAY QUE HACERLO
	public TreeMap<Integer,User> getClassification(Game game){
		TreeMap<Integer,User> players = new TreeMap<Integer,User>(Collections.reverseOrder());
		for (User user : game.getUsers()) {
			GameUser gameUser = gameUserService.findByGameAndUser(game, user).orElse(null); 
			gameUser.setGold(gameUser.getGold()/3);
			gameUser.setGlory(gameUser.getGlory()+gameUser.getGold()/3);
			players.put(gameUser.getGlory(),user);	
			gameUserService.saveGameUser(gameUser);		
		}
		
		return players;
	}
	
	public String getGameUrl(Game game) {
		//Si no está en progreso o no se ha elegido heroe, devuelve waiting/{gameId}
		if(!game.getIsInProgress() || !gameUserService.findHeroeOfGameUser(game, userService.getLoggedUser()).isPresent()){
			return "waiting/"+game.getId();
		}else{
		//Si no se ha seleccionado el primer jugador, devuelve selectPlayerToStart/{gameId}
			if(game.getUserPlaying()==null) return "selectPlayerToStart/"+game.getId();
		//Si la partida está en progreso, el jugador tiene héroe y se ha elegido primer jugador, devuelve /{gameId}
			else{
				return game.getId().toString();
			}
		}

	}
	//
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
			game.setIsInProgress(true);
			gameRepository.save(game);
			

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
				gamesEnemiesService.saveGamesEnemies(enemyInGame);
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

	//
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
			gameUser.setGlory(0);
			gameUser.setGold(0);
			gameUser.setHasEscapeToken(true);
			gameUser.setHeroeHealth(heroeCard.getMaxHealth());
			gameUserService.saveGameUser(gameUser);

			//Baraja las cartas de skill y a cuatro al azar las pone como ONHAND (por defecto están ONDECK)
			Collections.shuffle(skillCards);
			for(int i = 0; i<4;i++){
				GamesUsersSkillCards card = gamesUsersSkillCardsService.findByGameUserSkill(game, user, skillCards.get(i)).get();
				card.setSkillState(SkillState.ONHAND);
				gamesUsersSkillCardsService.saveGameUserSkillCard(card);
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
	public void buyMarketItem(@Valid Game game, User user, int itemId) throws DontHaveEnoughGoldToBuyException{
		GameUser gameuser =gameUserService.findByGameAndUser(game, user).get();
		MarketCard item=marketCardService.findById(itemId).get();
		GameMarket itemInGame =gameMarketService.findOneItemInGame(game, itemId);
		int actualGold=gameuser.getGold();
		int costItem=item.getCost();
		
		if(actualGold>=costItem) {
			gameuser.setGold(actualGold-costItem);
			gameuser.getItems().add(item);
			gameUserService.saveGameUser(gameuser);
			
			itemInGame.setItemState(ItemState.SOLD);
			gameMarketService.createGameMarket(itemInGame);
		}
		else {
			throw new DontHaveEnoughGoldToBuyException();
		}
	}

	@Transactional
	public void defendHeroe(@Valid Game game, User user){
		Collection<EnemyCard> enemycardsontable = enemyCardService.findOnTableEnemiesByGame(game);
		int lifeToRest = 0;
		for(EnemyCard enemy : enemycardsontable){//Almacena la vida de los enemigos restantes
			lifeToRest += enemy.getHealthInGame();
		}
		gamesUsersSkillCardsService.discardCards(game, user, lifeToRest);

		game.setGameState(GameState.BUYING);
	}

	//
	@Transactional
	public void updateGame(@Valid Game game){
		gameRepository.save(game);
	}
	
	//
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
		game.setUserPlaying(firstUser);
		game.setGameState(GameState.ATTACKING);
		this.updateGame(game);
	}

	@Transactional
	public void useCard(Integer skillCardId, Game game, User user, List<Integer> listEnemyCardsSelectedId) throws CardNotSelectedException, IncorrectNumberOfEnemiesException, Exception{
		//si el usuario que esta jugando no es el usuario correspondiente
		if(!game.getUserPlaying().equals(user)){
			throw new Exception();
		}

		Optional<SkillCard> skillcardOpt = skillCardsService.findById(skillCardId);
		List<EnemyCard> enemiesTargetedList = listEnemyCardsSelectedId.stream().map(id -> enemyCardService.findById(id).get()).collect(Collectors.toList());


		if(skillcardOpt.isPresent()){
			SkillCard skillCard = skillcardOpt.get();
			Integer numberOfEnemiesRequired = skillCardsService.numberOfEnemiesOfSkillCard(skillCard);
			//comprobamos que se han elegido bien los enemigos, dependiendo de la carta que utilicemos
			checkIfNumberOfEnemiesIsOK(numberOfEnemiesRequired, enemiesTargetedList, game);
			
			//Comprobamos si la carta requiere lógica adicional
			switch (skillCard.getId()) {
				case 4:case 5:case 6:case 7:case 8:case 9:
					skillCardsService.useDisparoRápido(enemiesTargetedList, game, user, skillCard);
					break;	
				case 11:case 12:
					skillCardsService.useLluviaDeFlechas(enemiesTargetedList, game, user, skillCard);
					break;
				case 13:case 14:
					skillCardsService.useRecogerFlechas(game, user, skillCard);
					break;
				case 20: case 21:
					skillCardsService.useEscudo(enemiesTargetedList.get(0), game, user, skillCard);
					break;
				case 22: case 23: case 24: case 25:
					skillCardsService.useEspadazo(enemiesTargetedList.get(0), game, user, skillCard);
					break;
				case 28:
					skillCardsService.useTodoONada(enemiesTargetedList.get(0), game, user, skillCard);
					break;
				case 29:
					skillCardsService.useVozDeAliento(game, user, skillCard);
					break;
			
				default://Si no requiere lógica adicional
					executeActions(game, user, skillCard.getActions(), enemiesTargetedList);
					break;
			}
			//pone la skill en el mazo de descarte
			gamesUsersSkillCardsService.discardSkill(game, user, skillCard);
		}else{
			throw new CardNotSelectedException();
		}	
	}
	private void checkIfNumberOfEnemiesIsOK(Integer numberOfEnemiesRequired, List<EnemyCard> listEnemyCardsSelectedId, Game game) throws IncorrectNumberOfEnemiesException,Exception {
		switch (numberOfEnemiesRequired) {
			case 0:	//si la carta no requiere enemigo, da igual el enemigo que selecciones
				break;
			case 1://si la carta requiere 1 enemigo, si tiene mas de uno da un error
				if(listEnemyCardsSelectedId.size()!=1) throw new IncorrectNumberOfEnemiesException();
				break;
			case 2:	//si la carta requiere mas de un enemigo y no seleccionas los requeridos, da un error
				if(enemyCardService.countOnTableEnemiesByGame(game)>=2 && listEnemyCardsSelectedId.size()!=2) throw new IncorrectNumberOfEnemiesException();
				else if(listEnemyCardsSelectedId.size()==0) throw new IncorrectNumberOfEnemiesException();					
				break;
			case 3:
			if(enemyCardService.countOnTableEnemiesByGame(game)==3 && listEnemyCardsSelectedId.size()!=3) throw new IncorrectNumberOfEnemiesException();
			if(enemyCardService.countOnTableEnemiesByGame(game)==2 && listEnemyCardsSelectedId.size()!=2) throw new IncorrectNumberOfEnemiesException();
			else if(listEnemyCardsSelectedId.size()==0) throw new IncorrectNumberOfEnemiesException();	
				break;
		
			default:
				throw new Exception();
				
		}
	}

	@Transactional
	public void executeActions(Game game, User user, Collection<Action> actions, List<EnemyCard> enemies){
		for(Action action : actions){
			switch (action.getType()) {
				case DAMAGE:
					//por cada enemigo
					enemies.stream().forEach(enemy -> {
						try {
							//le hace el daño correspondiente
							gamesEnemiesService.damageEnemy(game, enemy, user, action.getCantidad());
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
					break;
				case DRAW:
					gamesUsersSkillCardsService.drawCards(game, user, action.getCantidad());
					break;
				case RECOVER:
					gamesUsersSkillCardsService.recoverCards(game,user,action.getCantidad());
					break;
				case GAINGLORY:
					try {
						gamesUsersSkillCardsService.gainGlory(game,user,action.getCantidad());
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case GAINGOLD:
					try {
						gamesUsersSkillCardsService.gainGold(game,user,action.getCantidad());
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case LOSEGOLD:
					try {
						gamesUsersSkillCardsService.loseGold(game,user,action.getCantidad());
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case GAINLIFE:
					try {
						gamesUsersSkillCardsService.gainLife(game,user,action.getCantidad());
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case DEFENSE:
					gamesUsersSkillCardsService.defendDamage(game, user, action.cantidad);
					break;
				case DISCARD:
					gamesUsersSkillCardsService.discardCards(game,user,action.getCantidad());
					break;
				case ENDATTACKPHASE:
					gamesUsersSkillCardsService.endAttackTurn(game);
					break;
					
				default:
					break;
			}
		}

	}

	@Transactional	
    public void endTurn(Game game) {
		
		//Nuevo jugador
		List<User> users = new ArrayList<>(game.getUsers());
		Integer newIndex = (users.indexOf(game.getUserPlaying()) + 1) >= users.size() ? 0 : (users.indexOf(game.getUserPlaying()) + 1);
		User newUser = users.get(newIndex);
		game.setUserPlaying(newUser);
			//Skippea el jugador si está muerto
		while(gameUserService.findByGameAndUser(game, newUser).get().getHeroeHealth() <= 0){
			newIndex = (users.indexOf(game.getUserPlaying()) + 1) >= users.size() ? 0 : (users.indexOf(game.getUserPlaying()) + 1);
			newUser = users.get(newIndex);
			game.setUserPlaying(newUser);
		}

		//Rellena las cartas del nuevo jugador

		gamesUsersSkillCardsService.drawCards(game, newUser, 4 - skillCardsService.findAllAvailableSkillsByGameAndUser(game, newUser).size());


		//Rellena la tienda con 5 objetos si alguno fue comprado
		List<MarketCard> onTableMarket=(List<MarketCard>) marketCardService.findAllByGameAndOnTable(game);
		List<MarketCard> onDeckMarket=(List<MarketCard>) marketCardService.findByGameOnDeck(game);
		if(onTableMarket.size()<NUMBER_ITEMS_IN_MARKET) {
			
			int marketToTable=NUMBER_ITEMS_IN_MARKET-onTableMarket.size();

			if(onDeckMarket.size()<marketToTable) {
				for(int i=0; i<onDeckMarket.size(); i++) {
					gameMarketService.findOneItemInGame(game, onDeckMarket.get(i).getId()).setItemState(ItemState.ONTABLE);
				}
			}
			
			else {
				for(int i=0; i<marketToTable; i++) {
					gameMarketService.findOneItemInGame(game, onDeckMarket.get(i).getId()).setItemState(ItemState.ONTABLE);
				}
			}
		}
		//Rellena la arena con 3 enemigos si alguno fue eliminado
		List<EnemyCard> onTableEnemies=(List<EnemyCard>) enemyCardService.findOnTableEnemiesByGame(game);
		List<EnemyCard> onDeckEnemies=(List<EnemyCard>) enemyCardService.findOnDeckEnemiesByGame(game);
		if(onTableEnemies.size()<NUMBER_ENEMIES){
			int enemiesToTable=NUMBER_ENEMIES-onTableEnemies.size();

			if(onDeckEnemies.size()<enemiesToTable) {
				for(int t=0; t<onDeckEnemies.size(); t++) {
					gamesEnemiesService.findByGameAndEnemy(game, onDeckEnemies.get(t)).get().setEnemyState(EnemyState.ONTABLE);
				}
			}
			
			else {
				for(int t=0; t<enemiesToTable; t++) {
					gamesEnemiesService.findByGameAndEnemy(game, onDeckEnemies.get(t)).get().setEnemyState(EnemyState.ONTABLE);
				}
			}
		}

		game.setGameState(GameState.ATTACKING);

    }
}

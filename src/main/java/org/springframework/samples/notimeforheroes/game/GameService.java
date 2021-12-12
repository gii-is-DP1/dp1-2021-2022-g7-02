package org.springframework.samples.notimeforheroes.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.gamesUsers.GameUserService;
import org.springframework.samples.notimeforheroes.heroecard.HeroeCard;
import org.springframework.samples.notimeforheroes.marketcard.MarketCard;
import org.springframework.samples.notimeforheroes.marketcard.MarketCardsService;
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
			
			game.setCreator(creator);

			game.setUsers(List.of(creator));
			
			Collection<MarketCard> market=marketCardService.findAll();
			Set<MarketCard> order = new HashSet<MarketCard>(market);
			game.setMarket(order);
			
			gameRepository.save(game);
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
		List<User> usersWithHeroe= new ArrayList<User>();
		List<User> users=(List<User>) userService.findAllInGame(this.findById(gameId).get());
		for(int i=0; i<users.size(); i++){
            Optional<HeroeCard> heroeCard = gameUserService.findHeroeOfGameUser(this.findById(gameId).get(), users.get(i));
            if(heroeCard.isPresent()){
                usersWithHeroe.add(users.get(i));
            }
        }
		Random ran = new Random();
		Integer index = ran.nextInt(users.size());
		User firstUser = users.get(index);
		Game game = this.findById(gameId).get();
		game.setFirstPlayer(firstUser);
		this.updateGame(game);
	}
}

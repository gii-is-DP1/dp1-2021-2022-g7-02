package org.springframework.samples.notimeforheroes.game.gamesUsers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCard;
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCardsService;
import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCard;
import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCardsService;
import org.springframework.samples.notimeforheroes.cards.skillcard.SkillCard;
import org.springframework.samples.notimeforheroes.cards.skillcard.SkillCardsService;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.stereotype.Service;


@Service
public class GameUserService {

	@Autowired
	GameUserRepository gameUserRepository;

	@Autowired
	GameUserService gameUserService;

	@Autowired
	HeroeCardsService heroeCardsService;
	
	@Autowired
	SkillCardsService skillCardService;
	
	@Autowired
	MarketCardsService marketCardService;

	public Collection<GameUser> findAll(){
		return gameUserRepository.findAll();
	}

	public Optional<GameUser> findById(Integer id){
		return gameUserRepository.findById(id);
	}

	public Optional<GameUser> findByGameAndUser(Game game, User user){
		return gameUserRepository.findByGameAndUser(game.getId(), user.getId());
	}

	public Optional<HeroeCard> findHeroeOfGameUser(Game game, User user){
		Optional<Integer> heroeId = gameUserRepository.findHeroeOfGameUser(game.getId(), user.getId());
		return heroeCardsService.findById(heroeId.orElse(-1));
	}
	
	public Collection<SkillCard> findSkillCardsOfGameUser(Game game, User user){
		Optional<Integer> heroeId = gameUserRepository.findHeroeOfGameUser(game.getId(), user.getId());
		String color=heroeCardsService.findById(heroeId.get()).get().getColor();
		return skillCardService.findByColor(color);
	}
	
	public Collection<MarketCard> findItemsOfGameUser(Game game, User user){
		Collection<MarketCard> cards= new ArrayList<MarketCard>();
		Optional<List<Integer>> ids= gameUserRepository.findItemsOfGameUser(game.getId(), user.getId());
		for(int i=0; i<ids.get().size(); i++) {
			cards.add(marketCardService.findById(ids.get().get(i)).get());
		}
		return cards;
	}
	
	@Transactional
	public void deleteGameUser(GameUser gameUser) {
		gameUserRepository.deleteById(gameUser.getId());
		}
	
	@Transactional
	public void createGameUser(@Valid GameUser gameUser) {
		gameUserRepository.save(gameUser);
	}
	
}



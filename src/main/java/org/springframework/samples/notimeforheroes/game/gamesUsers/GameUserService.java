package org.springframework.samples.notimeforheroes.game.gamesUsers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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

	public Collection<GameUser> findAll() {
		return gameUserRepository.findAll();
	}

	public Optional<GameUser> findById(Integer id) {
		return gameUserRepository.findById(id);
	}

	public List<GameUser> findAllByGame(Game game) {
		return gameUserRepository.findAllByGame(game);
	}

	public List<GameUser> findAllByGameOrderByHealth(Game game){
		return gameUserRepository.findAllByGameOrderByHealth(game);
	}

	public List<GameUser> findByGameUsersAlive(Game game){
		return gameUserRepository.findByGameUsersAlive(game);
	}

	public Optional<GameUser> findByGameAndUser(Game game, User user){
		return gameUserRepository.findByGameAndUser(game, user);
  }

	public Optional<HeroeCard> findHeroeOfGameUser(Game game, User user) {
		Optional<Integer> heroeId = gameUserRepository.findHeroeOfGameUser(game.getId(), user.getId());
		return heroeCardsService.findById(heroeId.orElse(-1));
	}

	@Transactional
	public void saveGameUser(@Valid GameUser gameUser) {
		gameUserRepository.save(gameUser);
	}
	
	@Transactional
	public void deleteGameUser(GameUser gameUser) {
		gameUserRepository.deleteById(gameUser.getId());
	}

	public Integer getAllGoldByUser(User user) {
		Collection<Integer> gold = gameUserRepository.findAllGoldByUser(user);
		Integer AllGold = gold.stream().reduce(0, Integer::sum);
		return AllGold;
	}

	public Integer getAllGloryByUser(User user) {
		Collection<Integer> glory = gameUserRepository.findAllGloryByUser(user);
		Integer AllGlory = glory.stream().reduce(0, Integer::sum);
		return AllGlory;
	}

	public Integer getHeroeFav(User user) {
		Collection<Integer> heroeFav = gameUserRepository.getAllHeroesByUser(user);
		if (heroeFav.size() == 0) {
			return null;
		} else {
			Integer heroeMostRepeat = heroeFav.stream()
					.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
					.entrySet().stream().max((o1, o2) -> o1.getValue().compareTo(o2.getValue()))
					.map(Map.Entry::getKey).orElse(null);
			return heroeMostRepeat;
		}
	}

	public Integer getAllUsesOfaHeroeByUser(User user, int heroeNumber) {
		Collection<Integer> heroesUses = gameUserRepository.getAllHeroesByUser(user);
		if (heroesUses.size() == 0) {
			return 0;
		} else {
			Integer heroeUses = Collections.frequency(heroesUses, heroeNumber);
			return heroeUses;
		}
	}
}

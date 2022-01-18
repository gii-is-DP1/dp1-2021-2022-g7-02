package org.springframework.samples.notimeforheroes.cards.skillcard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.notimeforheroes.actions.Action;
import org.springframework.samples.notimeforheroes.actions.TypesActions;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyCard;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyCardService;
import org.springframework.samples.notimeforheroes.cards.enemycard.gamesEnemies.GamesEnemiesService;
import org.springframework.samples.notimeforheroes.cards.skillcard.gamesUsersSkillcards.GamesUsersSkillCards;
import org.springframework.samples.notimeforheroes.cards.skillcard.gamesUsersSkillcards.GamesUsersSkillCardsService;
import org.springframework.samples.notimeforheroes.cards.skillcard.gamesUsersSkillcards.SkillState;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUser;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUserService;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class SkillCardsService {

	@Autowired
	GameUserService gameUserService;

	@Autowired
	UserService userService;

	@Autowired
	SkillCardsRepository skillRepository;

	@Autowired
	GamesUsersSkillCardsService gamesUsersSkillCardsService;

	@Autowired
	EnemyCardService enemyCardService;

	@Autowired
	GamesEnemiesService gamesEnemiesService;

	@Transactional
	public Collection<SkillCard> findAllPage(Integer pageNo, Integer pageSize) {
		Pageable pagin = PageRequest.of(pageNo, pageSize);
		Page<SkillCard> pageResult = skillRepository.findAll(pagin);
		if (pageResult.hasContent()) {
			return pageResult.getContent();
		} else {
			return new ArrayList<SkillCard>();
		}
	}

	//
	@Transactional
	public Collection<SkillCard> findAll() {
		return skillRepository.findAll();

	}

	//
	@Transactional
	public Optional<SkillCard> findById(Integer id) {
		return skillRepository.findById(id);
	}

	//
	@Transactional
	public Collection<SkillCard> findByColor(String color) {
		return skillRepository.findByColor(color);
	}

	public Collection<SkillCard> findByGameAndUser(Game game, User user) {
		return skillRepository.findAllSkillsByGameAndUser(game, user);
	}

	public List<SkillCard> findAllAvailableSkillsByGameAndUser(Game game, User user) {
		return skillRepository.findAllAvailableSkillsByGameAndUser(game, user);
	}

	public List<SkillCard> findAllOnDeckSkillsByGameAndUser(Game game, User user) {
		return skillRepository.findAllOnDeckSkillsByGameAndUser(game, user);
	}

	public List<SkillCard> findAllOnDiscardedSkillsByGameAndUser(Game game, User user) {
		return skillRepository.findAllOnDiscardedSkillsByGameAndUser(game, user);
	}

	public Integer numberOfEnemiesOfSkillCard(SkillCard skillCard) throws Exception {
		Integer skillCardId = skillCard.getId();
		List<Integer> noEnemyRequiredIdList = List.of(1, 13, 14, 20, 21, 26, 27, 29, 30, 39, 43, 56, 57, 58);
		List<Integer> oneEnemyRequiredIdList = List.of(2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 16, 17,
				18, 19, 22, 23, 24, 25, 28, 32, 33, 34, 35, 36, 37, 38, 40, 41, 42, 44, 45, 46, 47, 48, 49, 50, 51, 52,
				53, 54, 55);
		List<Integer> twoEnemiesRequiredIdList = List.of(11, 12);
		List<Integer> threeEnemiesRequiredIdList = List.of(31);

		if (noEnemyRequiredIdList.contains(skillCardId))
			return 0;
		else if (oneEnemyRequiredIdList.contains(skillCardId)) {
			return 1;
		} else if (twoEnemiesRequiredIdList.contains(skillCardId)) {
			return 2;
		} else if (threeEnemiesRequiredIdList.contains(skillCardId)) {
			return 3;
		} else {
			throw new Exception("No se pudieron procesar los enemigos necesarios de la carta");
		}
	}

	@Transactional
	public void saveSkillCard(@Valid SkillCard skill) {
		skillRepository.save(skill);
	}

	@Transactional
	public void deleteSkillCard(SkillCard skill) {
		skillRepository.delete(skill);
	}

	// ----------------------------------------------------------------------------------------//
	// ----------------------------------------------------------------------------------------//
	// -------------------------LOGICA DE
	// CARTAS-----------------------------------------------//
	// ----------------------------------------------------------------------------------------//
	// ----------------------------------------------------------------------------------------//

	@Transactional
	public void useDisparoRápido(List<EnemyCard> enemiesTargetedList, Game game, User user, SkillCard skillCard) {
		enemiesTargetedList.forEach(enemy -> {
			try {
				gamesEnemiesService.damageEnemy(game, enemy, user, 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		List<SkillCard> skillCards = findAllOnDeckSkillsByGameAndUser(game, user);
		SkillCard newSkill = skillCards.get(0);
		GamesUsersSkillCards newSkillGame = gamesUsersSkillCardsService.findByGameUserSkill(game, user, newSkill).get();
		if (newSkill.getName().equals(skillCard.getName())) { // Si la carta robada es un disparo rápido
			newSkillGame.setSkillState(SkillState.ONHAND);
			gamesUsersSkillCardsService.saveGameUserSkillCard(newSkillGame);
			System.out.println("[DEBUG]: SE HA ROBADO UNA CARTA Disparo Rápido");
		}
	}

	@Transactional
	public void useLluviaDeFlechas(List<EnemyCard> enemiesTargetedList, Game game, User user, SkillCard skillCard) {
		for (EnemyCard enemyCard : enemiesTargetedList) {
			try {
				gamesEnemiesService.damageEnemy(game, enemyCard, user, 2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Collection<GameUser> players = gameUserService.findAllByGame(game);
		players.remove(gameUserService.findByGameAndUser(game, user).get());
		GameUser playerWithLessWounds = players.stream().sorted(new Comparator<GameUser>() {
			@Override
			public int compare(GameUser p1, GameUser p2) {
				Integer heridasP1 = p1.getHeroe().getMaxHealth() - p1.getHeroeHealth();
				Integer heridasP2 = p2.getHeroe().getMaxHealth() - p2.getHeroeHealth();
				return heridasP1 < heridasP2 ? -1 : heridasP1 == heridasP2 ? 0 : 1;
			}
		}).collect(Collectors.toList()).get(0);

		System.out.println("Cartas en el mazo del jugador "
				+ userService.findByGameUser(playerWithLessWounds).getUsername() + ": "
				+ findAllOnDeckSkillsByGameAndUser(game, userService.findByGameUser(playerWithLessWounds)).size());
		System.out.println("Cartas descartadas del jugador "
				+ userService.findByGameUser(playerWithLessWounds).getUsername() + ": "
				+ findAllOnDiscardedSkillsByGameAndUser(game, userService.findByGameUser(playerWithLessWounds)).size());

		gamesUsersSkillCardsService.discardCards(game, userService.findByGameUser(playerWithLessWounds), 2);

		System.out.println("Cartas en el mazo del jugador "
				+ userService.findByGameUser(playerWithLessWounds).getUsername() + ": "
				+ findAllOnDeckSkillsByGameAndUser(game, userService.findByGameUser(playerWithLessWounds)).size());
		System.out.println("Cartas descartadas del jugador "
				+ userService.findByGameUser(playerWithLessWounds).getUsername() + ": "
				+ findAllOnDiscardedSkillsByGameAndUser(game, userService.findByGameUser(playerWithLessWounds)).size());
	}

	public void useRecogerFlechas(Game game, User user, SkillCard skillCard) {
		List<SkillCard> disparoRapidoCards = this.findAllOnDiscardedSkillsByGameAndUser(game, user).stream()
				.filter(card -> card.getName().equals("Disparo Rapido")).collect(Collectors.toList());
		if (disparoRapidoCards.size() != 0) {
			GamesUsersSkillCards gusc = gamesUsersSkillCardsService
					.findByGameUserSkill(game, user, disparoRapidoCards.get(0)).get();
			gusc.setSkillState(SkillState.ONDECK);
			gamesUsersSkillCardsService.saveGameUserSkillCard(gusc);
		}
	}


    public void useEscudo(EnemyCard enemyTargeted, Game game, User user, SkillCard skillCard) {
		Integer dañoDeEnemigoSeleccionado = gamesEnemiesService.findByGameAndEnemy(game, enemyTargeted).get().getHealth();
		gamesUsersSkillCardsService.defendDamage(game, user, dañoDeEnemigoSeleccionado);
		gamesUsersSkillCardsService.endAttackTurn(game);
    }


    public void useEspadazo(EnemyCard enemyCard, Game game, User user, SkillCard skillCard) throws Exception {
		gamesEnemiesService.damageEnemy(game, enemyCard, user, 1);
		Integer numeroRandom = new Random().nextInt(4);
		if(numeroRandom == 0)
			gamesUsersSkillCardsService.drawCards(game, user, 1);
    }


	public void useTodoONada(EnemyCard enemyCard, Game game, User user, SkillCard skillCard) throws Exception {
		Integer daño = 1;
		List<SkillCard> mazo = findAllOnDeckSkillsByGameAndUser(game, user);
		for(SkillCard card : mazo){	//Comprueba cual es la siguiente carta del mazo que tiene una única acción de daño, y le añade el valor de dicho daño al ataque
			List<Action> attackActions = card.getActions().stream().filter(action -> action.getType().equals(TypesActions.DAMAGE)).collect(Collectors.toList());
			if(attackActions.size() == 1){
				daño += attackActions.get(0).getCantidad();
				break;
			}		
		}
		gamesEnemiesService.damageEnemy(game, enemyCard, user, daño);
	}


    public void useVozDeAliento(Game game, User user, SkillCard skillCard) throws Exception {

		//Devuelve dos cartas del mazo de descarte a todos los jugadores vivos
		for(User userInGame : game.getUsers()){
			List<SkillCard> cartasDescartadas = findAllOnDiscardedSkillsByGameAndUser(game, userInGame);
			if(cartasDescartadas.size() == 1 && gameUserService.findByGameAndUser(game, userInGame).get().getHeroeHealth() > 0){
				GamesUsersSkillCards gusc = gamesUsersSkillCardsService.findByGameUserSkill(game, userInGame, cartasDescartadas.get(0)).orElse(null);
				gusc.setSkillState(SkillState.ONDECK);
				gamesUsersSkillCardsService.saveGameUserSkillCard(gusc);
			}else if(cartasDescartadas.size() >= 2 && gameUserService.findByGameAndUser(game, userInGame).get().getHeroeHealth() > 0){
				for(int i = 0; i<2 ; i++){
					GamesUsersSkillCards gusc = gamesUsersSkillCardsService.findByGameUserSkill(game, userInGame, cartasDescartadas.get(i)).orElse(null);
					gusc.setSkillState(SkillState.ONDECK);
					gamesUsersSkillCardsService.saveGameUserSkillCard(gusc);
				}
			}
		}

		gamesUsersSkillCardsService.drawCards(game, user, 1);
		gamesUsersSkillCardsService.gainGlory(game, user, 1);


    }

}

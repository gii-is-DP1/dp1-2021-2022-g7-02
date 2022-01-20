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
import org.springframework.samples.notimeforheroes.game.exceptions.DontHaveEnoughGoldToBuyException;
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

	//
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

	//
	@Transactional
	public SkillCard findByName(String name) {
		return skillRepository.findByName(name);
	}

	//
	public Collection<SkillCard> findByGameAndUser(Game game, User user) {
		return skillRepository.findAllSkillsByGameAndUser(game, user);
	}

	//
	public List<SkillCard> findAllAvailableSkillsByGameAndUser(Game game, User user) {
		return skillRepository.findAllAvailableSkillsByGameAndUser(game, user);
	}

	//
	public List<SkillCard> findAllOnDeckSkillsByGameAndUser(Game game, User user) {
		return skillRepository.findAllOnDeckSkillsByGameAndUser(game, user);
	}

	//
	public List<SkillCard> findAllOnDiscardedSkillsByGameAndUser(Game game, User user) {
		return skillRepository.findAllOnDiscardedSkillsByGameAndUser(game, user);
	}

	public Integer numberOfEnemiesOfSkillCard(SkillCard skillCard) throws Exception {
		Integer skillCardId = skillCard.getId();
		List<Integer> noEnemyRequiredIdList = List.of(1, 13, 14, 20, 21, 26, 27, 29, 30, 39, 43, 56, 57, 58, 62, 64, 65, 67, 68, 69, 70, 71, 72);
		List<Integer> oneEnemyRequiredIdList = List.of(2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 16, 17,
				18, 19, 22, 23, 24, 25, 28, 32, 33, 34, 35, 36, 37, 38, 40, 41, 42, 44, 45, 46, 47, 48, 49, 50, 51, 52,
				53, 54, 55, 59, 60, 61, 63, 73);
		List<Integer> twoEnemiesRequiredIdList = List.of(11, 12);
		List<Integer> threeEnemiesRequiredIdList = List.of(31, 66);

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
	// -------------------------LOGICA DE CARTAS-----------------------------------------------//
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
	public void useLluviaDeFlechas(List<EnemyCard> enemiesTargetedList, Game game, User user, SkillCard skillCard) throws Exception {
		for (EnemyCard enemyCard : enemiesTargetedList) {
			gamesEnemiesService.damageEnemy(game, enemyCard, user, 2);
		}
		GameUser playerWithLessWounds = gameUserService.findAllByGameOrderByHealth(game).get(0);
		gamesUsersSkillCardsService.discardCards(game, userService.findByGameUser(playerWithLessWounds), 2);		
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
		Integer dañoDeEnemigoSeleccionado = gamesEnemiesService.findByGameAndEnemy(game, enemyTargeted).get()
				.getHealth();
		gamesUsersSkillCardsService.defendDamage(game, user, dañoDeEnemigoSeleccionado);
		gamesUsersSkillCardsService.endAttackTurn(game);
	}
	
	public void useEspadazo(EnemyCard enemyCard, Game game, User user, SkillCard skillCard) throws Exception {
		gamesEnemiesService.damageEnemy(game, enemyCard, user, 1);
		Integer numeroRandom = new Random().nextInt(4);
		if (numeroRandom == 0)
			gamesUsersSkillCardsService.drawCards(game, user, 1);
	}

	public void useTodoONada(EnemyCard enemyCard, Game game, User user, SkillCard skillCard) throws Exception {
		Integer daño = 1;
		List<SkillCard> mazo = findAllOnDeckSkillsByGameAndUser(game, user);
		for (SkillCard card : mazo) { // Comprueba cual es la siguiente carta del mazo que tiene una única acción de
										// daño, y le añade el valor de dicho daño al ataque
			List<Action> attackActions = card.getActions().stream()
					.filter(action -> action.getType().equals(TypesActions.DAMAGE)).collect(Collectors.toList());
			if (attackActions.size() == 1) {
				daño += attackActions.get(0).getCantidad();
				break;
			}
		}
		gamesEnemiesService.damageEnemy(game, enemyCard, user, daño);
	}

	public void useVozDeAliento(Game game, User user, SkillCard skillCard) throws Exception {

		// Devuelve dos cartas del mazo de descarte a todos los jugadores vivos
		for (User userInGame : game.getUsers()) {
			List<SkillCard> cartasDescartadas = findAllOnDiscardedSkillsByGameAndUser(game, userInGame);
			if (cartasDescartadas.size() == 1
					&& gameUserService.findByGameAndUser(game, userInGame).get().getHeroeHealth() > 0) {
				GamesUsersSkillCards gusc = gamesUsersSkillCardsService
						.findByGameUserSkill(game, userInGame, cartasDescartadas.get(0)).orElse(null);
				gusc.setSkillState(SkillState.ONDECK);
				gamesUsersSkillCardsService.saveGameUserSkillCard(gusc);
			} else if (cartasDescartadas.size() >= 2
					&& gameUserService.findByGameAndUser(game, userInGame).get().getHeroeHealth() > 0) {
				for (int i = 0; i < 2; i++) {
					GamesUsersSkillCards gusc = gamesUsersSkillCardsService
							.findByGameUserSkill(game, userInGame, cartasDescartadas.get(i)).orElse(null);
					gusc.setSkillState(SkillState.ONDECK);
					gamesUsersSkillCardsService.saveGameUserSkillCard(gusc);
				}
			}
		}
		gamesUsersSkillCardsService.drawCards(game, user, 1);
		gamesUsersSkillCardsService.gainGlory(game, user, 1);
    }
	public void useAuraProtectora(Game game, User user, SkillCard skillCard){
		gamesUsersSkillCardsService.defendDamage(game, user, 100);
		gamesUsersSkillCardsService.discardCards(game, user, enemyCardService.findOnTableEnemiesByGame(game).size());
	}

	public void useBolaDeFuego(Game game, User user, SkillCard skillCard) throws Exception {

		List<EnemyCard> enemiesOnTable = enemyCardService.findOnTableEnemiesByGame(game);
		for (EnemyCard e : enemiesOnTable) {
			gamesEnemiesService.damageEnemy(game, e, user, 2);
		}

		game.getUsers().stream().filter(otherUser -> !otherUser.equals(user))
								.forEach(otherUser -> gamesUsersSkillCardsService.discardCards(game, otherUser, 1));

	}

    public void useDisparoGelido(EnemyCard enemyCard, Game game, User user, SkillCard skillCard) throws Exception {
		GameUser player = gameUserService.findByGameAndUser(game, user).get();
		gamesUsersSkillCardsService.drawCards(game, user, 1);
		if(!player.getDamageShielded().equals(gamesEnemiesService.findByGameAndEnemy(game, enemyCard).get().getHealth())){
			gamesUsersSkillCardsService.defendDamage(game, user, gamesEnemiesService.findByGameAndEnemy(game, enemyCard).get().getHealth());
		}
		gamesEnemiesService.damageEnemy(game, enemyCard, user, 1);
	}

	public void useFlechaCorrosiva(EnemyCard enemyCard, Game game, User user, SkillCard skillCard) throws Exception {
		Integer numeroAleatorio = new Random().nextInt(5);
		gamesEnemiesService.damageEnemy(game, enemyCard, user, numeroAleatorio.equals(0) ? 1 : 2);
		gamesUsersSkillCardsService.discardCards(game, user, 1);
	}

    public void useGolpeDeBaston(EnemyCard enemyCard, Game game, User user, SkillCard skillCard) throws Exception {
		//50% de posibilidades de hacer 2 de daño.
		gamesEnemiesService.damageEnemy(game, enemyCard, user, Integer.valueOf(new Random().nextInt(2)).equals(0) ? 1 : 2);
    }
	

	public void useOrbeCurativo(Game game, User user, SkillCard skillCard) throws Exception{
		for(User userInGame : game.getUsers()){
			gamesUsersSkillCardsService.recoverCards(game, userInGame, 2);
		}
		gamesUsersSkillCardsService.gainLife(game, user, 1);
	}

	public void useTorrenteDeLuz(EnemyCard enemyCard, Game game, User user, SkillCard skillCard) throws Exception {
		gamesEnemiesService.damageEnemy(game, enemyCard, user, 2);
		gamesUsersSkillCardsService.gainGlory(game, user, 1);
			
		game.getUsers().stream().filter(otherUser -> !otherUser.equals(user))
								.forEach(otherUser -> gamesUsersSkillCardsService.recoverCards(game, otherUser, 2));		
	}

	public void useAlCorazon(EnemyCard enemyCard, Game game, User user, SkillCard skillCard) throws Exception {
		gamesEnemiesService.damageEnemy(game, enemyCard, user, 4);
		if(new Random().nextInt(100) < 35){	//Probabilidad del 35%
			gamesUsersSkillCardsService.gainGold(game, user, 1);
		}
		gamesUsersSkillCardsService.discardCards(game, user, 1);
	}

	public void useAtaqueFurtivo(EnemyCard enemyCard, Game game, User user, SkillCard skillCard) throws Exception {
		
		gamesEnemiesService.damageEnemy(game, enemyCard, user, 2);
		if(new Random().nextInt(4) == 0){
			gamesUsersSkillCardsService.gainGold(game, user, 1);
		}
	}

    public void useBallestaPrecisa(EnemyCard enemyCard, Game game, User user, SkillCard skillCard) throws Exception {
		Boolean masDeLaMitadDeLaVida = (enemyCard.getMaxHealth() / 2.0) < gamesEnemiesService.findByGameAndEnemy(game, enemyCard).get().getHealth();
		gamesEnemiesService.damageEnemy(game, enemyCard, user, masDeLaMitadDeLaVida ? 3 : 2);
    }

	public void useEngañar(Game game, User user, SkillCard skillCard) throws Exception {
		GameUser player = gameUserService.findByGameAndUser(game, user).get();
		if(player.getGlory() != null && player.getGold() >= 2){
			gamesUsersSkillCardsService.loseGold(game, user, 2);
			gamesUsersSkillCardsService.defendDamage(game, user, 2);
		}else{
			throw new DontHaveEnoughGoldToBuyException();
		}
	}

	public void useRobarBolsillos(Game game, User user, SkillCard skillCard) throws Exception {
		List<User> otherUsersWithOneGold = game.getUsers().stream()
				.filter(u -> !u.equals(user))
				.map(u -> gameUserService.findByGameAndUser(game, u).get())
				.filter(player -> player.getGold() >= 1)
				.map(player -> userService.findByGameUser(player))
				.collect(Collectors.toList());

		gamesUsersSkillCardsService.gainGold(game, user, otherUsersWithOneGold.size());
		for (User u : otherUsersWithOneGold) {
			gamesUsersSkillCardsService.loseGold(game, u, 1);
		}
	}

    public void useSaqueo(Game game, User user, SkillCard skillCard) throws Exception {
		Integer oroGanado = 2 * enemyCardService.findOnTableEnemiesByGame(game).size();
		gamesUsersSkillCardsService.gainGold(game, user, oroGanado);
    }



}

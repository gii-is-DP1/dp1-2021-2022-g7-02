package org.springframework.samples.notimeforheroes.cards.skillcard.gamesUsersSkillcards;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCardsService;
import org.springframework.samples.notimeforheroes.cards.skillcard.SkillCard;
import org.springframework.samples.notimeforheroes.cards.skillcard.SkillCardsService;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.game.GameService;
import org.springframework.samples.notimeforheroes.game.GameState;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUser;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUserService;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.stereotype.Service;

@Service
public class GamesUsersSkillCardsService {

    @Autowired
    GameService gameService;

    @Autowired
    GamesUsersSkillCardsRepository gamesUsersSkillCardsRepository;

    @Autowired
    GameUserService gamesUsersService;

    @Autowired
    SkillCardsService skillCardsService;

    @Autowired
    HeroeCardsService heroeCardsService;

    public Collection<GamesUsersSkillCards> findAll(){
        return gamesUsersSkillCardsRepository.findAll();
    }


    public Optional<GamesUsersSkillCards> findByGameUserSkill(Game game, User user, SkillCard skillCard){

        Optional<GameUser> player = gamesUsersService.findByGameAndUser(game, user);
        return gamesUsersSkillCardsRepository.findByGameUserSkill(player.orElse(null), skillCard);
    }

    public void saveGameUserSkillCard(@Valid GamesUsersSkillCards gameUserSkillCard){
        gamesUsersSkillCardsRepository.save(gameUserSkillCard);
    }


    public Collection<GamesUsersSkillCards> findAllAvailableSkillsandOnTableByGameAndUser(Game game, User user){
		return gamesUsersSkillCardsRepository.findAllAvailableSkillsAndOnTableByGameAndUser(game, user);
	}

    @Transactional
    public void discardSkill(Game game, User user, SkillCard skillCard) {   //Descarta la skillcard
        Optional<GamesUsersSkillCards> gamesUsersSkillCardsOpt = this.findByGameUserSkill(game, user, skillCard);
        if(gamesUsersSkillCardsOpt.isPresent()){
            GamesUsersSkillCards gamesUsersSkillCards = gamesUsersSkillCardsOpt.get();
            gamesUsersSkillCards.setSkillState(SkillState.DISCARD);
		    this.saveGameUserSkillCard(gamesUsersSkillCards);
        }
    }

    public void drawCards(Game game, User user, Integer cantidad) {

        //Quita una vida y pone todas las cartas del descarte en el mazo
        List<SkillCard> onDeckCards = skillCardsService.findAllOnDeckSkillsByGameAndUser(game, user);
        if(onDeckCards.size() <= cantidad){     
            System.out.println("[DRAW] El jugador " + user.getUsername() + " pierde una vida!");
            GameUser player = gamesUsersService.findByGameAndUser(game, user).get();
            //le quito 1 de vida al heroe
            player.setHeroeHealth(player.getHeroeHealth() - 1);
            gamesUsersService.saveGameUser(player);
            if(player.getHeroeHealth()==0){
                gameService.endTurn(game);
            }else{
                List<SkillCard> discardedCards = skillCardsService.findAllOnDiscardedSkillsByGameAndUser(game, user);
                //devuelve todas las cartas del mazo de descarte al mazo
                discardedCards.stream().forEach(c -> {
                GamesUsersSkillCards gusc = findByGameUserSkill(game, user, c).get();
                gusc.setSkillState(SkillState.ONDECK);
                //guardas en el repository el deck completo
                gamesUsersSkillCardsRepository.save(gusc);
            });
            }
        }

        //Sólo se pueden robar tal que se tengan como máximo 4 en la mano
        if(cantidad + skillCardsService.findAllAvailableSkillsByGameAndUser(game, user).size() > gameService.MAX_NUMBER_SKILLS_IN_HAND)
            cantidad = gameService.MAX_NUMBER_SKILLS_IN_HAND - skillCardsService.findAllAvailableSkillsByGameAndUser(game, user).size();

        //Roba cartas
        onDeckCards = skillCardsService.findAllOnDeckSkillsByGameAndUser(game, user);
        for(int i = 0; i<cantidad; i++){
            GamesUsersSkillCards gusc = findByGameUserSkill(game, user, onDeckCards.get(i)).get();
            System.out.println("[DRAW] El jugador " + user.getUsername() + " ha robado la carta " + onDeckCards.get(i).getName());
            gusc.setSkillState(SkillState.ONHAND);
            saveGameUserSkillCard(gusc);
        }
    }

    @Transactional
    public void discardCards(Game game, User user, Integer cantidad) {
        try {
            List<SkillCard> onDeckCards = skillCardsService.findAllOnDeckSkillsByGameAndUser(game, user);
            Integer cartasRestantes = onDeckCards.size();
            if (cartasRestantes <= cantidad) {
                System.out.println("[DISCARD] El jugador " + user.getUsername() + " pierde una vida!");

                // eliminar 1 vida
                GameUser player = gamesUsersService.findByGameAndUser(game, user).get();
                player.setHeroeHealth(player.getHeroeHealth() - 1);
                gamesUsersService.saveGameUser(player);

                // si el numero de vidas del jugador es 0 pasa al siguiente turno y en el metodo
                // endTurn si el jugador tiene 0 vidas, salta su turno
                if (player.getHeroeHealth() == 0) {
                    gameService.endTurn(game);
                } else {
                    // Volver a poner todas las cartas en el mazo
                    skillCardsService.findAllOnDiscardedSkillsByGameAndUser(game, user).stream().forEach(c -> {
                        GamesUsersSkillCards gusc = findByGameUserSkill(game, user, c).get();
                        gusc.setSkillState(SkillState.ONDECK);
                        gamesUsersSkillCardsRepository.save(gusc);
                    });

                    onDeckCards = skillCardsService.findAllOnDeckSkillsByGameAndUser(game, user);
                    for (int i = 0; i < cantidad - cartasRestantes; i++) {
                        GamesUsersSkillCards gusc = findByGameUserSkill(game, user, onDeckCards.get(i)).get();
                        System.out.println("[DISCARD] El jugador " + user.getUsername() + " pierde la carta "
                                + onDeckCards.get(i).getName());
                        gusc.setSkillState(SkillState.DISCARD);
                        saveGameUserSkillCard(gusc);
                    }
                }
            } else {
                for (int i = 0; i < cantidad; i++) {
                    GamesUsersSkillCards gusc = findByGameUserSkill(game, user, onDeckCards.get(i)).get();
                    System.out.println("[DISCARD] El jugador " + user.getUsername() + " pierde la carta "
                            + onDeckCards.get(i).getName());
                    gusc.setSkillState(SkillState.DISCARD);
                    saveGameUserSkillCard(gusc);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void recoverCards(Game game, User user, Integer cantidad) {
        if(gamesUsersService.findByGameAndUser(game, user).get().getHeroeHealth() > 0){
            List<SkillCard> onDiscardDeckCards = skillCardsService.findAllOnDiscardedSkillsByGameAndUser(game, user);
            if(onDiscardDeckCards.size()<cantidad) cantidad=onDiscardDeckCards.size();

            for (int i = 0; i < cantidad; i++) {
                System.out.println("[RECOVERCARDS] El jugador " + user.getUsername() + " ha recuperado la carta " + onDiscardDeckCards.get(i).getName());
                GamesUsersSkillCards gusc= findByGameUserSkill(game, user, onDiscardDeckCards.get(i)).get();
                gusc.setSkillState(SkillState.ONDECK);
                saveGameUserSkillCard(gusc);
            }
        }
    }

    public void gainGlory(Game game, User user, Integer cantidad) throws Exception {
        Optional<GameUser> gameUserOpt = gamesUsersService.findByGameAndUser(game, user);
        if(gameUserOpt.isPresent()){
            GameUser gameUser=gameUserOpt.get();
            gameUser.setGlory(gameUser.getGlory()+cantidad);
            System.out.println("[GAINGLORY] El jugador " + user.getUsername() + " ha ganado " + cantidad + " de gloria");
            gamesUsersService.saveGameUser(gameUser);
        }else{  //SI EL USER NO ESTÁ EN GAME
            throw new Exception("Player at game not found");
        }

    }

    public void gainGold(Game game, User user, Integer cantidad) throws Exception {
        Optional<GameUser> gameUserOpt = gamesUsersService.findByGameAndUser(game, user);
        if(gameUserOpt.isPresent()){
            GameUser gameUser=gameUserOpt.get();
            System.out.println("[GAINGOLD] El jugador " + user.getUsername() + " ha ganado " + cantidad + " de oro");
            gameUser.setGold(gameUser.getGold()+cantidad);
            gamesUsersService.saveGameUser(gameUser);
        }else{  //SI EL USER NO ESTÁ EN GAME
            throw new Exception("Player at game not found");
        }
    }

    public void loseGold(Game game, User user, Integer cantidad) throws Exception {
        Optional<GameUser> gameUserOpt = gamesUsersService.findByGameAndUser(game, user);
        if(gameUserOpt.isPresent()){
            GameUser gameUser=gameUserOpt.get();
            if(cantidad>=gameUser.getGold())gameUser.setGold(0);
            else{
                System.out.println("[LOSEGOLD] El jugador " + user.getUsername() + " ha perdido " + cantidad + " de oro");
                gameUser.setGold(gameUser.getGold()-cantidad);
            }
            gamesUsersService.saveGameUser(gameUser);
        }else{  //SI EL USER NO ESTÁ EN GAME
            throw new Exception("Player at game not found");
        }
    }

    public void gainLife(Game game, User user, Integer cantidad) throws Exception {
        GameUser gameUser = gamesUsersService.findByGameAndUser(game, user).get();
        Integer currentHealth = gameUser.getHeroeHealth();
        Integer maxHealth = gameUser.getHeroe().getMaxHealth();
        if(currentHealth < maxHealth){
            gameUser.setHeroeHealth(currentHealth + cantidad < maxHealth ? currentHealth + cantidad : maxHealth);
            System.out.println( String.format("[GAINLIFE] El jugador %s ha ganado %d de vida (%d + %d = %d)", user.getUsername(), cantidad, currentHealth, cantidad, gameUser.getHeroeHealth()));
        }
    }

    public void defendDamage(Game game, User user, Integer cantidad) {

        GameUser player = gamesUsersService.findByGameAndUser(game, user).get();
        if(player.getDamageShielded() == null)
            player.setDamageShielded(0);
		player.setDamageShielded(player.getDamageShielded() + cantidad);
        System.out.println("[DEFENDDAMAGE] El jugador " + user + " se ha defendido de " + cantidad + " de daño más. Ahora evitará " + player.getDamageShielded());
		gamesUsersService.saveGameUser(player);
    }

    public void endAttackTurn(Game game) {
        game.setGameState(GameState.DEFENDING);
		gameService.updateGame(game);
    }
}


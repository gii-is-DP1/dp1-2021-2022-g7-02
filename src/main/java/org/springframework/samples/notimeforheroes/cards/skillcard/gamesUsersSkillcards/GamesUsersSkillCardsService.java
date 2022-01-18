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

    public Optional<GamesUsersSkillCards> findByGameUserSkill(GameUser gameUser, SkillCard skillCard){
        return gamesUsersSkillCardsRepository.findByGameUserSkill(gameUser, skillCard);
    }

    public Optional<GamesUsersSkillCards> findByGameUserSkill(Game game, User user, SkillCard skillCard){
        return gamesUsersSkillCardsRepository.findByGameUserSkill(gamesUsersService.findByGameAndUser(game, user).get(), skillCard);
    }

    public void saveGameUserSkillCard(@Valid GamesUsersSkillCards gameUserSkillCard){
        gamesUsersSkillCardsRepository.save(gameUserSkillCard);
    }


    public Collection<GamesUsersSkillCards> findAllAvailableSkillsandOnTableByGameAndUser(Game game, User user){
		return gamesUsersSkillCardsRepository.findAllAvailableSkillsAndOnTableByGameAndUser(game, user);
	}

    @Transactional
    public void discardSkill(Game game, User user, SkillCard skillCard) {   //Descarta la skillcard
        GamesUsersSkillCards gamesUsersSkillCards = this.findByGameUserSkill(game, user, skillCard).get();
		gamesUsersSkillCards.setSkillState(SkillState.DISCARD);
		this.saveGameUserSkillCard(gamesUsersSkillCards);
    }

    public void drawCards(Game game, User user, Integer cantidad) {
        List<SkillCard> onDeckCards = skillCardsService.findAllOnDeckSkillsByGameAndUser(game, user);
        if(onDeckCards.size() <= cantidad){     //Si le quedan menos cartas en el mazo de las que tiene que robar
            System.out.println("[DRAW] El jugador " + user.getUsername() + " pierde una vida!");
            GameUser player = gamesUsersService.findByGameAndUser(game, user).get();
            //le quito 1 de vida al heroe
            //falta por concretar como se haria si llega a 0 vidas, para eliminarlo del juego
            player.setHeroeHealth(player.getHeroeHealth() - 1);
            List<SkillCard> discardedCards = skillCardsService.findAllOnDiscardedSkillsByGameAndUser(game, user);
            //devuelve todas las cartas del mazo de descarte al mazo
            discardedCards.stream().forEach(c -> {
                GamesUsersSkillCards gusc = findByGameUserSkill(game, user, c).get();
                gusc.setSkillState(SkillState.ONDECK);
                //guardas en el repository el deck completo
                gamesUsersSkillCardsRepository.save(gusc);
            });
            //guardas para que la vida de tu heroe se quede guardado
            gamesUsersService.saveGameUser(player);
        }
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
        if(cartasRestantes < cantidad){

            System.out.println("[DISCARD] El jugador " + user.getUsername() + " pierde una vida!");

            //eliminar 1 vida
            GameUser player= gamesUsersService.findByGameAndUser(game, user).get();
            player.setHeroeHealth(player.getHeroeHealth() - 1);  
            gamesUsersService.saveGameUser(player);

            //Volver a poner todas las cartas en el mazo
            skillCardsService.findAllOnDiscardedSkillsByGameAndUser(game, user).stream().forEach(c -> {
                GamesUsersSkillCards gusc = findByGameUserSkill(game, user, c).get();
                gusc.setSkillState(SkillState.ONDECK);
                gamesUsersSkillCardsRepository.save(gusc);
            });

            onDeckCards = skillCardsService.findAllOnDeckSkillsByGameAndUser(game, user);
            for(int i = 0; i<cantidad - cartasRestantes; i++){
                GamesUsersSkillCards gusc = findByGameUserSkill(game, user, onDeckCards.get(i)).get();
                System.out.println("[DISCARD] El jugador " + user.getUsername() + " pierde la carta " + onDeckCards.get(i).getName());
                gusc.setSkillState(SkillState.DISCARD);
                saveGameUserSkillCard(gusc);
            }
        }else{
            for(int i = 0; i<cantidad; i++){
                GamesUsersSkillCards gusc = findByGameUserSkill(game, user, onDeckCards.get(i)).get();
                System.out.println("[DISCARD] El jugador " + user.getUsername() + " pierde la carta " + onDeckCards.get(i).getName());
                gusc.setSkillState(SkillState.DISCARD);
                saveGameUserSkillCard(gusc);
            }
        }
            
        } catch (Exception e) {
            e.printStackTrace();
        }         
    }

    @Transactional
    public void deleteGamesUsersSkillCard(GamesUsersSkillCards g){
        gamesUsersSkillCardsRepository.delete(g);
    }

    public void recoverCards(Game game, User user, Integer cantidad) {
        List<SkillCard> onDiscardDeckCards = skillCardsService.findAllOnDiscardedSkillsByGameAndUser(game, user);
        if(onDiscardDeckCards.size()<cantidad) cantidad=onDiscardDeckCards.size();

        for (int i = 0; i < cantidad; i++) {
            GamesUsersSkillCards gusc= findByGameUserSkill(game, user, onDiscardDeckCards.get(i)).get();
            gusc.setSkillState(SkillState.ONDECK);
            saveGameUserSkillCard(gusc);
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
        Optional<GameUser> gameUserOpt = gamesUsersService.findByGameAndUser(game, user);

        if(gameUserOpt.isPresent()){
            GameUser gameUser=gameUserOpt.get();
            //si su vida es menor que la vida maxima
            if(gameUser.getHeroeHealth()<gameUser.getHeroe().getMaxHealth()){
                if(gameUser.getHeroeHealth() + cantidad > gameUser.getHeroe().getMaxHealth())
                    gameUser.setHeroeHealth(gameUser.getHeroe().getMaxHealth());
                else{
                    gameUser.setHeroeHealth(gameUser.getHeroeHealth()+cantidad);
                }
                    
                System.out.println("[GAINLIFE] El jugador " + user.getUsername() + " ha ganado " + cantidad + " de vida");  
            } 
            gamesUsersService.saveGameUser(gameUser);
        }else{  //SI EL USER NO ESTÁ EN GAME
            throw new Exception("Player at game not found");
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


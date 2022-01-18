package org.springframework.samples.notimeforheroes.cards.enemycard.gamesEnemies;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyCard;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyCardService;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyState;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.game.GameService;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUser;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUserService;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.stereotype.Service;

@Service
public class GamesEnemiesService {

    @Autowired
    GamesEnemiesRepository gamesEnemiesRepository;

    @Autowired
    EnemyCardService enemyCardService;

    @Autowired
    GameUserService gameUserService;

    @Autowired
    GameService gameService;

    public Collection<GamesEnemies> findAll(){
        return gamesEnemiesRepository.findAll();
    }

    public Collection<GamesEnemies> findAllInGame(Game game){
        return gamesEnemiesRepository.findAllInGame(game);
    }

    public Collection<GamesEnemies> findAllInGameOnTable(Game game){
        return gamesEnemiesRepository.findAllInGameOnTable(game);
    }

    public Optional<GamesEnemies> findByGameAndEnemy(Game game, EnemyCard enemyCard){
        return gamesEnemiesRepository.findByGameAndEnemy(game, enemyCard);
    }

    public void damageEnemy(Game game, EnemyCard enemyCard, User user, Integer damage) throws Exception{
        Optional<GamesEnemies> gamesEnemiesOpt = gamesEnemiesRepository.findByGameAndEnemy(game, enemyCard);
        Optional<GameUser> gameUserOpt = gameUserService.findByGameAndUser(game, user);

        if(gamesEnemiesOpt.isPresent()){
            GamesEnemies gamesEnemies = gamesEnemiesOpt.get();
            if(gameUserOpt.isPresent()){
                GameUser gameUser = gameUserOpt.get();
                if(gamesEnemies.getEnemyState().equals(EnemyState.ONTABLE)){    
                    //SI EL ATAQUE ES VÁLIDO

                    //Se aplica el daño
                    System.out.print("ANTERIORMENTE TENÍA " + gamesEnemies.getHealth() + ". ");
                    gamesEnemies.setHealth(gamesEnemies.getHealth() - damage);
                    System.out.println("[DEBUG]: ENEMIGO DAÑADO CON " + damage + " DE DAÑO Y TIENE " + gamesEnemies.getHealth() + " DE VIDA RESTANTE");
                    if(gamesEnemies.getHealth() < 1){   //Si el enemigo muere

                        //El jugador mata al enemigo y recibe la recompensa
                        gamesEnemies.setEnemyState(EnemyState.DEAD);
                        gameUser.setGold(gameUser.getGold() + enemyCard.getExtraGold());
                        gameUser.setGlory(gameUser.getGlory() + enemyCard.getGlory() + enemyCard.getExtraGlory());
                        gameUserService.saveGameUser(gameUser);
                        System.out.println("[DEBUG]: ENEMIGO ELIMINADO. EL JUGADOR "+ user.getUsername() + " AHORA TIENE " + gameUser.getGold() + " DE ORO Y " + gameUser.getGlory() + " DE GLORIA");

                        List<GamesEnemies> enemiesOnDeck = gamesEnemiesRepository.findAllInGameOnDeck(game);

                        if(enemiesOnDeck.size() == 0){  //Si no hay más enemigos
                            game.setIsInProgress(false);
                            gameService.updateGame(game);
                        }else{
                            /*//esto no va aqui, porque esto se hace al terminar el turno
                            GamesEnemies nextEnemy = enemiesOnDeck.get(0);
                            nextEnemy.setEnemyState(EnemyState.ONTABLE);
                            createGamesEnemies(nextEnemy);
                            */
                        }
                    }
                    this.saveGamesEnemies(gamesEnemies);
                }else{  //SI EL ENEMIGO NO ESTÁ ONTABLE
                    throw new Exception("Enemy at table not found");
                }
            }else{  //SI EL USER NO ESTÁ EN GAME
                throw new Exception("Player at game not found");
            }
        }else{  //SI EL ENEMY NO ESTÁ EN GAME
            throw new Exception("Enemy at game not found");
        }
    }    

    public void saveGamesEnemies(GamesEnemies ge){
        gamesEnemiesRepository.save(ge);
    }
    
}

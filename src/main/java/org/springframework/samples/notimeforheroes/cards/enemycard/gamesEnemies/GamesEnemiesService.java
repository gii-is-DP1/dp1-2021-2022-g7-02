package org.springframework.samples.notimeforheroes.cards.enemycard.gamesEnemies;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.notimeforheroes.cards.enemycard.EnemyCard;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.stereotype.Service;

@Service
public class GamesEnemiesService {

    @Autowired
    GamesEnemiesRepository gamesEnemiesRepository;

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

    public void createGamesEnemies(GamesEnemies ge){
        gamesEnemiesRepository.save(ge);
    }
    
}

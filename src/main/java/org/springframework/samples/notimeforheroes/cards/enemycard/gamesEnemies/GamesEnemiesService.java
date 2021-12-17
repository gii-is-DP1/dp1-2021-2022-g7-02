package org.springframework.samples.notimeforheroes.cards.enemycard.gamesEnemies;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
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

    public void createGamesEnemies(GamesEnemies ge){
        gamesEnemiesRepository.save(ge);
    }
    
}

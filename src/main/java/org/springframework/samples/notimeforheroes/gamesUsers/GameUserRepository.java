package org.springframework.samples.notimeforheroes.gamesUsers;


import java.util.Collection;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.notimeforheroes.game.Game;


public interface GameUserRepository extends CrudRepository<GameUser, Integer>{

Collection<GameUser> findAll();

//@Query("SELECT user FROM games_user  WHERE ?1 = games_user.game")
//Collection<GameUser> findAllUserofGame(Game g);

}
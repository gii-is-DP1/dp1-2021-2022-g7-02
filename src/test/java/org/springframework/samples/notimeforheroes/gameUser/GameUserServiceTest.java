package org.springframework.samples.notimeforheroes.gameUser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCard;
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCardsService;
import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCard;
import org.springframework.samples.notimeforheroes.cards.marketcard.MarketCardsService;

import org.springframework.samples.notimeforheroes.cards.marketcard.gamesMarket.GameMarket;
import org.springframework.samples.notimeforheroes.cards.marketcard.gamesMarket.GameMarketService;
import org.springframework.samples.notimeforheroes.cards.scenecard.SceneCard;
import org.springframework.samples.notimeforheroes.game.Game;

import org.springframework.samples.notimeforheroes.game.GameService;
import org.springframework.samples.notimeforheroes.game.GameServiceTests;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUser;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUserService;
import org.springframework.samples.notimeforheroes.user.User;
import org.springframework.samples.notimeforheroes.user.UserService;
import org.springframework.samples.notimeforheroes.user.UsersServiceTests;
import org.springframework.samples.notimeforheroes.user.exceptions.DuplicatedUserEmailException;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class GameUserServiceTest {

    @Autowired
    GameUserService gameUserService;

    @Autowired
    UserService userService;

    @Autowired
    GameService gameService;

    @Autowired
    HeroeCardsService heroeCardsService;

    public static GameUser gameUserConstructor(HeroeCard heroeCard) {
        GameUser gameUser = new GameUser();
        gameUser.setDamageShielded(1);
        gameUser.setGlory(10);
        gameUser.setGold(7);
        gameUser.setHasEscapeToken(true);
        gameUser.setHeroe(heroeCard);
        return gameUser;
    }

    @Test
    void testFindAllGameUser() throws DataAccessException, DuplicatedUserEmailException {
        User user1 = UsersServiceTests.userConstructor("Antonio", "Paco", "pacoan", "p1a@gmail.com", "1234");

        Game game = gameConstructor(1, LocalDate.now(), 10, false);
        gameService.createGame(game);

        userService.saveUser(user1);
        List<User> lista = new ArrayList<User>();
        lista.add(user1);

        game.setUsers(lista);
        gameService.updateGame(game);

        GameUser gameUser1 = gameUserService.findByGameAndUser(game, user1).get();

        assertTrue(gameUserService.findAll().contains(gameUser1));

    }

    @Test
    void testFindByIdGameUser() throws DataAccessException, DuplicatedUserEmailException {

        User user1 = UsersServiceTests.userConstructor("Antonio", "Paco", "pacoan", "p1a@gmail.com", "1234");

        Game game = gameConstructor(1, LocalDate.now(), 10, false);
        gameService.createGame(game);

        userService.saveUser(user1);
        List<User> lista = new ArrayList<User>();
        lista.add(user1);

        game.setUsers(lista);
        gameService.updateGame(game);
        
        GameUser gameUser1 = gameUserService.findByGameAndUser(game, user1).get();


        //
        assertTrue(gameUserService.findById(gameUser1.getId()).get().equals(gameUser1));
    }

    @Test
    void testFindAllbyGame() throws DataAccessException, DuplicatedUserEmailException {
        User user1 = UsersServiceTests.userConstructor("Antonio", "Paco", "pacoan", "p1a@gmail.com", "1234");

        Game game = gameConstructor(1, LocalDate.now(), 10, false);
        gameService.createGame(game);
        
        userService.saveUser(user1);
        List<User> lista = new ArrayList<User>();
        lista.add(user1);

        game.setUsers(lista);
        gameService.updateGame(game);
        
        Collection<GameUser> gameUsersN = gameUserService.findAllByGame(game);

        ;

        //
        assertTrue(gameUsersN.contains(gameUserService.findByGameAndUser(game, user1).get()));

    }

    @Test
    void testFindByGameAndUser() throws DataAccessException, DuplicatedUserEmailException {
        User user1 = UsersServiceTests.userConstructor("Antonio", "Paco", "pacoan", "p1a@gmail.com", "1234");

        Game game = gameConstructor(1, LocalDate.now(), 10, false);
        gameService.createGame(game);

        userService.saveUser(user1);
        List<User> lista = new ArrayList<User>();
        lista.add(user1);

        game.setUsers(lista);
        gameService.updateGame(game);

        GameUser gameUser1 = gameUserService.findByGameAndUser(game, user1).get();

        assertTrue(gameUserService.findAll().contains(gameUser1));


    }

    @Test
    void testgetAllGoldByUser() throws DataAccessException, DuplicatedUserEmailException {
        User user1 = UsersServiceTests.userConstructor("Antonio", "Paco", "pacoan", "p1a@gmail.com", "1234");

        Game game = gameConstructor(1, LocalDate.now(), 10, false);
        gameService.createGame(game);

        userService.saveUser(user1);
        List<User> lista = new ArrayList<User>();
        lista.add(user1);

        game.setUsers(lista);
        gameService.updateGame(game);
        
        Integer gold=7;
        gameUserService.findByGameAndUser(game, user1).get().setGold(gold);
        gameUserService.saveGameUser(gameUserService.findByGameAndUser(game, user1).get());
        assertTrue(gameUserService.getAllGoldByUser(user1)==gold);
    }

    @Test
    void testgetAllGloryByUser() throws DataAccessException, DuplicatedUserEmailException {
        User user1 = UsersServiceTests.userConstructor("Antonio", "Paco", "pacoan", "p1a@gmail.com", "1234");

        Game game = gameConstructor(1, LocalDate.now(), 10, false);
        gameService.createGame(game);

        userService.saveUser(user1);
        List<User> lista = new ArrayList<User>();
        lista.add(user1);

        game.setUsers(lista);
        gameService.updateGame(game);
        
        Integer glory=7;
        gameUserService.findByGameAndUser(game, user1).get().setGlory(glory);
        gameUserService.saveGameUser(gameUserService.findByGameAndUser(game, user1).get());
        assertTrue(gameUserService.getAllGloryByUser(user1)==glory);
    }
    
    @Test
    void testgetHeroeFav() throws DataAccessException, DuplicatedUserEmailException {
        User user1 = UsersServiceTests.userConstructor("Antonio", "Paco", "pacoan", "p1a@gmail.com", "1234");

        Game game = gameConstructor(1, LocalDate.now(), 10, false);
        gameService.createGame(game);

        userService.saveUser(user1);
        List<User> lista = new ArrayList<User>();
        lista.add(user1);

        game.setUsers(lista);
        gameService.updateGame(game);
        
        HeroeCard heroe=heroeCardsService.findById(1).get();
        gameUserService.findByGameAndUser(game, user1).get().setHeroe(heroe);;
        gameUserService.saveGameUser(gameUserService.findByGameAndUser(game, user1).get());
        assertTrue(gameUserService.getHeroeFav(user1)==heroe.getId());
        assertFalse(gameUserService.getHeroeFav(user1)==heroe.getId()+1);
    }

    @Test
    void testgetAllUsesOfaHeroeByUser() throws DataAccessException, DuplicatedUserEmailException {
        User user1 = UsersServiceTests.userConstructor("Antonio", "Paco", "pacoan", "p1a@gmail.com", "1234");

        Game game = gameConstructor(1, LocalDate.now(), 10, false);
        gameService.createGame(game);

        Game game1 = gameConstructor(1, LocalDate.now(), 15, false);
        gameService.createGame(game1);

        userService.saveUser(user1);
        List<User> lista = new ArrayList<User>();
        lista.add(user1);

        game.setUsers(lista);
        gameService.updateGame(game);
        
        game1.setUsers(lista);
        gameService.updateGame(game1);
        
        HeroeCard heroe=heroeCardsService.findById(1).get();
        
        gameUserService.findByGameAndUser(game, user1).get().setHeroe(heroe);
        gameUserService.saveGameUser(gameUserService.findByGameAndUser(game, user1).get());
        
        gameUserService.findByGameAndUser(game1, user1).get().setHeroe(heroe);
        gameUserService.saveGameUser(gameUserService.findByGameAndUser(game1, user1).get());
        
        assertTrue(gameUserService.getAllUsesOfaHeroeByUser(user1, 1)==2);
    }
    
    public Game gameConstructor(int creatorId, LocalDate date, int duration, boolean isInProgress) {
        Game g = new Game();
        g.setCreator(userService.findById(creatorId).get());
        g.setDate(date);
        g.setDuration(duration);
        g.setIsInProgress(isInProgress);

        return g;
    }
    






}

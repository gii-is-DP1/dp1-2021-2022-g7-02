package org.springframework.samples.notimeforheroes.gameUser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    void testFindAllGameUser() {

        GameUser gameUser = new GameUser();
        gameUser.setDamageShielded(1);
        gameUser.setHeroe(heroeCardsService.findByName("Feldon"));
        gameUserService.saveGameUser(gameUser);
        Collection<GameUser> gameUsersN = gameUserService.findAll();

        //
        assertTrue(gameUsersN.contains(gameUser));

    }

    @Test
    void testFindByIdGameUser() {
        GameUser gameUser = new GameUser();
        gameUserService.saveGameUser(gameUser);

        //
        assertTrue(gameUserService.findById(gameUser.getId()).orElse(null).equals(gameUser));
    }

    @Test
    void testFindAllbyGame() {

        Game game = gameConstructor(1, LocalDate.now(), 10, false);
        gameService.createGame(game);

        Collection<GameUser> gameUsersN = gameUserService.findAllByGame(game);

        Collection<GameUser> gameUsersN1 = gameUserService.findAll();

        //
        assertTrue(gameUsersN.containsAll(gameUsersN1));

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

        Collection<Game> gameByUser = gameService.findByUser(user1);

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

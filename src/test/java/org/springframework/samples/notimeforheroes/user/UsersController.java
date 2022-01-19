package org.springframework.samples.notimeforheroes.user;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCardsService;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.game.GameService;
import org.springframework.samples.notimeforheroes.game.GameServiceTests;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUser;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUserService;
import org.springframework.samples.notimeforheroes.user.exceptions.DuplicatedUserEmailException;
import org.springframework.scheduling.quartz.LocalDataSourceJobStore;
import org.springframework.stereotype.Service;

import net.bytebuddy.asm.Advice.Local;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private GameService gameService;

    @Autowired
    private GameUserService gameUserService;

    @Autowired
    private HeroeCardsService heroeCardsService;

}

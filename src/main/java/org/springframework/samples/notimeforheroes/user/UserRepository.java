package org.springframework.samples.notimeforheroes.user;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUser;

public interface UserRepository extends CrudRepository<User, Integer>{

	Page<User> findAll(Pageable page);

	
	Collection<User> findAll();

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	@Query(nativeQuery = true, value = "SELECT u.* FROM Users u JOIN games_users gu WHERE gu.fk_user = u.id AND gu.fk_game = ?1 AND gu.heroe_id IS NOT NULL")
    Collection<User> findAllInGameWithHeroeSelected(Game game);

	@Query(nativeQuery = true, value = "SELECT u.* FROM Games_Users gu JOIN Users u WHERE gu.fk_user= u.id AND gu.id = ?1")
    Optional<User> findByGameUser(GameUser gameUser);
}

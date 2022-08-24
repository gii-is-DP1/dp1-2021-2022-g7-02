package org.springframework.samples.notimeforheroes.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCard;
import org.springframework.samples.notimeforheroes.cards.heroecard.HeroeCardsService;
import org.springframework.samples.notimeforheroes.game.Game;
import org.springframework.samples.notimeforheroes.game.GameService;
import org.springframework.samples.notimeforheroes.game.gamesUsers.GameUserService;
import org.springframework.samples.notimeforheroes.user.exceptions.DuplicatedUserEmailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

	public static final String USER_LISTING = "users/userListing";
	public static final String USER_FORM = "users/createOrUpdateUserForm";
	public static final String USER_FORM_NEW = "users/createUserForm";
	public static final String USER_DETAILS = "users/userDetails";
	public static final String USER_PROFILE = "users/userProfile";
	public static final String USER_GAME_STATS_DURATION = "users/userGameStatsDuration";
	public static final String USER_GAME_STATS = "users/userGameStats";

	@Autowired
	GameService gameService;

	@Autowired
	UserService userService;

	@Autowired
	GameUserService gameUserService;

	@Autowired
	HeroeCardsService heroeCardService;

	@GetMapping("/{pageNo}")
	public String getAll(ModelMap model, @PathVariable("pageNo") Integer pageNo) {
		Integer lastPage = userService.findAll().size() / 4;
		model.addAttribute("lastPag", lastPage);
		Collection<User> lista = userService.findAllPage(pageNo, 4);
		model.addAttribute("user", lista);
		model.addAttribute("pag", pageNo);
		return USER_LISTING;
	}

	@GetMapping
	public String listUsers(ModelMap model) {
		model.addAttribute("user", userService.findAll());
		return USER_LISTING;
	}

	@GetMapping("/profile/gameStats")
	public String GameStats(ModelMap model) {
		User user = userService.getLoggedUser();
		Integer heroeFav = gameUserService.getHeroeFav(user);
		if (heroeFav == null) {
			model.addAttribute("heroe", null);
		} else {
			Optional<HeroeCard> heroe = heroeCardService.findById(heroeFav);
			model.addAttribute("heroe", heroe.get());

		}
		model.addAttribute("AllGold", gameUserService.getAllGoldByUser(user));
		model.addAttribute("AllGlory", gameUserService.getAllGloryByUser(user));

		return USER_GAME_STATS;
	}

	@GetMapping("/profile/gameDuration")
	public String GameStatsDuration(ModelMap model) {
		Collection<Game> games = gameService.findAllEnded();

		Collection<Integer> durations = new ArrayList<Integer>();
		for (Game g : games) {
			if (g.getDuration() == null) {

			} else {
				durations.add(g.getDuration());
			}
		}
		Integer MinDurations = Collections.min(durations);
		Integer MaxDurations = Collections.max(durations);
		Double averageDuration = durations.stream().mapToDouble(a -> a).average().getAsDouble();
		model.addAttribute("minDuration", MinDurations);
		model.addAttribute("maxDuration", MaxDurations);
		model.addAttribute("averageDuration", Math.round(averageDuration));
		model.addAttribute("games", gameService.findAllEnded());

		return USER_GAME_STATS_DURATION;
	}

	@GetMapping("/profile")
	public String PlayerProfile(ModelMap model) {
		User user = userService.getLoggedUser();
		model.addAttribute("user", user);
		model.addAttribute("games", gameService.findByUser(user));
		Map<User, Integer> players = userService.getListOfOpponents(user);
		model.addAttribute("players", players);
		Integer playsInWeek = gameService.findBetweenDates(user, LocalDate.now().minusDays(7), LocalDate.now());
		Integer playsInMonth = gameService.findBetweenDates(user, LocalDate.now().minusDays(30), LocalDate.now());
		model.addAttribute("Week", playsInWeek);
		model.addAttribute("Month", playsInMonth);
		return USER_PROFILE;
	}

	@GetMapping("/profile/edit")
	public String profileEdit(ModelMap model) {
		model.addAttribute("user", userService.getLoggedUser());
		return USER_FORM;
	}

	@PostMapping("/profile/edit")
	public String profileEdit(RedirectAttributes redirect, ModelMap model, @Valid User modifiedUser,
			BindingResult result) {

		User loggedUser = userService.getLoggedUser();
		
		if (result.hasErrors()) {
			model.addAttribute("message", "The user has errors");
			return USER_FORM;
		} else {
			
			BeanUtils.copyProperties(modifiedUser, loggedUser, "id");
			model.addAttribute("user", loggedUser);
			listUsers(model);
			redirect.addFlashAttribute("message", "User modified");
			return "redirect:/users/profile";
		}
	}

	@GetMapping("/{id}/edit")
	public String editUser(ModelMap model, @PathVariable("id") int id) {
		Optional<User> user = userService.findById(id);
		if (user.isPresent()) {
			if (userService.getLoggedUser().getId().equals(id) || userService.getLoggedUser().isAdmin()) {
				model.addAttribute("user", user.get());
				return USER_FORM;
			} else {
				model.addAttribute("message", "You can't edit this user");
				return listUsers(model);
			}

		} else {
			model.addAttribute("message", "This user doesn't exist");
			return listUsers(model);
		}
	}

	@PostMapping("/{id}/edit")
	public String editUser(RedirectAttributes redirect, ModelMap model, @PathVariable("id") int id,
			@Valid User modifiedUser, BindingResult result) {
		Optional<User> user = userService.findById(id);
		if (result.hasErrors()) {
			model.addAttribute("message", "The user has errors");
			return USER_FORM;
		} else {
			int userId = userService.getLoggedUser().getId();

			if (user.get().getId() == userId) {
				BeanUtils.copyProperties(modifiedUser, user.get(), "id");
				model.addAttribute("user", user.get());
				listUsers(model);
				redirect.addFlashAttribute("message", "User modified");
				return "redirect:/users/{id}/details";
			} else {
				redirect.addFlashAttribute("message", "You cannot modify this user");
				return "redirect:/users/0";
			}

		}
	}

	@GetMapping("/{id}/details")
	public String PlayerDetails(ModelMap model, @PathVariable("id") int id) {
		Optional<User> user = userService.findById(id);
		if (user.isPresent()) {
			model.addAttribute("user", user.get());
			return USER_DETAILS;
		} else {
			model.addAttribute("message", "This user doesn't exits");
			return listUsers(model);
		}
	}

	@GetMapping("/new")
	public String newUser(Map<String, Object> map) {
		User user = new User();
		map.put("user", user);
		return USER_FORM_NEW;
	}

	@PostMapping("/new")
	public String newUser(@Valid User user, BindingResult result, ModelMap model, RedirectAttributes redirect)
			throws DataAccessException, DuplicatedUserEmailException {
		if (result.hasErrors()) {
			return USER_FORM_NEW;
		} else {
			userService.saveUser(user);
			redirect.addFlashAttribute("message", "User created");
			return "redirect:/";
		}
	}

	@GetMapping("/{id}/delete")
	public String deleteUser(RedirectAttributes redirect, ModelMap model, @PathVariable("id") int id) {
		Optional<User> user = userService.findById(id);
		userService.deleteUser(user.get());
		listUsers(model);
		redirect.addFlashAttribute("message", "User deleted");
		return "redirect:/users/0";

	}

}

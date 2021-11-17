package org.springframework.samples.notimeforheroes.user;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.notimeforheroes.user.exceptions.DuplicatedUserEmailException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

	public static final String USER_LISTING = "users/userListing";
	public static final String USER_FORM =  "users/createOrUpdateUserForm";
	public static final String USER_DETAILS =  "users/userDetails";
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public String listUsers(ModelMap model) {
		model.addAttribute("users", userService.findAll());
		return USER_LISTING;
	}
	
	@GetMapping("/{id}/details")
	public String PlayerDetails(ModelMap model, @PathVariable("id") int id) {
		Optional<User> user = userService.findById(id);
		if(user.isPresent()) {
			model.addAttribute("user", user.get());
			return USER_DETAILS;
		} else {
			model.addAttribute("message", "This user doesn't exits");
			return listUsers(model);
		}
	}
	
	@GetMapping("/{id}/edit")
	public String editUser(ModelMap model, @PathVariable("id") int id) {
		Optional<User> user = userService.findById(id);
		if(user.isPresent()) {
			model.addAttribute("users", user.get());
			return USER_FORM;
		} else {
			model.addAttribute("message", "This user doesn't exist");
			return listUsers(model);
		}
	}
	
	@PostMapping("/{id}/edit")
	public String editUser(ModelMap model, @PathVariable("id") int id, @Valid User modifiedUser, BindingResult result) {
		Optional<User> user = userService.findById(id);
		if(result.hasErrors()) {
			model.addAttribute("message", "The user has errors");
			return USER_FORM;
		} else {
			BeanUtils.copyProperties(modifiedUser, user.get(), "id");
			model.addAttribute("users", user.get());
			listUsers(model);
			return "redirect:/users";
		}
	}
	
	
	@GetMapping("/new")
	public String newUser(Map<String, Object> map) {
		User user = new User();
		map.put("users", user);
		return USER_FORM;
	}
	
	@PostMapping("/new")
	public String newUser(@Valid User user,BindingResult result, ModelMap model) throws DataAccessException, DuplicatedUserEmailException {
		if(result.hasErrors()) {
			return USER_FORM;
		} else {
			userService.saveUser(user);
			model.addAttribute("message", "User created");
			return "redirect:/users";
		}
	}
	
	@GetMapping("/{id}/delete")
	public String deleteUser(ModelMap model, @PathVariable("id") int id) {
		Optional<User> user = userService.findById(id);
		userService.deleteUser(user.get());
		model.addAttribute("message", "User Deleted");
		listUsers(model);
		return "redirect:/users";

	}
	
	
}

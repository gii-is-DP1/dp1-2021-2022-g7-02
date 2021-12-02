package org.springframework.samples.notimeforheroes.user;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
	public static final String USER_FORM =  "users/createOrUpdateUserForm";
	public static final String USER_DETAILS =  "users/userDetails";
	public static final String USER_PROFILE =  "users/userProfile";
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public String listUsers(ModelMap model) {
		model.addAttribute("user", userService.findAll());
		return USER_LISTING;
	}
	
	@GetMapping("/profile")
	public String PlayerProfile(ModelMap model) {
		User user = userService.getLoggedUser();
			model.addAttribute("user", user);
			return USER_PROFILE;
	}

	@GetMapping("/profile/edit")
	public String profileEdit(ModelMap model) {
		model.addAttribute("user", userService.getLoggedUser());
		return USER_FORM;
	}

	@PostMapping("/profile/edit")
	public String profileEdit(RedirectAttributes redirect,ModelMap model, @Valid User modifiedUser, BindingResult result){
		
		User loggedUser = userService.getLoggedUser();
		
		if(result.hasErrors()){
			model.addAttribute("message", "The user has errors");
			return USER_FORM;
		}else{
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
		if(user.isPresent()) {
			if(userService.getLoggedUser().getId().equals(id) || userService.getLoggedUser().isAdmin()){
				model.addAttribute("user", user.get());
				return USER_FORM;
			}else {
				model.addAttribute("message", "You can't edit this user");
				return listUsers(model);
			}
			
		} else {
			model.addAttribute("message", "This user doesn't exist");
			return listUsers(model);
		}
	}
	
	@PostMapping("/{id}/edit")
	public String editUser(RedirectAttributes redirect,ModelMap model, @PathVariable("id") int id, @Valid User modifiedUser, BindingResult result) {
		Optional<User> user = userService.findById(id);
		if(result.hasErrors()) {
			model.addAttribute("message", "The user has errors");
			return USER_FORM;
		} else {
			int userId = userService.getLoggedUser().getId();

			if(user.get().getId()==userId) {
				BeanUtils.copyProperties(modifiedUser, user.get(), "id");
				model.addAttribute("user", user.get());
				listUsers(model);
				redirect.addFlashAttribute("message", "User modified");
				return "redirect:/users/{id}/details";
			}
			else {
				redirect.addFlashAttribute("message", "You cannot modify this user");
				return "redirect:/users";
			}

		}
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
	
	
	@GetMapping("/new")
	public String newUser(Map<String, Object> map) {
		User user = new User();
		map.put("user", user);
		return USER_FORM;
	}
	
	@PostMapping("/new")
	public String newUser(@Valid User user,BindingResult result, ModelMap model,RedirectAttributes redirect) throws DataAccessException, DuplicatedUserEmailException {
		if(result.hasErrors()) {
			return USER_FORM;
		} else {
			userService.saveUser(user);
			redirect.addFlashAttribute("message", "User created");
			return "redirect:/users";
		}
	}
	
	@GetMapping("/{id}/delete")
	public String deleteUser(RedirectAttributes redirect, ModelMap model, @PathVariable("id") int id) {
		Optional<User> user = userService.findById(id);
		userService.deleteUser(user.get());
		listUsers(model);
		redirect.addFlashAttribute("message", "User deleted");
		return "redirect:/users";

	}
	
	
}

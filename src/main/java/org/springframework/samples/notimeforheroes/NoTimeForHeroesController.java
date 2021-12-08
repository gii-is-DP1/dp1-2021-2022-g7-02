
package org.springframework.samples.notimeforheroes;

import org.springframework.samples.petclinic.web.WelcomeController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class NoTimeForHeroesController {
    
    private static final String WELCOME = "welcome";


    @GetMapping("/")
	public String welcome(ModelMap model) {
		return WELCOME;
	}

	@GetMapping("/error")
	public String error(ModelMap model){
		model.addAttribute("message", "Ha ocurrido un error");
		return WELCOME;
	}

}
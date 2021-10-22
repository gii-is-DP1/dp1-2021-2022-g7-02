package org.springframework.samples.notimeforheroes;


import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;




    @Controller
    @RequestMapping("/CartaLocalizacion")
    public class CartaLocalizacionController {


        private final CartaLocalizacionService CartaLocalizacionService;

        @Autowired
        public CartaLocalizacionController(CartaLocalizacionService CartaLocalizacionService, UserService userService, AuthoritiesService authoritiesService) {
            this.CartaLocalizacionService = CartaLocalizacionService;
        }

        @GetMapping()
        public String showCartaLocalizacionList(ModelMap modelMap) {
            Collection<CartaLocalizacion> Cartalocalizacion = CartaLocalizacionService.findAll();
            modelMap.addAttribute("Carta Localizacion", Cartalocalizacion);

            return "CartaLocalizacion/CartaLocalizacionListing";
        }


    }

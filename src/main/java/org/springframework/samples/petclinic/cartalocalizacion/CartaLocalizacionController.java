package org.springframework.samples.petclinic.cartalocalizacion;


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
    @RequestMapping("/cartasLocalizacion")
    public class CartaLocalizacionController {


        private final CartaLocalizacionService cartaLocalizacionService;

        @Autowired
        public CartaLocalizacionController(CartaLocalizacionService CartaLocalizacionService, UserService userService, AuthoritiesService authoritiesService) {
            this.cartaLocalizacionService = CartaLocalizacionService;
        }

        @GetMapping()
        public String showCartaLocalizacionList(ModelMap modelMap) {
            Collection<CartaLocalizacion> cartasLocalizacion = cartaLocalizacionService.findAll();
            modelMap.addAttribute("Carta Localizacion", cartasLocalizacion);

            return "cartasLocalizacion/CartaLocalizacionListing";
        }


    }
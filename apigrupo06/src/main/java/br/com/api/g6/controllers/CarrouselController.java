package br.com.api.g6.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
@RestController
public class CarrouselController {

   @RequestMapping(value = { "/carrousel-promocional" }, method = RequestMethod.GET)
   public ModelAndView index() {
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("index");
      return modelAndView;
   }
}
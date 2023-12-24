package com.project.artisan.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.artisan.model.*;
import com.project.artisan.service.*;

@Controller
public class FavoriteController {
	@Autowired
	  private FavorisService favoriteService;
	  @Autowired
	  private UserService userService;
	  
	  
	@GetMapping("/favoris")
	  public String showCart(Model model, Principal principal) {
		
	    User user = userService.findByEmail(principal.getName());
	    List<Favoris> favorites= favoriteService.listFavoris(user);
	    model.addAttribute("favoris", favorites);
	    model.addAttribute("user", user);
	    return "user/favoris";
	  }
	@GetMapping("/favoris/delete/{pid}")
	  public String removeFavorite(@PathVariable("pid") Long productId,
	                          Principal principal) {
	    User user = userService.findByEmail(principal.getName());
	    favoriteService.removeProductF(productId, user);
	    return "redirect:/favoris";
	  } 
}

package com.project.artisan.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.artisan.model.*;
import com.project.artisan.service.*;

@RestController
public class FavorisController {
	  @Autowired
	  private FavorisService favoriteService;
	  @Autowired
	  private UserService userService;
	  List<Long> ids= new ArrayList<Long>();
	  @PostMapping("/Favoris{pid}")
	  public String addToFavoris(Model model,@PathVariable Long pid, Principal principal) {
		  User user = userService.findByEmail(principal.getName());
			model.addAttribute("user", user);
			List<Favoris> favoris = favoriteService.listFavoris(user);
			for(var i=0; i<favoris.size();i++) {
				ids.add(favoris.get(i).getArticle().getId());
		    }	
			if(!ids.contains(pid)) {
				favoriteService.addToFavorites(pid, user);
			}
			return "success";
	  }
}

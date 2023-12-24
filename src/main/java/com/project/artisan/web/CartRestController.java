package com.project.artisan.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.artisan.model.Cart;
import com.project.artisan.model.User;
import com.project.artisan.service.CartService;
import com.project.artisan.service.UserService;

@RestController
public class CartRestController {
	@Autowired
	private CartService cartService;
	@Autowired
	private UserService userService;

	@PostMapping("/update/{pid}/{qty}")
	  public String update(@PathVariable("pid") Long productId,
	                          @PathVariable("qty") int quantity,
	                          Principal principal) {
	    User user = userService.findByEmail(principal.getName());
	    
		double sub=cartService.updateQty(productId, quantity, user);
		double subTotal=Math.round(sub * 100.0) / 100.0;
	    return String.valueOf(subTotal );
	  } 
	
	
}

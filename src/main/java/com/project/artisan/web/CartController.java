package com.project.artisan.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.project.artisan.model.*;
import com.project.artisan.repository.CartRepository;
import com.project.artisan.service.*;
import org.springframework.ui.Model;


@Controller
public class CartController {
	@Autowired
	private CartService cartService;
	@Autowired
	private UserService userService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CartRepository cartRepo;
	List<Long> ids= new ArrayList<Long>();
	
	@GetMapping("/cart")
	  public String showCart(Model model, Principal principal) {
		double total=0.0;
		double somme=0;
		
	    User user = userService.findByEmail(principal.getName());
	    model.addAttribute("user", user);
	    List<Cart> cartItems = cartService.listCart(user);
	    model.addAttribute("carts", cartItems);
	    for(var i=0; i<cartItems.size();i++) {
			total+=cartItems.get(i).getProduct().getPrix() * cartItems.get(i).getQuatite();
	    }
	    somme=total+69;
	    
	    model.addAttribute("total", Math.round(total*100.0)/100.0);
	    model.addAttribute("somme", somme);
	    model.addAttribute("user", user);
	    return "user/cart";
	  }
	  @PostMapping("/add-to-cart")
	  public String addToCart(@RequestParam("id") Long productId,
	                          @RequestParam("q") int quantity,
	                          Principal principal) {
	    User user = userService.findByEmail(principal.getName());
	    Optional<Article> optionalArticle=articleService.get(productId);
		Article product=optionalArticle.get();
		List<Cart> cartItems = cartService.listCart(user);
		for(var i=0; i<cartItems.size();i++) {
			ids.add(cartItems.get(i).getProduct().getId());
	    }	
		if(!ids.contains(productId)) {
	    Cart cartItem = new Cart(product, user, quantity);
	    cartRepo.save(cartItem);
		}
		
	    return "redirect:/cart";
	  } 
	  @GetMapping("/delete/{pid}")
	  public String remove(@PathVariable("pid") Long productId,
	                          Principal principal) {
	    User user = userService.findByEmail(principal.getName());
	    cartService.removeProduct(productId, user);
	    return "redirect:/cart";
	  }   
	  
}

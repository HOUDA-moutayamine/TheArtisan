package com.project.artisan.web;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.artisan.model.*;
import com.project.artisan.service.*;

@Controller
@RequestMapping("")
public class UserController {
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userServise;
	@GetMapping("/chatwith{to}")
	public String comment(Model model,@PathVariable String to ,Principal principal) {
		User user = userServise.findByEmail(principal.getName());
		model.addAttribute("user", user);
		model.addAttribute("users", userServise.findAll());
		model.addAttribute("to", to);
		return "chat";
	}
	
	@GetMapping("/")
	public String accueil(Model model,Principal principal) {
		User user = userServise.findByEmail(principal.getName());
		model.addAttribute("user", user);
		return "user/index";
	}
	@GetMapping("/login")
	public String login() {
			return "user/login"; 
	}
	@GetMapping("/logout")
	public String logout() {
			return "redirect:/"; 
	}
	@GetMapping("/decouvrir")
	public String decouvrir(Model model,Principal principal) {
		User user = userServise.findByEmail(principal.getName());
		model.addAttribute("user", user);
		List<Article> article= articleService.findAll();
		model.addAttribute("Articles", article);
		return"user/decouvrir";
	}
	@GetMapping("/decouvrirDetails{id}")
	public String datails(@PathVariable Long id, Model model,Principal principal) {
		Article p=new Article();
		Optional<Article> optionalArticle=articleService.get(id);
		p=optionalArticle.get();
		User user = userServise.findByEmail(principal.getName());
		model.addAttribute("user", user);
		model.addAttribute("users", userServise.findAll());
		model.addAttribute("Article", p);
		model.addAttribute("currentUser", user);
		return"user/details";
	}
	@GetMapping("/contact")
	public String contact(Model model,Principal principal) {
		User user = userServise.findByEmail(principal.getName());
		model.addAttribute("user", user);
		return "user/contact";
	}
}

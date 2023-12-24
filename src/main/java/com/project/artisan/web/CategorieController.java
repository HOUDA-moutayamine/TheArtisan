package com.project.artisan.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.artisan.model.Article;
import com.project.artisan.model.User;
import com.project.artisan.service.ArticleService;
import com.project.artisan.service.UserService;

@Controller
@RequestMapping("/")
public class CategorieController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userServise;
	
	@GetMapping("Categories")
	public String index(Model model,Principal principal) {
		User user = userServise.findByEmail(principal.getName());
		model.addAttribute("user", user);
		return "produits";
	}
	@GetMapping("mobilier")
	public String mobilier(Model model,Principal principal) {
		User user = userServise.findByEmail(principal.getName());
		model.addAttribute("user", user);
		List<Article> article= articleService.findAll();
		model.addAttribute("Articles", article);
		return "mobilier";
	}
	@GetMapping("poterie")
	public String poterie(Model model,Principal principal) {
		User user = userServise.findByEmail(principal.getName());
		model.addAttribute("user", user);
		List<Article> article= articleService.findAll();
		model.addAttribute("Articles", article);
		return "poterie";
	}
	@GetMapping("tapis")
	public String tapis(Model model,Principal principal) {
		User user = userServise.findByEmail(principal.getName());
		model.addAttribute("user", user);
		List<Article> article= articleService.findAll();
		model.addAttribute("Articles", article);
		return "user/tapis";
	}
	@GetMapping("decoration")
	public String decoration(Model model,Principal principal) {
		User user = userServise.findByEmail(principal.getName());
		model.addAttribute("user", user);
		List<Article> article= articleService.findAll();
		model.addAttribute("Articles", article);
		return "decoration";
	}

}

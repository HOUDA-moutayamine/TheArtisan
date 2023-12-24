package com.project.artisan.web;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.artisan.model.Categorie;
import com.project.artisan.service.CategorieService;
import com.project.artisan.service.UploadFile;
import com.project.artisan.service.UserService;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	private final Logger LOGGER= LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	CategorieService categorieService;
	@Autowired
	private UserService userServise;
	@Autowired
	private UploadFile upload;
	
	@GetMapping("")
	public String product() {
		return "admin/home";
	}
	
	@GetMapping("/customers")
	public String users(Model model) {
		model.addAttribute("users", userServise.findAll());
		return "admin/customers";
	}
	
	@GetMapping("/categorie")
	public String categorie(Model model) {
		model.addAttribute("categories", categorieService.findAll());
		return "admin/categorie";
	}
	@GetMapping("/createCategorie")
	public String creatC() {
		return "admin/createCategorie";
	}
	@PostMapping("/save")
	public String save(Categorie c,  @RequestParam("imge") MultipartFile file) throws IOException {
		
			String nameImage=upload.saveImage(file);
			c.setImg(nameImage);
		
		LOGGER.info("categorie {}",c);
		categorieService.save(c);
		return "redirect:/admin/categorie";
	}
	@GetMapping("/Categorie/edit/{id}")
	public String editCategorie(@PathVariable Integer id, Model model) {
		Categorie c=new Categorie();
		Optional<Categorie> optionalCat=categorieService.get(id);
		c=optionalCat.get();
		model.addAttribute("categorie", c);
		LOGGER.info("{}",c);
		return "admin/editCategorie";
	}
	@PostMapping("/Categorie/update")
	public String update(Categorie c,@RequestParam("imge") MultipartFile file) {
		Categorie cat =new Categorie();
		cat=categorieService.get(c.getId()).get();
		if(file.isEmpty()) {
			c.setImg(cat.getImg());
		}else {
			if(!cat.getImg().equals("default.jpeg")) {
				upload.deleteImage(cat.getImg());
			}}
		categorieService.update(c);
		return "redirect:/admin/categorie";
	}
	@GetMapping("/Categorie/delete/{id}")
	public String delete(@PathVariable Integer id) {
		Categorie cat =new Categorie();
		cat=categorieService.get(id).get();
		if(!cat.getImg().equals("default.jpeg")) {
			upload.deleteImage(cat.getImg());
		}
		categorieService.delete(id);
		return "redirect:/admin/categorie";
	}
}

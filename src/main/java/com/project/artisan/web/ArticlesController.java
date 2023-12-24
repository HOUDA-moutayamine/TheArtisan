package com.project.artisan.web;

import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.project.artisan.model.Article;
import com.project.artisan.service.ArticleService;
import com.project.artisan.service.CategorieService;
import com.project.artisan.service.UploadFile;

@Controller
@RequestMapping(value="/admin/articles")
public class ArticlesController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	CategorieService categorieService;
	@Autowired
	private UploadFile upload;
	@GetMapping("")
	public String article(Model model) {
		model.addAttribute("articles", articleService.findAll());
		model.addAttribute("categories", categorieService.findAll());
		return "admin/articles/articles";
	}
	@GetMapping("/createArticle")
	public String creatC(Model model) {
		model.addAttribute("categories", categorieService.findAll());
		return "admin/articles/createArticle";
	}
	@PostMapping("/save")
	public String save(Article p, @RequestParam("img") MultipartFile file,
			@RequestParam("imge") MultipartFile file1,
			@RequestParam("imge1") MultipartFile file2,
			@RequestParam("imge2") MultipartFile file3,
			@RequestParam("imge3") MultipartFile file4) throws IOException {
		if(p.getId()== (Long)null) {
			String nameImage=upload.saveImage(file);
			p.setPhoto(nameImage);
			String nameImage1=upload.saveImage(file1);
			p.setImage(nameImage1);
			String nameImage2=upload.saveImage(file2);
			p.setImage1(nameImage2);
			String nameImage3=upload.saveImage(file3);
			p.setImage2(nameImage3);
			String nameImage4=upload.saveImage(file4);
			p.setImage3(nameImage4);
		}else {
		} 
		articleService.save(p);
		return "redirect:/admin/articles";
	}
	@GetMapping("/edit/{id}")
	public String editCategorie(@PathVariable Long id, Model model) {
		Article p=new Article();
		Optional<Article> optionalArticle=articleService.get(id);
		p=optionalArticle.get();
		model.addAttribute("article", p);
		model.addAttribute("categories", categorieService.findAll());
		return "admin/articles/editArticle";
	}
	@PostMapping("/update")
	public String update(Article p,@RequestParam("img") MultipartFile file,
			@RequestParam("imge") MultipartFile file1,
			@RequestParam("imge1") MultipartFile file2,
			@RequestParam("imge2") MultipartFile file3,
			@RequestParam("imge3") MultipartFile file4) throws IOException{
		Article a= new Article();
		a=articleService.get(p.getId()).get();
		if(file.isEmpty()) {
			p.setPhoto(a.getPhoto());
		}else {
			if(!a.getPhoto().equals("default.jpeg")) {
				upload.deleteImage(a.getPhoto());
			}}
		if(file1.isEmpty()) {
			p.setImage(a.getImage());
		}else {
			if(!a.getImage().equals("default.jpeg")) {
				upload.deleteImage(a.getImage());
			}}
		if(file2.isEmpty()) {
			p.setImage1(a.getImage1());
		}else {
			if(!a.getImage1().equals("default.jpeg")) {
				upload.deleteImage(a.getImage1());
			}}
		if(file3.isEmpty()) {
			p.setImage2(a.getImage2());
		}else {
			if(!a.getImage2().equals("default.jpeg")) {
				upload.deleteImage(a.getImage2());
			}}
		if(file4.isEmpty()) {
			p.setImage3(a.getImage3());
		}else {
			if(!a.getImage3().equals("default.jpeg")) {
				upload.deleteImage(a.getImage3());
			}}
		articleService.update(p);
		return "redirect:/admin/articles";
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		Article p= new Article();
		p=articleService.get(id).get();
		if(!p.getPhoto().equals("default.jpeg")) {
			upload.deleteImage(p.getPhoto());
		}
		if(!p.getImage().equals("default.jpeg")) {
			upload.deleteImage(p.getImage());
		}
		if(!p.getImage1().equals("default.jpeg")) {
			upload.deleteImage(p.getImage1());
		}
		if(!p.getImage2().equals("default.jpeg")) {
			upload.deleteImage(p.getImage2());
		}
		if(!p.getImage3().equals("default.jpeg")) {
			upload.deleteImage(p.getImage3());
		}
		articleService.delete(id);
		return "redirect:/admin/articles";
	}
	
	

}

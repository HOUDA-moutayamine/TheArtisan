package com.project.artisan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.artisan.model.*;
import com.project.artisan.repository.ArticleRepository;
import com.project.artisan.repository.FavorisRepository;

@Service
@Transactional
public class FavorisService {
	 @Autowired
	  private FavorisRepository favoriteRepository;
	 @Autowired
	private ArticleRepository articleRepo;
	 
	  public void addToFavorites(Long pid, User user) {
	    Article p= articleRepo.findById(pid).get();
	    Favoris favorite = new Favoris();
	    favorite.setArticle(p);
	    favorite.setUser(user);
	    favoriteRepository.save(favorite);
	  }

	  public void removeProductF(Long pid, User user) {
		  favoriteRepository.deleteByUserAndProduct(user.getId(),pid);
		}

	  public List<Favoris> listFavoris(User user){
			return favoriteRepository.findByUser(user);
		}
}

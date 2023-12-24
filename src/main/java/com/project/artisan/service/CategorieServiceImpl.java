package com.project.artisan.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.artisan.model.Categorie;
import com.project.artisan.repository.ProductRepository;
@Service
public class CategorieServiceImpl implements CategorieService{
	
	@Autowired
	private ProductRepository catRepo;
	@Override
	public Categorie save(Categorie c) {
		return catRepo.save(c);
	}

	@Override
	public Optional<Categorie> get(Integer code) {
		return catRepo.findById(code);
	}

	@Override
	public void update(Categorie c) {
		catRepo.save(c);		
	}

	@Override
	public void delete(Integer code) {
		catRepo.deleteById(code);
	}

	@Override
	public List<Categorie> findAll() {
		return catRepo.findAll();
	}

}

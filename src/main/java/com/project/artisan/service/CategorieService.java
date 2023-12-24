package com.project.artisan.service;

import java.util.List;
import java.util.Optional;

import com.project.artisan.model.Categorie;

public interface CategorieService {
	public Categorie save(Categorie c);
	public Optional<Categorie> get(Integer code);
	public void update(Categorie c);
	public void delete(Integer code);
	public List<Categorie> findAll();
}

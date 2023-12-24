package com.project.artisan.service;
import java.util.List;
import java.util.Optional;

import com.project.artisan.model.Article;
public interface ArticleService {
	public Article save(Article p);
	public Optional<Article> get(Long code);
	public void update(Article c);
	public void delete(Long code);
	public List<Article> findAll();
}

package com.project.artisan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.project.artisan.model.Article;
import com.project.artisan.repository.ArticleRepository;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleRepository ArticleRepo;
	@Override
	public Article save(Article p) {
		return ArticleRepo.save(p);
	}

	@Override
	public Optional<Article> get(Long code) {
		return ArticleRepo.findById(code);
	}

	@Override
	public void update(Article p) {
		ArticleRepo.save(p);
	}

	@Override
	public void delete(Long code) { 
		ArticleRepo.deleteById(code);
	}

	@Override
	public List<Article> findAll() {
		return ArticleRepo.findAll();
	}
	
	public Page<Article> findProductsWithPagination(int offset,int pageSize){
        Page<Article> products = ArticleRepo.findAll(PageRequest.of(offset, pageSize));
        return  products;
    }

    public Page<Article> findProductsWithPaginationAndSorting(int offset,int pageSize,String field){
        Page<Article> products = ArticleRepo.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return  products;
    }


}

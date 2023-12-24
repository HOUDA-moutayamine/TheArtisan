package com.project.artisan.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.artisan.model.*;
@Repository
public interface ArticleRepository extends JpaRepository<Article,Long>{
}

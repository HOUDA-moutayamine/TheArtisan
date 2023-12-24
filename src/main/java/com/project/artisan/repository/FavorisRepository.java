package com.project.artisan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.artisan.model.*;
@Repository
public interface FavorisRepository extends JpaRepository<Favoris,Integer>{
	List<Favoris> findByUser(User user);
	@Query("delete From Favoris f where f.user.id=?1 and f.article.id=?2")
	@Modifying
	public void deleteByUserAndProduct(Long userId, Long productId );
}

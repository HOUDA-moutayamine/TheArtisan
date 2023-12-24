package com.project.artisan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.artisan.model.Cart;
import com.project.artisan.model.User;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long>{
	public List<Cart> findByUser(User user);
	@Query("update Cart c SET c.quatite=?1 where c.product.id=?2 and c.user.id=?3")
	@Modifying
    public void updateQuantity(int quantity, Long pid, Long userId);
	
	@Query("delete From Cart c where c.user.id=?1 and c.product.id=?2")
	@Modifying
	public void deleteByUserAndProduct(Long userId, Long productId ); 
}

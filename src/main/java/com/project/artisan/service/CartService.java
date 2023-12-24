package com.project.artisan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.artisan.model.*;
import com.project.artisan.repository.*;

@Service
@Transactional
public class CartService {
	
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private ArticleRepository articleRepo;
	
	public List<Cart> listCart(User user){
		return cartRepo.findByUser(user);
	}
	
	public double updateQty(Long pid, int q, User user) {
		cartRepo.updateQuantity(q, pid, user.getId());
		Article p= articleRepo.findById(pid).get();
		double sub= p.getPrix()*q;
		return sub;
	}
	
	public void removeProduct(Long pid, User user) {
		cartRepo.deleteByUserAndProduct(user.getId(),pid);
	}
}

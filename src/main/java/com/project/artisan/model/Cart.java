package com.project.artisan.model;

import jakarta.persistence.*;

@Entity
@Table
public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Article product;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	private int quatite;
	
	public Cart(Article product, User user, int quatite) {
		super();
		this.product = product;
		this.user = user;
		this.quatite = quatite;
	}
	public Cart() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Article getProduct() {
		return product;
	}
	public void setProduct(Article product) {
		this.product = product;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getQuatite() {
		return quatite;
	}
	public void setQuatite(int quatite) {
		this.quatite = quatite;
	}
	@Transient
	public double getSubtotal() {
		return Math.round((this.product.getPrix() * quatite)*100.00/100.00);
	}
}

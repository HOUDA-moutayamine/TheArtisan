package com.project.artisan.model;
import java.util.*;
import jakarta.persistence.*;

@Entity
@Table(name="Categorie")
public class Categorie {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(nullable=true, length=70)
	private String img;
	@OneToMany(mappedBy="categorie", cascade=CascadeType.ALL)
	private List<Article> articles=new ArrayList<Article>();
	public Categorie() {};
	public Categorie( String name, String img) {
		this.name = name; 
		this.img = img;
	}
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}
	public int getId() {
		return id;
	}
	public void setId(int code) {
		this.id = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Categorie [code=" + id + ", name=" + name + ", img=" + img + "]";
	}
}

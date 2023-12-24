package com.project.artisan.model;
import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table
public class Article {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.DATE)
	private Date date = new Date(System.currentTimeMillis());
	private String designation;
	private String description;
	@Column(nullable=true, length=70)
	private String photo;
	private String image;
	private String image1;
	private String image2;
	private String image3;
	private Long quantite;
	private double height;
	private double width;
	@Column(length=1000)
	private String moredescription;
	private double prix;
	@ManyToOne
	@JoinColumn(name="codecate")
	private Categorie categorie;
	public Article(Long id, Date date, String designation, String description, String photo, String image,
			String image1, String image2, String image3, Long quantite, double height, double width,
			String moredescription, double prix, Categorie categorie) {
		super();
		this.id = id;
		this.date = date;
		this.designation = designation;
		this.description = description;
		this.photo = photo;
		this.image = image;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
		this.quantite = quantite;
		this.height = height;
		this.width = width;
		this.moredescription = moredescription;
		this.prix = prix;
		this.categorie = categorie;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public String getMoredescription() {
		return moredescription;
	}

	public void setMoredescription(String moredescription) {
		this.moredescription = moredescription;
	}

	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public Article() {};
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public long getQuantite() {
		return quantite;
	}
	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}
	
}

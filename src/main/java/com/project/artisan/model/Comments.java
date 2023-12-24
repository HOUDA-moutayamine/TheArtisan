package com.project.artisan.model;
import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comments {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String comment;
  
  @Column(nullable = false)
  private Long articleId;
  
  @Column(nullable = false)
  private String userId;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getComment() {
	return comment;
}

public void setComment(String comment) {
	this.comment = comment;
}

public Long getArticleId() {
	return articleId;
}

public void setArticleId(Long articleId) {
	this.articleId = articleId;
}

public String getUserId() {
	return userId;
}

public void setUserId(String userId) {
	this.userId = userId;
}

public Comments(String comment, Long articleId, String userId) {
	super();
	this.comment = comment;
	this.articleId = articleId;
	this.userId = userId;
}
  public Comments() {};
  }
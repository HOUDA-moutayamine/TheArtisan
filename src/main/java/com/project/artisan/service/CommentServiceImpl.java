package com.project.artisan.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.artisan.model.Comments;
import com.project.artisan.repository.CommentRepository;
@Service
public class CommentServiceImpl{
	
	@Autowired
	private CommentRepository commentRepository;
	public List<Comments> getAllComments() {
	    return commentRepository.findAll();
	  }
	
    public void addComment(String comment, Long articleId, String userId) {
	    Comments newComment = new Comments();
	    newComment.setComment(comment);
	    newComment.setArticleId(articleId);
	    newComment.setUserId(userId);
	    commentRepository.save(newComment);
	}

}

package com.project.artisan.web;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.artisan.model.*;
import com.project.artisan.service.*;


@RestController
public class CommentController {
  @Autowired
  private CommentServiceImpl commentService;

  @GetMapping("/comment/list")
  public List<Comments> getAllComments() {
    return commentService.getAllComments();
  }

  @PostMapping("/comment/add")
  public ResponseEntity<String> addComment( @RequestParam("articleId") Long articleId,@RequestParam("comment") String comment, @RequestParam("userId") String userId) {
    try {
      commentService.addComment(comment, articleId, userId);
      return ResponseEntity.ok().body("Comment added successfully");
    } catch (Exception ex) {
      return ResponseEntity.badRequest().body("Error adding comment: " + ex.getMessage());
      
    }
  }
  
  
}
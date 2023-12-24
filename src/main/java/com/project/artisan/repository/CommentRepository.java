package com.project.artisan.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.artisan.model.Comments;

@Repository
public interface CommentRepository extends JpaRepository<Comments,Integer> {

}

package com.example.NewService.repository;

import com.example.NewService.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>
{
    Integer countByNewsId(Long id);
}

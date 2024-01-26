package com.example.NewService.repository;

import com.example.NewService.model.News;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News>
{
    @Override
    @EntityGraph(attributePaths = {"user", "comments", "category"})
    Optional<News> findById(Long id);
}

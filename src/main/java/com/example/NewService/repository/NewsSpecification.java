package com.example.NewService.repository;

import com.example.NewService.dro.NewsRequest;
import com.example.NewService.model.News;
import org.springframework.data.jpa.domain.Specification;

public interface NewsSpecification
{
    static Specification<News> withRequest(NewsRequest request) {
        return Specification
                .where(byUserId(request.getUserId()))
                .and(byCategoryId(request.getCategoryId()));
    }

    static Specification<News> byUserId(Long userId) {
        return (root, query, criteriaBuilder) -> {
            if(userId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("user").get("id"), userId);
        };
    }

    static Specification<News> byCategoryId(Long categoryId) {
        return (root, query, criteriaBuilder) -> {
            if(categoryId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("category").get("id"), categoryId);
        };
    }
}

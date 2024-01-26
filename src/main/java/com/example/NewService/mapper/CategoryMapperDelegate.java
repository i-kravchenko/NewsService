package com.example.NewService.mapper;

import com.example.NewService.dro.CategoryResponse;
import com.example.NewService.dro.NewsRequest;
import com.example.NewService.dro.UpsertCategoryRequest;
import com.example.NewService.model.Category;
import com.example.NewService.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class CategoryMapperDelegate implements CategoryMapper
{
    @Autowired
    @Qualifier("delegate")
    private CategoryMapper delegate;
    @Autowired
    private NewsService newsService;

    @Override
    public Category requestToCategory(UpsertCategoryRequest request) {
        return delegate.requestToCategory(request);
    }

    @Override
    public CategoryResponse categoryToResponse(Category category) {
        CategoryResponse response = delegate.categoryToResponse(category);
        NewsRequest request = new NewsRequest();
        request.setCategoryId(category.getId());
        response.setNewsCount(newsService.getCount(request));
        return response;
    }
}

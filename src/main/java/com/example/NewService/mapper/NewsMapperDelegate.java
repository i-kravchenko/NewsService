package com.example.NewService.mapper;

import com.example.NewService.dro.NewsResponse;
import com.example.NewService.dro.NewsResponseList;
import com.example.NewService.dro.UpsertNewsRequest;
import com.example.NewService.model.News;
import com.example.NewService.service.CategoryService;
import com.example.NewService.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class NewsMapperDelegate implements NewsMapper
{
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentService commentService;
    @Autowired
    @Qualifier("delegate")
    private NewsMapper delegate;

    @Override
    public News requestToNews(UpsertNewsRequest request) {
        News news = delegate.requestToNews(request);
        if(request.getCategoryId() != null) {
            news.setCategory(categoryService.findById(request.getCategoryId()));
        }
        return news;
    }

    @Override
    public NewsResponseList newsToResponseList(News news) {
        NewsResponseList response = delegate.newsToResponseList(news);
        Integer commentsCount = commentService.getCountByNewsId(news.getId());
        response.setCommentsCount(commentsCount);
        return response;
    }

    @Override
    public NewsResponse newsToResponse(News news) {
        return delegate.newsToResponse(news);
    }
}

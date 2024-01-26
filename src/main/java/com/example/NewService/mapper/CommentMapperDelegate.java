package com.example.NewService.mapper;

import com.example.NewService.dro.CommentResponse;
import com.example.NewService.dro.UpsertCommentRequest;
import com.example.NewService.model.Comment;
import com.example.NewService.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class CommentMapperDelegate implements CommentMapper
{
    @Autowired
    private NewsService newsService;
    @Autowired
    @Qualifier("delegate")
    private CommentMapper delegate;

    @Override
    public Comment requestToComment(UpsertCommentRequest request) {
        Comment comment = delegate.requestToComment(request);
        if(request.getNewsId() != null) {
            comment.setNews(newsService.findById(request.getNewsId()));
        }
        return comment;
    }

    @Override
    public CommentResponse commentToResponse(Comment comment) {
        return delegate.commentToResponse(comment);
    }
}

package com.example.NewService.mapper;

import com.example.NewService.dro.NewsResponse;
import com.example.NewService.dro.NewsResponseList;
import com.example.NewService.dro.UpsertNewsRequest;
import com.example.NewService.model.News;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommentMapper.class})
@DecoratedWith(NewsMapperDelegate.class)
public interface NewsMapper
{
    News requestToNews(UpsertNewsRequest request);

    @Mapping(source = "id", target = "newsId")
    NewsResponse newsToResponse(News news);

    @Mapping(source = "id", target = "newsId")
    NewsResponseList newsToResponseList(News news);
}

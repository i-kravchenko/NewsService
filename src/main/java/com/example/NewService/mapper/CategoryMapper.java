package com.example.NewService.mapper;


import com.example.NewService.dro.CategoryResponse;
import com.example.NewService.dro.UpsertCategoryRequest;
import com.example.NewService.model.Category;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@DecoratedWith(CategoryMapperDelegate.class)
public interface CategoryMapper
{
    Category requestToCategory(UpsertCategoryRequest request);

    @Mapping(source = "id", target = "categoryId")
    CategoryResponse categoryToResponse(Category category);
}

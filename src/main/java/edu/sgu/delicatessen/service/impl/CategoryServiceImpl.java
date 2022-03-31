package edu.sgu.delicatessen.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sgu.delicatessen.dto.request.CategoryCreateRequest;
import edu.sgu.delicatessen.dto.request.CategoryUpdateRequest;
import edu.sgu.delicatessen.dto.response.CategoryResponse;
import edu.sgu.delicatessen.entity.Category;
import edu.sgu.delicatessen.exception.ApiException;
import edu.sgu.delicatessen.repository.CategoryRepository;
import edu.sgu.delicatessen.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository catalogRepository;
    @Override
    public List<CategoryResponse> findAll() {
        List<CategoryResponse> categories=catalogRepository.findAll().stream()
        .map(s->new ModelMapper().map(s, CategoryResponse.class)).collect(Collectors.toList());
        return categories;
    }

    @Override
    @Transactional
    public CategoryResponse create(CategoryCreateRequest request) throws ApiException {
        Category entity= new Category();
        if(StringUtils.isBlank(request.getCategoryName())){
            throw ApiException.invalidField("category name");
        }
        entity.setCategoryName(request.getCategoryName());
        entity = catalogRepository.save(entity);
        return new ModelMapper().map(entity, CategoryResponse.class);
    }

    @Override
    @Transactional
    public CategoryResponse update(CategoryUpdateRequest request) throws ApiException{
        Category category = catalogRepository.findById(request.getCategoryId()).orElseThrow(() -> ApiException.invalidField("category id"));
        category.setCategoryName(request.getCategoryName());
        category = catalogRepository.save(category);
        return new ModelMapper().map(category, CategoryResponse.class);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws ApiException {
        Category category = catalogRepository.findById(id).orElseThrow(() -> ApiException.invalidField("category id"));
        catalogRepository.delete(category);
    }

    @Override
    public CategoryResponse findById(Integer id) {
        Category category= catalogRepository.findById(id).orElseThrow(() -> ApiException.invalidField("category id"));
        return new ModelMapper().map(category, CategoryResponse.class);
    }
    
}

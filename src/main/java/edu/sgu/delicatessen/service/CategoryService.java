package edu.sgu.delicatessen.service;

import java.util.List;

import edu.sgu.delicatessen.dto.request.CategoryCreateRequest;
import edu.sgu.delicatessen.dto.request.CategoryUpdateRequest;
import edu.sgu.delicatessen.dto.response.CategoryResponse;

public interface CategoryService {
    List<CategoryResponse> findAll();
    CategoryResponse findById(Integer id);
    CategoryResponse  create(CategoryCreateRequest request);
    CategoryResponse  update(CategoryUpdateRequest request);
    void delete(Integer id);
}

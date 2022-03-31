package edu.sgu.delicatessen.service;

import java.util.List;

import edu.sgu.delicatessen.dto.request.PostCreateRequest;
import edu.sgu.delicatessen.dto.request.PostUpdateRequest;
import edu.sgu.delicatessen.dto.response.PostResponse;

public interface PostService {
    List<PostResponse> findAll();
    List<PostResponse> findByCategory(Integer categoryId);
    PostResponse findById(Integer id);
    PostResponse  create(PostCreateRequest request,String username);
    PostResponse  update(PostUpdateRequest request,String username);
    void delete(Integer id);
}

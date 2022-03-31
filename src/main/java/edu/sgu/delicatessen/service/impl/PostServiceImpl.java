package edu.sgu.delicatessen.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sgu.delicatessen.dto.request.PostCreateRequest;
import edu.sgu.delicatessen.dto.request.PostUpdateRequest;
import edu.sgu.delicatessen.dto.response.PostResponse;
import edu.sgu.delicatessen.entity.Category;
import edu.sgu.delicatessen.entity.Post;
import edu.sgu.delicatessen.entity.User;
import edu.sgu.delicatessen.exception.ApiException;
import edu.sgu.delicatessen.repository.CategoryRepository;
import edu.sgu.delicatessen.repository.PostRepository;
import edu.sgu.delicatessen.repository.UserRepository;
import edu.sgu.delicatessen.service.PostService;
@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository catalogRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<PostResponse> findAll() {
        List<PostResponse> posts = postRepository.findAll().stream()
        .map(s->new ModelMapper().map(s, PostResponse.class)).collect(Collectors.toList());;
        return posts;
    }

    @Override
    @Transactional
    public PostResponse create(PostCreateRequest request,String username) throws ApiException ,UsernameNotFoundException{
        Post entity= new ModelMapper().map(request, Post.class);
        Category category = catalogRepository.findById(request.getCategoryId()).orElseThrow(() -> ApiException.invalidField("category id"));
        entity.setCategory(category);
        User user = userRepository.findByUsername(username)
          .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        entity.setUser(user);
        entity = postRepository.save(entity);
        return new ModelMapper().map(entity, PostResponse.class);
    }

    @Override
    @Transactional
    public PostResponse update(PostUpdateRequest request,String username) {
        Post entity= postRepository.findById(request.getPostId()).orElseThrow(() -> ApiException.invalidField("post id"));
        entity.setContent(request.getContent());
        entity.setTitle(request.getTitle());
        Category category = catalogRepository.findById(request.getCategoryId()).orElseThrow(() -> ApiException.invalidField("category id"));
        entity.setCategory(category);
        User user = userRepository.findByUsername(username)
          .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        entity.setUser(user);
        entity = postRepository.save(entity);
        return new ModelMapper().map(entity, PostResponse.class);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws ApiException {
        Post entity= postRepository.findById(id).orElseThrow(() -> ApiException.invalidField("post id"));
        postRepository.delete(entity);
    }

    @Override
    public List<PostResponse> findByCategory(Integer categoryId) {
        List<PostResponse> posts = postRepository.findByCategory_CategoryId(categoryId).stream()
        .map(s->new ModelMapper().map(s, PostResponse.class)).collect(Collectors.toList());;
        return posts;
    }

    @Override
    public PostResponse findById(Integer id)  throws ApiException{
        Post entity= postRepository.findById(id).orElseThrow(() -> ApiException.invalidField("post id"));
        return new ModelMapper().map(entity, PostResponse.class);
    }
    
}

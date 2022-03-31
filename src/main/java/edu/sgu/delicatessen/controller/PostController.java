package edu.sgu.delicatessen.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sgu.delicatessen.dto.request.PostCreateRequest;
import edu.sgu.delicatessen.dto.request.PostUpdateRequest;
import edu.sgu.delicatessen.dto.response.BaseResponse;
import edu.sgu.delicatessen.dto.response.PostResponse;
import edu.sgu.delicatessen.service.PostService;
@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService postService;
    @GetMapping
    public ResponseEntity<BaseResponse<List<PostResponse>>> getAll(){
        return ResponseEntity.ok(new BaseResponse<List<PostResponse>>(postService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<PostResponse>> findById(@PathVariable Integer id){
        return ResponseEntity.ok(new BaseResponse<PostResponse>(postService.findById(id)));
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<BaseResponse<List<PostResponse>>> findByCategory(@PathVariable Integer id){
        return ResponseEntity.ok(new BaseResponse<List<PostResponse>>(postService.findByCategory(id)));
    }
    @PostMapping
    public ResponseEntity<BaseResponse<PostResponse>> create(@Valid @RequestBody PostCreateRequest request,Authentication authentication ) {

        return ResponseEntity.ok(new BaseResponse<PostResponse>(postService.create(request, authentication.getName())));
    }
    @PutMapping
    public ResponseEntity<BaseResponse<PostResponse>> update(@Valid @RequestBody PostUpdateRequest request,Authentication authentication ) {
        return ResponseEntity.ok(new BaseResponse<PostResponse>(postService.update(request, authentication.getName())));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        postService.delete(id);
        return ResponseEntity.ok("sucess");
    }
}

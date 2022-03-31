package edu.sgu.delicatessen.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sgu.delicatessen.dto.request.CategoryCreateRequest;
import edu.sgu.delicatessen.dto.request.CategoryUpdateRequest;
import edu.sgu.delicatessen.dto.response.BaseResponse;
import edu.sgu.delicatessen.dto.response.CategoryResponse;
import edu.sgu.delicatessen.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<BaseResponse<List<CategoryResponse>>> getAll(){
        return ResponseEntity.ok(new BaseResponse<List<CategoryResponse>>(categoryService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<CategoryResponse>> findById(@PathVariable Integer id){
        return ResponseEntity.ok(new BaseResponse<CategoryResponse>(categoryService.findById(id)));
    }
    @PostMapping
    public ResponseEntity<BaseResponse<CategoryResponse>> create(@Valid @RequestBody CategoryCreateRequest request ) {

        return ResponseEntity.ok(new BaseResponse<CategoryResponse>(categoryService.create(request)));
    }
    @PutMapping
    public ResponseEntity<BaseResponse<CategoryResponse>> update(@Valid @RequestBody CategoryUpdateRequest request) {
        return ResponseEntity.ok(new BaseResponse<CategoryResponse>(categoryService.update(request)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        categoryService.delete(id);
        return ResponseEntity.ok("sucess");
    }
}

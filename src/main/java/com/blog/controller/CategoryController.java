package com.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.playload.AppResponse;
import com.blog.playload.CategoryDto;
import com.blog.services.CategoryService;


@Controller
@RequestMapping("/api/Categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/createCategory")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		
		return new ResponseEntity<>(createCategory,HttpStatus.CREATED);
		
	}
    @DeleteMapping("/deleteCategory/{id}")
	public ResponseEntity<AppResponse> deleteCategory(@PathVariable("id")Integer categoryid){
		
    	this.categoryService.deleteCategory(categoryid);
    	
    	return new ResponseEntity<AppResponse>(new AppResponse("Category Deleted Successfully",true),HttpStatus.OK);
	}
    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid@PathVariable("id") Integer categoryid, @RequestBody CategoryDto categoryDto){
    	
    	CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, categoryid);
    	
    	return ResponseEntity.ok(updateCategory);
    }
    @GetMapping("/getcategory/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Integer categoryid){
    	
    	CategoryDto category = this.categoryService.getCategory(categoryid);
    	
    	return new ResponseEntity<>(category,HttpStatus.OK);
    }
    @GetMapping("/getAllCategories")
    public ResponseEntity<?> getAllCategories(){
    	List<CategoryDto> allCategories = this.categoryService.getAllCategories();
    	
    	return ResponseEntity.ok(allCategories);
    }
    
	
}

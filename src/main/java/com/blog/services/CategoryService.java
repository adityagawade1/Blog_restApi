package com.blog.services;

import java.util.List;

import com.blog.playload.CategoryDto;

public interface CategoryService {
	
	  CategoryDto createCategory(CategoryDto categoryDto);
	 
	 CategoryDto updateCategory(CategoryDto categoryDto ,int categoryid);
	  
	  void deleteCategory(int categoryid);
	  
	  CategoryDto getCategory(int categoryid);
	  
	  List<CategoryDto> getAllCategories();

}

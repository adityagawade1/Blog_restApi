package com.blog.serviceIMpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Categaory;
import com.blog.exceptionhandling.ResourceNotFoundException;
import com.blog.playload.CategoryDto;
import com.blog.repositories.CategoryRepository;
import com.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
	private ModelMapper modlemapper;
    
    @Autowired  
  private CategoryRepository categoryRepo;
	 
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		   Categaory category = this.dtoToCategory(categoryDto);
		   
		   Categaory save = this.categoryRepo.save(category);
		   
		return this.categoryToDto(save);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryid) {
		   
		   Categaory findById = this.categoryRepo.findById(categoryid).orElseThrow(()-> new ResourceNotFoundException("Category"," category id ", categoryid));
		   
		
		Categaory category = this.dtoToCategory(categoryDto);
		category.setCategoryid(categoryid);
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		 Categaory save = this.categoryRepo.save(category);
				
		
		return this.categoryToDto(save);
	}

	@Override
	public void deleteCategory(int categoryid) {
		
	   Categaory id = this.categoryRepo.findById(categoryid).orElseThrow(()-> new ResourceNotFoundException("Category", " Category id ", categoryid));
		  
	   
	   this.categoryRepo.delete(id);
	}

	@Override
	public CategoryDto getCategory(int categoryid) {
		
		 Categaory category = this.categoryRepo.findById(categoryid).orElseThrow(()-> new ResourceNotFoundException("Category", " Category id ", categoryid));
		  
		 
		return this.categoryToDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		
		 List<Categaory> findAll = this.categoryRepo.findAll();
		 List<CategoryDto> allcategories= findAll.stream().map((category)-> this.categoryToDto(category)).collect(Collectors.toList()); 
		 
		return allcategories;
	}
	
	public Categaory dtoToCategory(CategoryDto categoryDto) {
		
		Categaory category= this.modlemapper.map(categoryDto, Categaory.class);
		
		return category;
		
	}
	
	public CategoryDto categoryToDto(Categaory category) {
		
		CategoryDto categoryDto=this.modlemapper.map(category, CategoryDto.class);
		
		return categoryDto;
	}
	 

}

package com.blog.playload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CategoryDto {

	
	private int categoryid;
	@NotEmpty
	@Size(min = 3,message = "Title should not be less than 3 character")
	@Pattern(regexp = "")
	private String categoryTitle;
	@NotEmpty(message = "Description cannot be null")
	private String CategoryDescription;
	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CategoryDto(int categoryid, String categoryTitle, String categoryDescription) {
		super();
		this.categoryid = categoryid;
		this.categoryTitle = categoryTitle;
		CategoryDescription = categoryDescription;
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public String getCategoryDescription() {
		return CategoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		CategoryDescription = categoryDescription;
	}
	
	
}

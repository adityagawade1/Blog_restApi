package com.blog.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categaory {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name="Category_ID")
	private int categoryid;
	 @Column(name="Title" ,nullable = false)	 
	private String categoryTitle;
	 @Column(name="Description")
	private String CategoryDescription;
	 @OneToMany(mappedBy = "category", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	 private List<Post> post= new ArrayList<>();
			 
	
	 
	public List<Post> getPost() {
		return post;
	}
	public void setPost(List<Post> post) {
		this.post = post;
	}
	public Categaory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Categaory(int categoryid, String categoryTitle, String categoryDescription, List<Post> post) {
		super();
		this.categoryid = categoryid;
		this.categoryTitle = categoryTitle;
		CategoryDescription = categoryDescription;
		this.post = post;
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

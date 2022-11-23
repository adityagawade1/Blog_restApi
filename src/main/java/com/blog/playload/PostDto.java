package com.blog.playload;

import java.util.Date;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.blog.entity.Categaory;
import com.blog.entity.Comment;
import com.blog.entity.User;

public class PostDto {


	 
	 private String postname;
	 
	 private String postDescription;
    
	 private String postedOn;
		private String image;
		
		
		private UserDto user;
		
		private CategoryDto category;
		
		private Set<CommentDto> comment;

		
	
	

	public Set<CommentDto> getComment() {
			return comment;
		}

		public void setComment(Set<CommentDto> comment) {
			this.comment = comment;
		}

	public PostDto(String postname, String postDescription, String postedOn, String image, UserDto user,
				CategoryDto category, Set<CommentDto> comment) {
			super();
			this.postname = postname;
			this.postDescription = postDescription;
			this.postedOn = postedOn;
			this.image = image;
			this.user = user;
			this.category = category;
			this.comment = comment;
		}

	public String getPostedOn() {
			return postedOn;
		}

		public void setPostedOn(String postedOn) {
			this.postedOn = postedOn;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public UserDto getUser() {
			return user;
		}

		public void setUser(UserDto user) {
			this.user = user;
		}

		public CategoryDto getCategory() {
			return category;
		}

		public void setCategory(CategoryDto category) {
			this.category = category;
		}

	public String getPostname() {
		return postname;
	}

	public void setPostname(String postname) {
		this.postname = postname;
	}

	public String getPostDescription() {
		return postDescription;
	}

	public void setPostDescription(String postDescription) {
		this.postDescription = postDescription;
	}

	

	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	 
	
	
}

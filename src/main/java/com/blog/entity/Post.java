package com.blog.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int postid;
	@Column(name="post_name",nullable = false )
	private String postname;
	@Column(name="Post_Content",nullable = false)
	private String postDescription;
	
	private String postedOn;
	private String image;
	
	@ManyToOne
	@JoinColumn(name = "User_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="Category_id")
	private Categaory category;
	
	@OneToMany(mappedBy = "postComment",cascade = CascadeType.ALL)
	private Set<Comment> comment=new HashSet<>() ;

	

	public Post(int postid, String postname, String postDescription, String postedOn, String image, User user,
			Categaory category, Set<Comment> comment) {
		super();
		this.postid = postid;
		this.postname = postname;
		this.postDescription = postDescription;
		this.postedOn = postedOn;
		this.image = image;
		this.user = user;
		this.category = category;
		this.comment = comment;
	}

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getPostid() {
		return postid;
	}

	public void setPostid(int postid) {
		this.postid = postid;
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

	public String getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(String date1) {
		this.postedOn = date1;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Categaory getCategory() {
		return category;
	}

	public void setCategory(Categaory category) {
		this.category = category;
	}

	public Set<Comment> getComment() {
		return comment;
	}

	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}

	 
	
	
	

}

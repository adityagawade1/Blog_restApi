package com.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
  
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int commentid;
	 @Column(name="Content")
	 private String commentcontent;
	 
	 @ManyToOne
	 @JoinColumn(name = "postid")
	 private Post postComment;

	public int getCommentid() {
		return commentid;
	}

	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}

	public String getCommentcontent() {
		return commentcontent;
	}

	public void setCommentcontent(String commentcontent) {
		this.commentcontent = commentcontent;
	}

	public Post getPostComment() {
		return postComment;
	}

	public void setPostComment(Post postComment) {
		this.postComment = postComment;
	}

	public Comment(int commentid, String commentcontent, Post postComment) {
		super();
		this.commentid = commentid;
		this.commentcontent = commentcontent;
		this.postComment = postComment;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	 
	 
	 
	 
}

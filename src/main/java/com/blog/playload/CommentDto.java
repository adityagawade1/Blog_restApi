package com.blog.playload;


public class CommentDto {
	
	private int commentid;
	
	private String commentContent;
	


	public int getCommentid() {
		return commentid;
	}

	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	
	public CommentDto(int commentid, String commentContent) {
		super();
		this.commentid = commentid;
		this.commentContent = commentContent;
		
	}

	public CommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}

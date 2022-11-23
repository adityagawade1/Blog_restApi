package com.blog.playload;

public class AppResponse {
 
	  private String message;
	  private boolean success;
	public AppResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AppResponse(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	  
	  
}

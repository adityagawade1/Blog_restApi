package com.blog.services;

import com.blog.playload.CommentDto;

public interface CommentService {
	
	
	CommentDto createComment(CommentDto commentDto,Integer postid);
	
	void deleteComment(Integer commentid);

}

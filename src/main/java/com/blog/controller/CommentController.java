package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.playload.AppResponse;
import com.blog.playload.CommentDto;
import com.blog.services.CommentService;

@RestController
@RequestMapping("/Api/comment")
public class CommentController {

	 @Autowired
	private CommentService commentService; 
	
	 @PostMapping("/post/{postid}/createComment")
	 public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable("postid")Integer postid){
		  
		  CommentDto createComment = this.commentService.createComment(commentDto, postid);
		 
		  
		  return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
	 }
	 
	 @DeleteMapping("/deletecomment/{commentid}")
	 public ResponseEntity<AppResponse> deleteComment(@PathVariable("commentid") Integer commentid){
		 
		 this.commentService.deleteComment(commentid);
		 
		 return ResponseEntity.ok(new AppResponse("Comment deleted succesfully",true));
		 
	 }
}

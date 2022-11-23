package com.blog.serviceIMpl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exceptionhandling.ResourceNotFoundException;
import com.blog.playload.CommentDto;
import com.blog.repositories.CommentRepo;
import com.blog.repositories.PostRepo;
import com.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostRepo postRepo;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postid) {
		
		Post post = this.postRepo.findById(postid).orElseThrow(()-> new  ResourceNotFoundException("Post", " post id ", postid));
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPostComment(post);
		Comment save = this.commentRepo.save(comment);
		
		CommentDto commentDto2 = this.modelMapper.map(save, CommentDto.class);
		return commentDto2;
	}

	@Override
	public void deleteComment(Integer commentid) {
		Comment comment=this.commentRepo.findById(commentid).orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment id ", commentid)) ;
		
		this.commentRepo.delete(comment);
		
	}

	
	

}

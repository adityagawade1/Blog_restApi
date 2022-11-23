package com.blog.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;

import com.blog.entity.Post;
import com.blog.playload.PostDto;
import com.blog.playload.PostResponse;

public interface PostService {
   
	 PostDto createPost(PostDto postDto,Integer userid,Integer categoryid);
	 
	 PostDto updatePost(PostDto postDto,Integer postid);
	 
	 void deletePost(Integer postid);
	 
	 PostDto getPost(Integer postid);
	 
	
	 
	 List<PostDto> getPostByUser(Integer userid);
	 
	 List<PostDto> getPostByCategory(Integer categoryid);
	 
	 List<PostDto> searchPost(String key);

	 PostResponse getAllPost(Integer pagenumber, Integer pagesize, String sortBy, String sortDir);

	
}

package com.blog.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.entity.Post;
import com.blog.playload.AppResponse;
import com.blog.playload.PostDto;
import com.blog.playload.PostResponse;
import com.blog.services.FileService;
import com.blog.services.PostService;

@RestController
@RequestMapping("/Api/post")
public class PostController {
     @Autowired
	 private PostService postService;
     
     @Autowired
     private FileService fileService;
     
     @Value("${project.image}")
     private String path;
     
     @PostMapping("/createPost/user/{id}/category/{cid}")
     public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable("id")Integer userid,@PathVariable("cid")Integer categoryid ){
    	 
    	 PostDto createPost = this.postService.createPost(postDto, userid, categoryid);
    	 
    	 return new ResponseEntity<>(createPost,HttpStatus.CREATED);
    	 
     }
     
     @DeleteMapping("/deletepost/{id}")
     public ResponseEntity<AppResponse> deletePost(@PathVariable("id")Integer postid){
    	   
    	 this.postService.deletePost(postid);
    	 
    	 return new ResponseEntity<AppResponse>(new AppResponse("Post deleted successfully",true),HttpStatus.OK);
    	 
     }
     @GetMapping("/getPost/{id}")
     public ResponseEntity<PostDto> getPostByid(@PathVariable("id")Integer postid){
    	 
    	PostDto post= this.postService.getPost(postid);
    	
    	return ResponseEntity.ok(post);
     }
     @GetMapping("/getallpost")
     public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pagenumber",defaultValue = "0",required = false)Integer pagenumber,
 			@RequestParam(value="pagesize",defaultValue = "10",required = false)Integer pagesize,
 			@RequestParam(value = "sortBy",defaultValue = "postid", required = false)String sortBy,
 			@RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir){
    	 
    	 return ResponseEntity.ok(this.postService.getAllPost(pagenumber, pagesize,sortBy,sortDir));
     }
     @GetMapping("/getpostbyuser/{id}")
     public ResponseEntity<List<PostDto>> getpostbyuser(@PathVariable("id")Integer userid){
    	 
    	 List<PostDto> postByUser = this.postService.getPostByUser(userid);
    	 
    	 return ResponseEntity.ok(postByUser);
     }
     
     @GetMapping("/getpostbycategory/{id}")
     public ResponseEntity<List<PostDto>> getpostbycategory(@PathVariable("id")Integer categoryid){
    	 List<PostDto> postByCategory = this.postService.getPostByCategory(categoryid);
    	 
    	 return ResponseEntity.ok(postByCategory);
    	 
     }
     
     @PutMapping("/updatepost/{id}")
     public ResponseEntity<PostDto> updatePost(@PathVariable("id")Integer postid, PostDto postDto){
    	   
    	 PostDto updatePost = this.postService.updatePost(postDto, postid);
    	 
    	 return new ResponseEntity<>(updatePost,HttpStatus.OK);
    	 
     }
     @GetMapping("/searchpost/{search}")
     public ResponseEntity<List<PostDto>> searchpost(@PathVariable("search")String key){
    	 List<PostDto> searchPost = this.postService.searchPost(key);
    	 
    	 return ResponseEntity.ok(searchPost);
    	 
     }
     
     @PostMapping("/image/upload/{id}")
     public ResponseEntity<PostDto> uploadImage(@PathVariable("id")Integer postid,@RequestParam("image")MultipartFile file) throws IOException{
    	 PostDto post = this.postService.getPost(postid);
    	  String uploadimage = this.fileService.uploadimage(file, path );
    	  
    	  post.setImage(uploadimage);
    	  PostDto updatePost = this.postService.updatePost(post, postid);
    	 
    	 return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
     }
     
     @GetMapping("image/{imageName}")
 	public void downloadfile(@PathVariable("imageName")String imageName,HttpServletResponse response) throws IOException {
 		
 		InputStream resource = this.fileService.getResource(path, imageName);
 		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
 		StreamUtils.copy(resource,response.getOutputStream());

 	}
     
}

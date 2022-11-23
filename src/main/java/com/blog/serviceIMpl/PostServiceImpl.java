package com.blog.serviceIMpl;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.entity.Categaory;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.exceptionhandling.ResourceNotFoundException;
import com.blog.playload.PostDto;
import com.blog.playload.PostResponse;
import com.blog.repositories.CategoryRepository;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepository;
import com.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	 @Autowired
	 private ModelMapper modelmapper;
	 @Autowired
	 private PostRepo postRepo;
	 @Autowired
	 private UserRepository userRepo;
	 @Autowired
	 private CategoryRepository categoryRepo;
	 
	@Override
	public PostDto createPost(PostDto postDto,Integer userid,Integer categoryids) {
		User user = this.userRepo.findById(userid).orElseThrow(()->new ResourceNotFoundException("User", " userid ", userid));
		Categaory categaory = this.categoryRepo.findById(categoryids).orElseThrow(()-> new ResourceNotFoundException("Category", " Category id " , categoryids));
		
		Post post= this.modelmapper.map(postDto, Post.class);
		post.setImage("Default.img");
		Date date= new Date();
		String date1=DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM).format(date);
		post.setPostedOn(date1);
		post.setUser(user);
		post.setCategory(categaory);
		
		Post save = this.postRepo.save(post);
		
		return this.modelmapper.map(save, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postid) {
		Post post=this.postRepo.findById(postid).orElseThrow(()->new ResourceNotFoundException("Post", " Post id ", postid));
		
	Post p= this.modelmapper.map(postDto, Post.class);
	 p.setPostname(postDto.getPostname());
	 p.setPostDescription(postDto.getPostDescription());
	 p.setImage(postDto.getImage());
	
		return this.modelmapper.map(p,PostDto.class);
	}

	@Override
	public void deletePost(Integer postid) {
	Post post=	this.postRepo.findById(postid).orElseThrow(()->new ResourceNotFoundException("Post", " Post id ", postid));
		
		this.postRepo.delete(post);
		
	}

	@Override
	public PostDto getPost(Integer postid) {
		Post post = this.postRepo.findById(postid).orElseThrow(()-> new ResourceNotFoundException("Post", " Post id ", postid));
		
		PostDto postDto = this.modelmapper.map(post, PostDto.class);
		return postDto ;
	}

	@Override
	public PostResponse getAllPost(Integer pagenumber,Integer pagesize,String sortBy,String sortDir	) {
		Sort sort= null;
		if (sortDir.equalsIgnoreCase("asc")) {
			sort= Sort.by(sortBy).ascending();
		}
		else {
			sort=Sort.by(sortBy).descending();
		}
		
		  Pageable page = PageRequest.of(pagenumber, pagesize,sort);
		 Page<Post> findAll = this.postRepo.findAll(page);
		 List<Post> all = findAll.getContent();
		 List<PostDto> collect = all.stream().map(post-> this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		  PostResponse postResponse= new PostResponse();
		  postResponse.setContent(collect);
		  postResponse.setPagenumber(findAll.getNumber());
           postResponse.setPagesize(findAll.getSize());
           postResponse.setLastpage(findAll.isLast());
           postResponse.setTotalelement(findAll.getTotalElements());
           postResponse.setTotalpages(findAll.getTotalPages());
		  
		return postResponse;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userid) {
		
	User user = this.userRepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User", " user id ", userid));
		      List<Post> list = this.postRepo.findByUser(user);
		      
		      List<PostDto> collect = list.stream().map(post -> this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		      
		return collect;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryid) {
		Categaory categaory = this.categoryRepo.findById(categoryid).orElseThrow(()-> new ResourceNotFoundException("Category", " category id ", categoryid));
		 List<Post> findByCategory = this.postRepo.findByCategory(categaory);
		 List<PostDto> collect = findByCategory.stream().map(post-> this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		 
		return collect;
	}

	@Override
	public List<PostDto> searchPost(String key) {
		   
		List<Post> list = this.postRepo.findByPostnameContaining(key);
		
		  List<PostDto> postdto = list.stream().map((p)->this.modelmapper.map(p, PostDto.class)).collect(Collectors.toList());
		return postdto;
	}

}

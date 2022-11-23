package com.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.entity.Categaory;
import com.blog.entity.Post;
import com.blog.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
       
	  
	  List<Post> findByUser(User user);
	  List<Post> findByCategory(Categaory category);
	  
	  List<Post> findByPostnameContaining(String postname);
}

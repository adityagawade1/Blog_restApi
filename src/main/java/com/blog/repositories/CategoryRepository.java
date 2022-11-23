package com.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entity.Categaory;

public interface CategoryRepository extends JpaRepository<Categaory, Integer>{
	

}

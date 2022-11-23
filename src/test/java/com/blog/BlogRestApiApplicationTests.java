package com.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blog.repositories.UserRepository;


@SpringBootTest
class BlogRestApiApplicationTests {
	
	@Autowired
	private UserRepository userRepo;
	


	@Test
	void contextLoads() {
	}
	
	@Test
	public void userRepoTest() {
		
		String name = this.userRepo.getClass().getName();
		String package1 = this.userRepo.getClass().getPackageName();
		
		System.out.println(name);
		System.out.println(package1);
		
	}
	

}

package com.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blog.entity.Role;
import com.blog.repositories.RoleRepo;


@SpringBootApplication
public class BlogRestApiApplication implements CommandLineRunner{
     
	 @Autowired
	 private RoleRepo roleRepo;
	public static void main(String[] args) {
		SpringApplication.run(BlogRestApiApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		
		
		return new ModelMapper();
	}
	@Override
	public void run(String... args) throws Exception {
		
		Role role= new Role();
		role.setId(1);
		role.setName("ROLE_ADMIN");
		
		Role role1=new Role();
		role1.setId(2);
		role1.setName("ROLE_NORMAL");
		
	   List<Role> list = List.of(role,role1);
		
		List<Role> all = this.roleRepo.saveAll(list);
		
		all.forEach((r)->{
			System.out.println(r.getName());
		});
		
	}
	

}

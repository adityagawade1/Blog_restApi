package com.blog.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.blog.playload.AppResponse;
import com.blog.playload.UserDto;

import com.blog.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
   
	@Autowired 
	private UserService userService;
	
	//post create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		
		UserDto createUser = this.userService.createUser(userDto);
		
		return new ResponseEntity<>(createUser, HttpStatus.CREATED);
	}
	
	
	//put update user
	
	@PutMapping("/update/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("id")Integer id){
		 
		UserDto update = this.userService.update(userDto, id);
		
		return new ResponseEntity<>(update,HttpStatus.OK);
	}
	
	
	//delete  delete user
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<AppResponse> deleteUser(@PathVariable("id")Integer id){
		 
		 this.userService.deleteUser(id);
		 
		 return new ResponseEntity<AppResponse>(new AppResponse("User Deleted successfully",true),HttpStatus.OK);
	}
	
	//get  get user
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/Getuser/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable("id")Integer id){
		
		UserDto userbyID = this.userService.getUserbyID(id);
		
		return new ResponseEntity<UserDto>(userbyID,HttpStatus.OK);
	}
	
	// get all user
	@PreAuthorize("hasRole('ADMIN')")
	  @GetMapping("/Alluser")
	public ResponseEntity<List<UserDto>> getAlluser(){
		 
		  return ResponseEntity.ok (this.userService.getAllUser());
	  }
	
	
	
	
}

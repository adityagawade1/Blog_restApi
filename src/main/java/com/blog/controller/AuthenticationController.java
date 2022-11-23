
package com.blog.controller;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.exceptionhandling.APIException;
import com.blog.playload.JwtAuthRequest;
import com.blog.playload.JwtResponse;
import com.blog.playload.UserDto;
import com.blog.repositories.UserRepository;
import com.blog.security.CustomUserDetailService;
import com.blog.security.JWTTokenHelper;
import com.blog.services.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
     @Autowired
	private JWTTokenHelper helper;
	 @Autowired
     private CustomUserDetailService service;
     @Autowired
	 private AuthenticationManager authManager;
     @Autowired
     private UserService userService;
	@PostMapping("/login")
	
	public ResponseEntity<JwtResponse> createToken(@RequestBody JwtAuthRequest authRequest){
		this.authenticate(authRequest.getUsername(),authRequest.getPassword());
		
		UserDetails userDetails = this.service.loadUserByUsername(authRequest.getUsername());
		
		String generateToken = this.helper.generateToken(userDetails);
		
		JwtResponse response= new JwtResponse();
		response.setToken(generateToken);
		
   return new ResponseEntity<JwtResponse>(response,HttpStatus.OK);		
	}
	
	private void authenticate(String username,String password)  {
		UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(username, password);
	try {
		this.authManager.authenticate(authenticationToken);
	}
	catch (Exception e) {
		// TODO: handle exception
		throw new APIException("Invalid username and password");
	}
	}
	@PostMapping("/register")
	public ResponseEntity<UserDto> registredUser(@RequestBody UserDto userDto){
		
		UserDto user = this.userService.registredUser(userDto);
		
		return new ResponseEntity<UserDto>(user,HttpStatus.CREATED);
	}
}

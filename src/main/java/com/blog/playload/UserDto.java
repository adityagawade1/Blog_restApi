package com.blog.playload;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.blog.entity.Role;



public class UserDto {
	
    private int id;
    @NotEmpty
    @Size(min = 4, message  = "name cannot be null")
    private String name;
    @Email(message = "Please Enter valid Email")
	private String email;
    @NotEmpty
    @Size(min = 3,message = "Password cannot be null")
    @Pattern(regexp = "/^(?=[^A-Z]*[A-Z])(?=[^a-z]*[a-z])(?=[^0-9]*[0-9]).{6,}$/",message = "Password must contain atleast one uppercase, one lowercase")
	private String password;
	private String about;
	private Set<Role> role= new HashSet<>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDto(int id, @NotEmpty @Size(min = 4, message = "name cannot be null") String name,
			@Email(message = "Please Enter valid Email") String email,
			@NotEmpty @Size(min = 3, message = "Password cannot be null") @Pattern(regexp = "/^(?=[^A-Z]*[A-Z])(?=[^a-z]*[a-z])(?=[^0-9]*[0-9]).{6,}$/", message = "Password must contain atleast one uppercase, one lowercase") String password,
			String about, Set<Role> role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.role = role;
	}
	public Set<Role> getRole() {
		return role;
	}
	public void setRole(Set<Role> role) {
		this.role = role;
	}
	
	
	
}

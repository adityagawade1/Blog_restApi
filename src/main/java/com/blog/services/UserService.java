package com.blog.services;

import java.util.List;

import com.blog.entity.User;
import com.blog.playload.UserDto;

public interface UserService {
	
	
	UserDto registredUser(UserDto userDto);
	UserDto createUser(UserDto user);
    UserDto update(UserDto user,int id);
    UserDto getUserbyID(Integer id);
    List<UserDto> getAllUser();
    void deleteUser(Integer id);
	

}

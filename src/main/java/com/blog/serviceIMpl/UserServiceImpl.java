package com.blog.serviceIMpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.exceptionhandling.*;
import com.blog.entity.Role;
import com.blog.entity.User;
import com.blog.playload.UserDto;
import com.blog.repositories.RoleRepo;
import com.blog.repositories.UserRepository;
import com.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
    @Autowired
	private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo roleRepo;
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user= this.dtoToUser(userDto);
		
		User saveUser= this.userRepo.save(user);
		
		
		return this.userToDto(saveUser);
	}

	@Override
	public UserDto update(UserDto userDto, int id) {
		
		User user=this.userRepo.findById(id).orElseThrow(()->  new ResourceNotFoundException("User"," id ",id));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		
		User updateUser = this.userRepo.save(user);
		return this.userToDto(updateUser);
	}

	@Override
	public UserDto getUserbyID(Integer id) {
		User user= this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User"," id ", id));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		
		List<User> allUser = this.userRepo.findAll();
		
		List<UserDto> list = allUser.stream().map(user-> this.userToDto(user)).collect(Collectors.toList());
		return list;
	}

	@Override
	public void deleteUser(Integer id) {
		User user = this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", " id ", id));
		
		this.userRepo.delete(user);

	}
	
	private User dtoToUser(UserDto userDto) {
		
		User user= this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
//		
		return user;
		
	}
	
	private UserDto userToDto(User user) {
		
		UserDto userDto= this.modelMapper.map(user,UserDto.class);
		
		
		return userDto;
	}

	@Override
	public UserDto registredUser(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);
		user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
		
		Role id = this.roleRepo.findById(2).get();
		
		user.getRole().add(id);
		
		User user1=this.userRepo.save(user);
		return this.modelMapper.map(user1, UserDto.class);
	}

}

package com.app.service.user;

import org.springframework.validation.BindingResult;

import com.app.dto.UserDto;

import java.util.List;

public interface UserService {

	boolean registerUser(UserDto userDto, BindingResult bindingResult);

	List<UserDto> getUserList(UserDto userDto);
	
	boolean login(UserDto userDto);
	
	int updateUser(UserDto userDto);
	
	UserDto getUserInfo(String userId);

	boolean idCheck(String userId);
}
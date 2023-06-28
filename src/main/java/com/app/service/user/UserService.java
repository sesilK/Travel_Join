package com.app.service.user;

import com.app.dto.user.UserDto;

import java.util.List;

public interface UserService {

	int saveUser(UserDto userDto);

	List<UserDto> getUserList(UserDto userDto);
	
	boolean login(UserDto userDto);

	boolean idCheck(String userId);

}
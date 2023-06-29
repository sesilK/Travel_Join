package com.app.service.user;

import com.app.dto.user.UserDto;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface UserService {

	boolean registerUser(UserDto userDto, BindingResult bindingResult);

	List<UserDto> getUserList(UserDto userDto);
	
	boolean login(UserDto userDto);

	boolean idCheck(String userId);
}
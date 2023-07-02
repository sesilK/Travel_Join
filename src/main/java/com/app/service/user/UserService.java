package com.app.service.user;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.app.dto.UserDto;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {

	boolean registerUser(UserDto userDto, BindingResult bindingResult);

	List<UserDto> getUserList(UserDto userDto);
	
	boolean login(UserDto userDto, HttpSession session);
	
	int updateUser(UserDto userDto);
	
	UserDto getUserInfo(String userId);

	boolean idCheck(String userId);

	int update_user_profile(UserDto userDto);
}
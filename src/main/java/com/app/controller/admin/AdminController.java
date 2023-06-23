package com.app.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.dto.UserDto;
import com.app.service.user.UserService;
import com.app.utils.CommonCode;


@Controller
public class AdminController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/registerAdmin")
	public String registerAdmin() {
		return "registerAdmin";
	}
	
	@PostMapping("/registerAdmin")
	public String registerAdmin_process(UserDto userDto) {
		
		userDto.setUserType(CommonCode.USER_USER_TYPE_ADMIN);
		int result = userService.saveUser(userDto);
		
		return "registerAdmin";
	}
}

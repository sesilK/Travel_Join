package com.app.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.dto.user.UserDto;
import com.app.service.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/main")
	public String main() {
		return "backup/main";
	}

	// 회원가입
	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/register")
	public String register_proc(@ModelAttribute UserDto userDto) {
		int result = userService.saveUser(userDto);

		return "backup/main";
	}

	// 로그인
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String login_proc(@ModelAttribute UserDto userDto, HttpSession session) {

		boolean result = userService.login(userDto);

		if (result) {
			session.setAttribute("userId", userDto.getUserId());
			return "redirect:/main";
		} else {
			// alert ~~~
			return "redirect:/login";
		}
	}

	// 로그아웃
	@RequestMapping("/logout")
	public String logout(HttpSession session) {

		session.removeAttribute("userId");
		return "redirect:/main";
	}

	// 회원정보수정 페이지
	@GetMapping("/myinfo")
	public String myinfo(HttpSession session) {
		
		String userId = (String)session.getAttribute("userId");
		UserDto userInfo = userService.getUserInfo(userId);
		session.setAttribute("userDto", userInfo);
		System.out.println("ddd");
		
		return "myinfo";
	}	

	//수정요청 
	@PostMapping("/myinfo")
	@ResponseBody
	public String myinfo_proc(@RequestBody String requestBody) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		UserDto userDto = objectMapper.readValue(requestBody, UserDto.class); //바꿀 정보 담겨있음
		System.out.println(userDto);
		int result = userService.updateUser(userDto);
		if(result == 1) {
			return "true";			
		} else {
			return "false";
		}
	}

}

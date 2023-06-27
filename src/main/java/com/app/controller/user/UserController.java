package com.app.controller.user;

import com.app.dto.user.UserDto;
import com.app.service.user.UserService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/main")
	public String main() {
		return "backup/main";
	}
	

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/register")
	public String register_proc(@ModelAttribute UserDto userDto) {
		int result = userService.saveUser(userDto);

		return "backup/main";
	}

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

	@RequestMapping("/logout")
	public String logout(HttpSession session) {

		session.removeAttribute("userId");
		return "redirect:/main";
	}

}

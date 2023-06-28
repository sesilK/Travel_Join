package com.app.controller.user;

import com.app.dto.user.UserDto;
import com.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	


	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/register")
	public String register_proc(@ModelAttribute UserDto userDto) {
		System.out.println(userDto.toString());
		int result = userService.saveUser(userDto);

		return "login";
	}

	/** 아이디 중복체크 rest api */
	@PostMapping("/api/register")
	@ResponseBody
	public boolean register_api(@RequestParam String userId) {
		boolean result = false;
		result = userService.idCheck(userId);
		return result;
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
			return "redirect:/home";
		} else {
			// alert ~~~
			return "redirect:/login";
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {

		session.removeAttribute("userId");
		return "redirect:/home";
	}

}

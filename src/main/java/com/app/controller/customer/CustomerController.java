package com.app.controller.customer;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.dto.user.UserDto;
import com.app.service.user.UserService;
import com.app.utils.CommonCode;

@Controller
public class CustomerController {

	private static final Logger log = LogManager.getLogger(CustomerController.class);
	
	@Autowired
	UserService userService;
	
	@GetMapping("/registerCustomer")
	public String registerCustomer(HttpSession session) {
		
		if(session.getAttribute("login") == null) {
			return "redirect:/login";
		}
		
		return "registerCustomer";
	}
	
	@PostMapping("/registerCustomer")
	public String registerCustomer_process(UserDto userDto) {
		
		//userDto 안에 사용자가 입력한, id , name 들어있을것이다.
		userDto.setUserType(CommonCode.USER_USER_TYPE_CUSTOMER);
		int result = userService.saveUser(userDto);
		
		if(result > 0) {
			//정상 케이스
		} else {
			//뭔가 문제가 발생했다. -> 유효성 검증 -> 다른 페이지
		}
		
		return "registerCustomer";
	}
	
	@GetMapping("/customerList")
	public String customerList(Model model) {
		
		UserDto userDto = new UserDto();
		userDto.setUserType(CommonCode.USER_USER_TYPE_CUSTOMER);
		
		List<UserDto> userList = userService.getUserList(userDto);
		
		model.addAttribute("userList", userList);
		
		return "customerList";
	}
}





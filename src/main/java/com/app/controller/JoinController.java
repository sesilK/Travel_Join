package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.dto.join.JoinDto;
import com.app.service.user.join.JoinService;

@Controller
public class JoinController {

	@Autowired
	JoinService joinService;
	
	@GetMapping("/join")
	public String Join() {
		return "join";
	}
	
	@GetMapping("/join_view")
	public List<JoinDto> Join_View(Model model) {
		
		List<JoinDto> list = joinService.JoinViews();
		
		model.addAttribute("list",list);
		
		return list;
	}
}

package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


	@RequestMapping("/header")
	public String header() {
		return "header";
	}
	
	@RequestMapping("/test")
	public String test() {
		return "test";
	}



}


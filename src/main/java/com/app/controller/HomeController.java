package com.app.controller;

import com.app.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {


    @Autowired
    ChatService chatService;

    @RequestMapping("/")
    public String home() {
        return "forward:/main";
    }

    @RequestMapping("/main")
    public String main() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }



}


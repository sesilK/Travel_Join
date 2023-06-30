package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/review")
    public String reviewTest() {
        return "reviewtest";
    }
    

    @GetMapping("/board")
    public String board() {
        return "board";
    }


}


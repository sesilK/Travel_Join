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

    @GetMapping("/board")
    public String boardTest() {
        return "boardtest";
    }

    @GetMapping("/review")
    public String reviewTest() {
        return "reviewtest";
    }


}


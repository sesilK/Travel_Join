package com.app.controller;

import com.app.dto.ChatRoomDto;
import com.app.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


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

    @RequestMapping("/roomlist")
    public String roomList(Model model) {
        List<ChatRoomDto> rooms = chatService.getAllChatRooms();
        model.addAttribute("rooms", rooms);
        return "roomlist";
    }

    @RequestMapping("/enter/{roomId}")
    public String enterRoom(@PathVariable String roomId, Model model) {
        ChatRoomDto chatRoomDto = chatService.getChatRoomById(Integer.parseInt(roomId));

        System.out.println(chatRoomDto);
        System.out.println(roomId);

        model.addAttribute("room", chatRoomDto);
        return "chat";
    }

}


package com.app.controller;

import com.app.dto.ChatDto;
import com.app.dto.ChatRoomDto;
import com.app.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ChatController {

    @Autowired
    ChatService chatService;

    @RequestMapping("/chatlist")
    public String roomList(Model model) {
        List<ChatRoomDto> rooms = chatService.getAllChatRooms();
        model.addAttribute("rooms", rooms);
        for (ChatRoomDto room : rooms) {
            System.out.println(room.toString());
        }
        return "chatlist";
    }

    @RequestMapping("/chat/{roomId}")
    public String enterRoom(@PathVariable String roomId, Model model, HttpSession session, @RequestParam String username) {
        ChatRoomDto chatRoomDto = chatService.getChatRoomById(Integer.parseInt(roomId));
        List<ChatDto> chats = chatService.getAllChatByRoomId(Integer.parseInt(roomId));

        model.addAttribute("room", chatRoomDto);
        model.addAttribute("chats", chats);
        
        // 세션 주입
        session.setAttribute("username", username);
        return "chat";
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}

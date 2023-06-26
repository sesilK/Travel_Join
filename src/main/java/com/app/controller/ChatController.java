package com.app.controller;

import com.app.dto.ChatDto;
import com.app.dto.ChatRoomDto;
import com.app.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        int readUpdate = chatService.insert_all_chat_r_read_up_to_recent_by_user_id(username);
        System.out.println(username + "의 안읽은" + readUpdate + " 개 채팅 읽음처리 완료");
        ChatRoomDto chatRoomDto = chatService.getChatRoomById(Integer.parseInt(roomId));
        List<ChatDto> chats = chatService.getAllChatByRoomId(Integer.parseInt(roomId));

        model.addAttribute("room", chatRoomDto);
        model.addAttribute("chats", chats);
        
        // 세션 주입
        session.setAttribute("username", username);
        return "chat";
    }

    @PostMapping("/api/chat/read")
    @ResponseBody
    public List<ChatDto> readApi(@RequestParam String roomId) {
        List<ChatDto> chats = chatService.getAllChatByRoomId(Integer.parseInt(roomId));
        return  chats;
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}

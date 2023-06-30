package com.app.controller;

import com.app.dto.ChatDto;
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
    public String roomList(Model model, HttpSession session, @RequestParam String id) {
        session.setAttribute("userId", id);
        String userId = (String) session.getAttribute("userId"); // 세션에서 userID가져오기
        List<ChatDto> rooms = chatService.getAllChatRooms(userId); // 내 채팅방 목록 불러오기
        model.addAttribute("rooms", rooms); // model에 담아서 view에 뿌리기
        return "chatlist";
    }

    @RequestMapping("/chat/{roomId}")
    public String enterRoom(@PathVariable String roomId, Model model, HttpSession session) {

        ChatDto chatDto = new ChatDto(); // chatDto 선언
        chatDto.setRoomId(Integer.parseInt(roomId));
        chatDto.setSender(session.getAttribute("userId").toString()); // Dto에 member 채우기
        int readUpdate = chatService.update_chat_to_read_by_chatdto(chatDto); // roomId, userId로 채팅방 읽음처리 하기

        List<ChatDto> chats = chatService.getAllChatByRoomId(Integer.parseInt(roomId)); // roomId로 모든채팅 불러오기
        model.addAttribute("chats", chats); // model에 모든 채팅정보 담아보내기
        model.addAttribute("roomId", roomId); // model에 roomId 담아보내기
        System.out.println("check");
        return "chat";
    }

    @PostMapping("/api/chat/update")
    @ResponseBody
    public List<ChatDto> readApi(@RequestParam String roomId) {
        // 채팅방 id로 각 채팅의 안 읽은갯수 가져오기
        List<ChatDto> chats = chatService.select_unread_chat_by_plan_id(Integer.parseInt(roomId));
        return chats;
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}

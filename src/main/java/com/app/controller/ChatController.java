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
    public String roomList(Model model, HttpSession session, @RequestParam String id) {
        session.removeAttribute("userId");
        session.setAttribute("userId", id);
        String userId = (String) session.getAttribute("userId"); // 세션에서 userID가져오기
        List<ChatRoomDto> rooms = chatService.select_my_chat_info(userId); // 내 채팅방 목록 불러오기
        model.addAttribute("rooms", rooms); // model에 담아서 view에 뿌리기
        model.addAttribute("userId", userId);
        return "chatlist";
    }

    @RequestMapping("/chat/{roomId}")
    public String enterRoom(@PathVariable String roomId, Model model, HttpSession session) {
        // 모두 읽음처리에 필요한 userId와 planId 주입
        ChatDto tempDto = new ChatDto();
        tempDto.setUserId(session.getAttribute("userId").toString());
        tempDto.setPlanId(Integer.parseInt(roomId));
        int readStatus = chatService.readAllChatMessage(tempDto);

        List<ChatDto> chats = chatService.getAllChatByRoomId(Integer.parseInt(roomId)); // planId로 모든채팅 불러오기
        model.addAttribute("chats", chats); // model에 모든 채팅정보 담아보내기
        model.addAttribute("roomId", roomId); // model에 roomId 담아보내기
        return "chat";
    }

}

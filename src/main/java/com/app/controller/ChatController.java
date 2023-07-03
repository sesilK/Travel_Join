package com.app.controller;

import com.app.dto.ChatDto;
import com.app.dto.ChatRoomDto;
import com.app.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Controller
public class ChatController {

    @Autowired
    ChatService chatService;


    @RequestMapping("/chatlist")
    public String roomList(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("userId"); // 세션에서 userID가져오기
        List<ChatRoomDto> rooms = chatService.select_my_chat_info(userId); // 내 채팅방 목록 불러오기
        model.addAttribute("rooms", rooms); // model에 담아서 view에 뿌리기
        model.addAttribute("userId", userId);
        return "chatlist";
    }

    @RequestMapping("/chat/{roomId}")
    public String enterRoom(@PathVariable String roomId, Model model, HttpSession session, @RequestParam String chatId) {

        // 채팅방 모든 채팅 읽음처리에 필요한 ChatDto 생성
        ChatDto chatDto = new ChatDto();
        chatDto.setPlanId(Integer.parseInt(roomId));
        chatDto.setUserId(session.getAttribute("userId").toString());
        chatService.readAllChatMessage(chatDto);

        // 페이징에 필요한 ChatRoomDto 생성
        ChatRoomDto chatRoomDto = new ChatRoomDto();
        chatRoomDto.setChatId(Integer.parseInt(chatId));
        chatRoomDto.setPlanId(Integer.parseInt(roomId));
        chatRoomDto.setUserId(session.getAttribute("userId").toString());
        chatRoomDto.setChatCount(5);

        // 페이징된 채팅내역들 담겨있음
        List<ChatDto> list = chatService.select_chat_paging(chatRoomDto);
        Collections.reverse(list); // 배열 순서 거꾸로 뒤집기
//        List<ChatDto> chats = chatService.getAllChatByRoomId(Integer.parseInt(roomId)); // planId로 모든채팅 불러오기
        model.addAttribute("chats", list); // model에 모든 채팅정보 담아보내기
        model.addAttribute("roomId", roomId); // model에 roomId 담아보내기
        return "chat";
    }

    // 채팅 페이징
    @PostMapping("/api/chatpage")
    @ResponseBody
    public List<ChatDto> getChatPage(@RequestBody ChatRoomDto chatRoomDto) {

        // 페이징 할 갯수
        chatRoomDto.setChatCount(5);

        // 페이징된 채팅내역들 담겨있음
        List<ChatDto> list = chatService.select_chat_paging(chatRoomDto);

        return list;
    }

}

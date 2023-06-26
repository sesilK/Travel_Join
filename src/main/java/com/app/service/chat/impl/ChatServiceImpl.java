package com.app.service.chat.impl;

import com.app.dao.chat.ChatDao;
import com.app.dto.ChatDto;
import com.app.dto.ChatRoomDto;
import com.app.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatDao chatDao;


    @Override
    public int sendChatMessage(ChatDto chatDto) {
        int result = chatDao.insert_chat(chatDto);
        return result;
    }

    @Override
    public List<ChatRoomDto> getAllChatRooms() {
        List<ChatRoomDto> rooms = chatDao.select_all_chatroom_m();
        return rooms;
    }

    @Override
    public ChatRoomDto getChatRoomById(int roomId) {
        ChatRoomDto room = chatDao.select_chatroom_by_id(roomId);
        return room;
    }

    @Override
    public List<ChatDto> getAllChatByRoomId(int id) {
        List<ChatDto> chats = chatDao.select_all_chat_by_roomid(id);
        return chats;
    }
}

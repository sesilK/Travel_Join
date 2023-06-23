package com.app.service.chat;

import com.app.dto.ChatDto;
import com.app.dto.ChatRoomDto;

import java.util.List;

public interface ChatService {

    int createChatRoom(ChatDto chatDto);

    int joinChatRoom(ChatDto chatDto);

    int sendChatMessage(ChatDto chatDto);

    List<ChatRoomDto> getAllChatRooms();

    ChatRoomDto getChatRoomById(int id);
}

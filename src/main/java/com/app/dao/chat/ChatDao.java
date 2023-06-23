package com.app.dao.chat;

import com.app.dto.ChatDto;
import com.app.dto.ChatRoomDto;

import java.util.List;

public interface ChatDao {

    int insert_chatroom_m(ChatDto chatDto);

    int insert_chatroom_d(ChatDto chatDto);

    int insert_chat(ChatDto chatDto);

    int insert_chat_r(ChatDto chatDto);

    List<ChatRoomDto> select_all_chatroom_m();

    ChatRoomDto select_chatroom_by_id(int roomId);
}

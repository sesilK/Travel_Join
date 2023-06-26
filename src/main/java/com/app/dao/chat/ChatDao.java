package com.app.dao.chat;

import com.app.dto.ChatDto;
import com.app.dto.ChatRoomDto;

import java.util.List;

public interface ChatDao {

    int insert_chatroom_m(ChatDto chatDto);

    int insert_chatroom_d(ChatDto chatDto);

    int insert_chat(ChatDto chatDto);

    int insert_chat_r(ChatDto chatDto);

    List<ChatRoomDto> select_all_my_chatroom();

    ChatRoomDto select_chatroom_by_id(int roomId);

    List<ChatDto> select_all_chat_by_roomid(int roomId);

    int insert_all_chat_r_read_up_to_recent_by_user_id(String userId);
}

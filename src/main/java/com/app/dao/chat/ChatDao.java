package com.app.dao.chat;

import com.app.dto.ChatDto;
import com.app.dto.ChatRoomDto;

import java.util.List;

public interface ChatDao {


    int insert_chat(ChatDto chatDto);

    int insert_chat_read(ChatDto chatDto);

    List<ChatDto> select_all_chat_by_plan_id(int planId);

    int merge_chat_read(ChatDto chatDto);

    List<ChatDto> select_all_unread_count_by_plan_id(int planId);

    List<ChatRoomDto> select_my_chat_info(String userId);

}

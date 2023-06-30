package com.app.dao.chat;

import com.app.dto.ChatDto;

import java.util.List;

public interface ChatDao {


    int insert_chat(ChatDto chatDto);

    int insert_chat_r(ChatDto chatDto);

    List<ChatDto> select_all_chat_by_plan_id(int planId);

    List<ChatDto> select_all_last_chats_by_user_id(String userId);

    int update_chat_to_read_by_chatdto(ChatDto chatDto);

    List<ChatDto> select_unread_chat_by_plan_id(int planId);
}

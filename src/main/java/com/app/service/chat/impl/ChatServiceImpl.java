package com.app.service.chat.impl;

import com.app.dao.chat.ChatDao;
import com.app.dto.ChatDto;
import com.app.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatDao chatDao;


    /**
     * 전송된 채팅 테이블에 insert
     */
    @Override
    public int sendChatMessage(ChatDto chatDto) {
        int result = chatDao.insert_chat(chatDto);
        return result;
    }

    /**
     * userId가 참여중인 모든 모집방 id로 마지막 채팅정보 가져오기
     */
    @Override
    public List<ChatDto> getAllChatRooms(String userId) {
        List<ChatDto> chats = chatDao.select_all_last_chats_by_user_id(userId);
        return chats;
    }

    /**
     * 채팅방 고유번호로 모든 채팅정보 가져오기
     */
    @Override
    public List<ChatDto> getAllChatByRoomId(int id) {
        List<ChatDto> chats = chatDao.select_all_chat_by_plan_id(id);
        return chats;
    }

    /**
     * userId의 마지막 확인 채팅부터 최신 채팅까지 읽음처리 (채팅방 입장시)
     */
    @Override
    public int update_chat_to_read_by_chatdto(ChatDto chatDto) {
        int result = chatDao.update_chat_to_read_by_chatdto(chatDto);
        return result;
    }

    /**
     * 채팅방 id로 채팅별로 안 읽은 갯수가져오기
     */
    @Override
    public List<ChatDto> select_unread_chat_by_plan_id(int plan_id) {
        List<ChatDto> chats = chatDao.select_unread_chat_by_plan_id(plan_id);
        return chats;
    }
}

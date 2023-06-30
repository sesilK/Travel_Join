package com.app.dao.chat.impl;

import com.app.dao.chat.ChatDao;
import com.app.dto.ChatDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatDaoImpl implements ChatDao {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;


    /**
     * insert to chat 테이블
     */
    @Override
    public int insert_chat(ChatDto chatDto) {
        int result = sqlSessionTemplate.insert("insert_chat", chatDto);
        return result;
    }

    /**
     * insert to chat_r 테이블
     */
    @Override
    public int insert_chat_r(ChatDto chatDto) {
        int result = sqlSessionTemplate.insert("insert_chat_r", chatDto);
        return result;
    }

    /**
     * plan_id로 모든 채팅내역 가져오기
     */
    @Override
    public List<ChatDto> select_all_chat_by_plan_id(int planId) {
        List<ChatDto> list = sqlSessionTemplate.selectList("select_all_chat_by_plan_id", planId);
        return list;
    }

    /**
     * userId가 참여중인 모든 모집방 id로 마지막 채팅정보 가져오기
     */
    @Override
    public List<ChatDto> select_all_last_chats_by_user_id(String userId) {
        List<ChatDto> list = sqlSessionTemplate.selectList("select_all_last_chats_by_user_id", userId);
        return list;
    }

    /**
     * userId의 마지막 확인 채팅부터 최신 채팅까지 읽음처리 (채팅방 입장시)
     */
    @Override
    public int update_chat_to_read_by_chatdto(ChatDto chatDto) {
        int result = sqlSessionTemplate.insert("update_chat_to_read_by_chatdto", chatDto);
        return result;
    }

    @Override
    public List<ChatDto> select_unread_chat_by_plan_id(int planId) {
        List<ChatDto> list = sqlSessionTemplate.selectList("select_unread_chat_by_plan_id", planId);
        return list;
    }
}

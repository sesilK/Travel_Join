package com.app.dao.chat.impl;

import com.app.dao.chat.ChatDao;
import com.app.dto.ChatDto;
import com.app.dto.ChatRoomDto;
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
     * insert to chat_read 테이블
     */
    @Override
    public int insert_chat_read(ChatDto chatDto) {
        int result = sqlSessionTemplate.insert("insert_chat_read", chatDto);
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

    /** plan_id와 user_id로 해당 채팅방 모두 읽음처리 */
    @Override
    public int merge_chat_read(ChatDto chatDto) {
        int result = sqlSessionTemplate.insert("merge_chat_read", chatDto);
        return result;
    }

    /** plan_id로 안 읽은 채팅갯수 가져오기 */
    @Override
    public List<ChatDto> select_all_unread_count_by_plan_id(int planId) {
        List<ChatDto> list = sqlSessionTemplate.selectList("select_all_unread_count_by_plan_id", planId);
        return list;
    }

    /** user_id로 참여중인 채팅리스트 정보 가져오기 */
    @Override
    public List<ChatRoomDto> select_my_chat_info(String userId) {
        List<ChatRoomDto> list = sqlSessionTemplate.selectList("select_my_chat_info", userId);
        return list;
    }

    @Override
    public List<ChatDto> select_chat_paging(ChatRoomDto chatRoomDto) {
        List<ChatDto> list = sqlSessionTemplate.selectList("select_chat_paging", chatRoomDto);
        return list;
    }

    @Override
    public int check_chatroom_member(ChatDto chatDto) {
        int result = sqlSessionTemplate.selectOne("check_chatroom_member", chatDto);
        return result;
    }

    @Override
    public int get_last_chat_id(int planId) {
        int chat_id = sqlSessionTemplate.selectOne("get_last_chat_id", planId);
        return chat_id;
    }
}

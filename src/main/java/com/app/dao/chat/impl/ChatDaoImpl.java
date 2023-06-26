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

    @Override
    public int insert_chatroom_m(ChatDto chatDto) {
        int result = sqlSessionTemplate.insert("insert_chatroom_m", chatDto);
        return result;
    }

    @Override
    public int insert_chatroom_d(ChatDto chatDto) {
        int result = sqlSessionTemplate.insert("insert_chatroom_d", chatDto);
        return result;
    }

    @Override
    public int insert_chat(ChatDto chatDto) {
        int result = sqlSessionTemplate.insert("insert_chat", chatDto);
        return result;
    }

    @Override
    public int insert_chat_r(ChatDto chatDto) {
        int result = sqlSessionTemplate.insert("insert_chat_r", chatDto);
        return result;
    }

    @Override
    public List<ChatRoomDto> select_all_chatroom_m() {
        List<ChatRoomDto> list = sqlSessionTemplate.selectList("select_all_chatroom");
        return list;
    }

    @Override
    public ChatRoomDto select_chatroom_by_id(int roomId) {
        ChatRoomDto chatRoomDto = sqlSessionTemplate.selectOne("select_chatroom_by_id", roomId);
        return chatRoomDto;
    }

    @Override
    public List<ChatDto> select_all_chat_by_roomid(int roomId) {
        List<ChatDto> list = sqlSessionTemplate.selectList("select_all_chat_by_roomid", roomId);
        return list;
    }
}

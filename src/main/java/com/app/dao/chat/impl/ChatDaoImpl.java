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

    /** insert to chatroom_m 테이블 */
    @Override
    public int insert_chatroom_m(ChatDto chatDto) {
        int result = sqlSessionTemplate.insert("insert_chatroom_m", chatDto);
        return result;
    }

    /** insert to chatroom_d 테이블 */
    @Override
    public int insert_chatroom_d(ChatDto chatDto) {
        int result = sqlSessionTemplate.insert("insert_chatroom_d", chatDto);
        return result;
    }

    /** insert to chat 테이블 */
    @Override
    public int insert_chat(ChatDto chatDto) {
        int result = sqlSessionTemplate.insert("insert_chat", chatDto);
        return result;
    }

    /** insert to chat_r 테이블 */
    @Override
    public int insert_chat_r(ChatDto chatDto) {
        int result = sqlSessionTemplate.insert("insert_chat_r", chatDto);
        return result;
    }

    /** 내가 속해있는 채팅방 정보와 마지막 채팅내용 가져오기 */
    @Override
    public List<ChatRoomDto> select_all_my_chatroom() {
        List<ChatRoomDto> list = sqlSessionTemplate.selectList("select_all_my_chatroom");
        return list;
    }

    /** chatroom_m 테이블에서 room_id 로 채팅방정보 불러오기  */
    @Override
    public ChatRoomDto select_chatroom_by_id(int roomId) {
        ChatRoomDto chatRoomDto = sqlSessionTemplate.selectOne("select_chatroom_by_id", roomId);
        return chatRoomDto;
    }

    /** chat 테이블에서 room_id 로 모든 채팅정보 불러오기 */
    @Override
    public List<ChatDto> select_all_chat_by_roomid(int roomId) {
        List<ChatDto> list = sqlSessionTemplate.selectList("select_all_chat_by_roomid", roomId);
        return list;
    }

    /** userId의 마지막 확인 채팅부터 최신 채팅까지 읽음처리 (채팅방 입장시) */
    @Override
    public int insert_all_chat_r_read_up_to_recent_by_user_id(String userId) {
        int result = sqlSessionTemplate.insert("insert_all_chat_r_read_up_to_recent_by_user_id", userId);
        return result;
    }
}

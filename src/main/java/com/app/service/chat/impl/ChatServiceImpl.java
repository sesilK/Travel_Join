package com.app.service.chat.impl;

import com.app.dao.chat.ChatDao;
import com.app.dto.ChatDto;
import com.app.dto.ChatRoomDto;
import com.app.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatDao chatDao;


    /** 전송된 채팅 테이블에 insert */
    @Override
    public int sendChatMessage(ChatDto chatDto) {
        int result = chatDao.insert_chat(chatDto);
        return result;
    }

    /** 내가 속해있는 채팅방 정보와 마지막 채팅내용 가져오기 */
    @Override
    public List<ChatRoomDto> getAllChatRooms() {
        List<ChatRoomDto> rooms = chatDao.select_all_my_chatroom();
        return rooms;
    }

    /** 채팅방 고유번호로 채팅방 정보 가져오기 */
    @Override
    public ChatRoomDto getChatRoomById(int roomId) {
        ChatRoomDto room = chatDao.select_chatroom_by_id(roomId);
        return room;
    }

    /** 채팅방 고유번호로 모든 채팅정보 가져오기 */
    @Override
    public List<ChatDto> getAllChatByRoomId(int id) {
        List<ChatDto> chats = chatDao.select_all_chat_by_roomid(id);
        return chats;
    }

    /** userId의 마지막 확인 채팅부터 최신 채팅까지 읽음처리 (채팅방 입장시) */
    @Override
    public int insert_all_chat_r_read_up_to_recent_by_user_id(String userId) {
        int result = chatDao.insert_all_chat_r_read_up_to_recent_by_user_id(userId);
        return result;
    }
}

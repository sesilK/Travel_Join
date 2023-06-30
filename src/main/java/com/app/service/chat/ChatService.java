package com.app.service.chat;

import com.app.dto.ChatDto;

import java.util.List;

public interface ChatService {

    /**
     * 전송된 채팅을 테이블에 insert
     */
    int sendChatMessage(ChatDto chatDto);

    /**
     * 내가 속해있는 채팅방 정보와 마지막 채팅내용 가져오기
     */
    List<ChatDto> getAllChatRooms(String userId);

    /**
     * 채팅방 고유번호로 모든 채팅정보 가져오기
     */
    List<ChatDto> getAllChatByRoomId(int id);

    /**
     * userId의 마지막 확인 채팅부터 최신 채팅까지 읽음처리 (채팅방 입장시)
     */
    int update_chat_to_read_by_chatdto(ChatDto chatDto);

    /**
     * 채팅방 id로 채팅별로 안 읽은 갯수가져오기
     */
    List<ChatDto> select_unread_chat_by_plan_id(int plan_id);
}

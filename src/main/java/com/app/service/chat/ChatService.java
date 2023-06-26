package com.app.service.chat;

import com.app.dto.ChatDto;
import com.app.dto.ChatRoomDto;

import java.util.List;

public interface ChatService {

    /** 전송된 채팅 테이블에 insert */
    int sendChatMessage(ChatDto chatDto);

    /** 내가 속해있는 채팅방 정보와 마지막 채팅내용 가져오기 */
    List<ChatRoomDto> getAllChatRooms();

    /** 채팅방 고유번호로 채팅방 정보 가져오기 */
    ChatRoomDto getChatRoomById(int id);

    /** 채팅방 고유번호로 모든 채팅정보 가져오기 */
    List<ChatDto> getAllChatByRoomId(int id);

    /** userId의 마지막 확인 채팅부터 최신 채팅까지 읽음처리 (채팅방 입장시) */
    int insert_all_chat_r_read_up_to_recent_by_user_id(String userId);
}

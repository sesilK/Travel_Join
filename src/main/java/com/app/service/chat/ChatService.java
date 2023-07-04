package com.app.service.chat;

import com.app.dto.ChatDto;
import com.app.dto.ChatRoomDto;

import java.util.List;

public interface ChatService {

    /**
     * 전송된 채팅을 테이블에 insert
     */
    int sendChatMessage(ChatDto chatDto);

    /** 전송한 메세지 읽음처리 */
    int readChatMessage(ChatDto chatDto);

    /** plan_id와 user_id로 해당 채팅방 모두 읽음처리 */
    int readAllChatMessage(ChatDto chatDto);

    /** 채팅방 번호로 모든 채팅정보 가져오기 */
    List<ChatDto> getAllChatByRoomId(int id);

    /** planId로 unReadCount 모두 가져오기 */
    List<ChatDto> select_all_unread_count_by_plan_id(ChatDto message);

    /** userId로 채팅방 리스트에 띄울 정보들 가져오기 */
    List<ChatRoomDto> select_my_chat_info(String userId);

    List<ChatDto> select_chat_paging(ChatRoomDto chatRoomDto);

    int check_chatroom_member(ChatDto chatDto);

    int get_last_chat_id(int planId);

}

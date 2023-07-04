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


    /**
     * 전송된 채팅 테이블에 insert
     */
    @Override
    public int sendChatMessage(ChatDto chatDto) {
        int result = chatDao.insert_chat(chatDto);
        return result;
    }

    /** 단건 채팅 읽음처리 */
    @Override
    public int readChatMessage(ChatDto chatDto) {
        int result = chatDao.insert_chat_read(chatDto);
        return result;
    }

    /** plan_id와 user_id로 해당 채팅방 모두 읽음처리 */
    @Override
    public int readAllChatMessage(ChatDto chatDto) {
        int result = chatDao.merge_chat_read(chatDto);
        return result;
    }

    /**
     * 채팅방 고유번호로 모든 채팅정보 가져오기
     */
    @Override
    public List<ChatDto> getAllChatByRoomId(int id) {
        List<ChatDto> chats = chatDao.select_all_chat_by_plan_id(id);
        return chats;
    }

    /** plan_id로 안 읽은 채팅갯수 가져오기 chat_id : un_read 형태 */
    @Override
    public List<ChatDto> select_all_unread_count_by_plan_id(ChatDto message) {

        List<ChatDto> chats = null;
        do { // unReadCount 조회한 갯수가 클라이언트가 보낸시점의 메세지 갯수보다 작으면 DB입출력이 끝나지 않은 시점으로 판단하고 unReadCount를 다시 조회
            chats = chatDao.select_all_unread_count_by_plan_id(message.getPlanId());
        } while (chats.size() < message.getUnRead());

        return chats;
    }

    /** userId로 채팅방 리스트에 띄울 정보들 가져오기 */
    @Override
    public List<ChatRoomDto> select_my_chat_info(String userId) {
        List<ChatRoomDto> rooms = chatDao.select_my_chat_info(userId);
        return rooms;
    }

    /** 채팅내역 페이징처리 */
    @Override
    public List<ChatDto> select_chat_paging(ChatRoomDto chatRoomDto) {
        List<ChatDto> list = chatDao.select_chat_paging(chatRoomDto);
        return list;
    }

    /** 채팅방 멤버가 맞는지 체크 */
    @Override
    public int check_chatroom_member(ChatDto chatDto) {
        int result = chatDao.check_chatroom_member(chatDto);
        return result;
    }

    /** 마지막 채팅id 가져오기 */
    @Override
    public int get_last_chat_id(int planId) {
        int chatId = chatDao.get_last_chat_id(planId);
        return chatId;
    }


}

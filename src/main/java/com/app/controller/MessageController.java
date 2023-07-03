package com.app.controller;


import com.app.dto.ChatDto;
import com.app.dto.ChatRoomDto;
import com.app.service.chat.ChatService;
import com.app.utils.TimeStampUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final String subScribeURL = "/sub/channel/"; // 구독 링크

    @Autowired
    ChatService chatService;

    /*
        /sub/channel/12345      - 구독(channelId:12345)
        /pub/send              - 메시지 발행
    */


    /**
     * 전송된 채팅처리
     */
    @MessageMapping("/send")
    public void message(ChatDto message) {
        message.setTime(TimeStampUtil.sysDate()); // 현재시간 Dto에 넣기
        chatService.sendChatMessage(message); // 전송된 채팅 db에 저장
        simpMessageSendingOperations.convertAndSend(subScribeURL + message.getPlanId(), message); // 가공된 메세지 다시 구독자들에게 메세지 보냄 (채팅방 멤버들)
    }

    /**
     * 채팅 읽음처리
     */
    @MessageMapping("/read")
    public void read(ChatDto message) {
        chatService.readChatMessage(message);
        message.setType("read");
    }

    /**
     * 안 읽은 모든채팅 읽음처리
     */
    @MessageMapping("/readAll")
    public void readAll(ChatDto message) {
        chatService.readAllChatMessage(message);
    }

    /**
     * 채팅방의 모든 채팅에 대한 안 읽은갯수 보내기
     */
    @MessageMapping("/getUnread")
    public void getUnreadCount(ChatDto message) {
        message.setType("unread"); // 메세지 타입 unread로 지정
        List<ChatDto> counts = chatService.select_all_unread_count_by_plan_id(message); // {chatId, unRead} 담긴 ChatDto 리스트
        message.setData(counts); // 메세지 Object에 리스트 넣어주기
        simpMessageSendingOperations.convertAndSend(subScribeURL + message.getPlanId(), message);
    }

    @MessageMapping("/get_list")
    public void getChatListInfo(ChatDto message) {
        message.setType("info");
        List<ChatRoomDto> rooms = chatService.select_my_chat_info(message.getUserId()); // 내 채팅방 목록 불러오기
        message.setData(rooms);
        simpMessageSendingOperations.convertAndSend(subScribeURL + message.getPlanId(), message);
    }

    @MessageMapping("/chat_paging")
    public void chatPaging(ChatDto message) {
        ChatRoomDto chatRoomDto = new ChatRoomDto();
        chatRoomDto.setChatCount(5); // 페이징 갯수
        chatRoomDto.setPlanId(message.getPlanId()); // 불러올 채팅방 id
        chatRoomDto.setChatId(message.getChatId()); // 불러올 기준 chat_id
        List<ChatDto> list = chatService.select_chat_paging(chatRoomDto);
        message.setData(list);
        message.setType("paging");
        simpMessageSendingOperations.convertAndSend(subScribeURL + message.getPlanId(), message);
    }

}
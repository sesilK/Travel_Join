package com.app.controller;


import com.app.dto.ChatDto;
import com.app.service.chat.ChatService;
import com.app.utils.TimeStampUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

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


    /** 모든 타입 메세지 핸들러 */
    @MessageMapping("/send")
    public void message(ChatDto message) {
        message.setTimeStamp(TimeStampUtil.sysDate()); // 현재시간 Dto에 넣기
        chatService.sendChatMessage(message); // 전송된 채팅 db에 저장
        simpMessageSendingOperations.convertAndSend(subScribeURL + message.getRoomId(), message); // 가공된 메세지 다시 구독자들에게 메세지 보냄 (채팅방 멤버들)
    }

    /** 채팅방 신규가입 핸들러 */
    @MessageMapping("/join")
    public void chatJoin(ChatDto message) {
        // chatroom_d 테이블에 새로운 유저 추가하는 로직 구현예정 06.26
        message.setContent(message.getSender() + " 님이 입장 했습니다.");
    }

    /** 채팅방 탈퇴 핸들러 */
    @MessageMapping("/drop")
    public void chatDrop(ChatDto message) {
        // chatroom_d 테이블에 탈퇴한 유저 삭제하는 로직 구현예정 06.26
        message.setContent(message.getSender() + " 님이 퇴장 했습니다.");
    }

    /** 채팅방 입장 핸들러 */
    @MessageMapping("/in")
    public void chatIn(ChatDto message) {
        message.setTimeStamp(TimeStampUtil.sysDate()); // 현재시간 Dto에 넣기

        int readUpdate = chatService.insert_all_chat_r_read_up_to_recent_by_user_id(message.getSender()); // db에 읽음처리

        // ( 서버쪽에서 처리하는게 보안이 더 좋을거라고 판단 )
        message.setContent(null); // 채팅내용 null 로 설정
        message.setType("in"); // 메세지타입 in 으로 설정

        chatService.sendChatMessage(message); // 전송된 채팅 db에 저장
        simpMessageSendingOperations.convertAndSend(subScribeURL + message.getRoomId(), message); // 가공된 메세지 다시 구독자들에게 메세지 보냄 (채팅방 멤버들)
    }

    /** 채팅방 퇴장 핸들러 */
    @MessageMapping("/out")
    public void chatOut(ChatDto message) {
        message.setTimeStamp(TimeStampUtil.sysDate()); // 현재시간 Dto에 넣기

        // ( 서버쪽에서 처리하는게 보안이 더 좋을거라고 판단 )
        message.setContent(null); // 채팅내용 null 로 설정
        message.setType("out"); // 메세지타입 out 으로 설정

        chatService.sendChatMessage(message); // 전송된 채팅 db에 저장
        simpMessageSendingOperations.convertAndSend(subScribeURL + message.getRoomId(), message); // 가공된 메세지 다시 구독자들에게 메세지 보냄 (채팅방 멤버들)
    }

}
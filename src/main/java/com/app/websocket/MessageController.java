package com.app.websocket;


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

    @Autowired
    ChatService chatService;

    /*
        /sub/channel/12345      - 구독(channelId:12345)
        /pub/send              - 메시지 발행
    */

    @MessageMapping("/send")
    public void message(ChatDto message) {
        message.setTimeStamp(TimeStampUtil.sysDate());
        System.out.println(message.toString());
        chatService.sendChatMessage(message);
        simpMessageSendingOperations.convertAndSend("/sub/channel/" + message.getRoomId(), message);
    }
}
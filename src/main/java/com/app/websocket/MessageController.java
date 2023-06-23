package com.app.websocket;

import com.app.dto.ChatDto;
import com.app.utils.TimeStampUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations simpMessageSendingOperations;


    /*
        /sub/channel/12345      - 구독(channelId:12345)
        /pub/send              - 메시지 발행
    */

    @MessageMapping("/send")
    public void message(ChatDto message) {
        message.setTimeStamp(TimeStampUtil.sysDate());
        simpMessageSendingOperations.convertAndSend("/sub/channel/" + message.getRoomId(), message);
    }
}
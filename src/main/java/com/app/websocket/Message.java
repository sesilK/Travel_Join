package com.app.websocket;

import lombok.*;
import org.springframework.web.socket.WebSocketMessage;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message {
    private String type;
    private String sender;
    private String channelId;
    private Object data;

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void newConnect() {
        this.type = "new";
    }

    public void closeConnect() {
        this.type = "close";
    }

}
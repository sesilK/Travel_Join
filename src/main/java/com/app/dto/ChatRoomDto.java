package com.app.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ChatRoomDto {
    private int roomId;
    private String master;
    private String member;
    private String content;
    private int status;
}

package com.app.dto;

import lombok.Data;

@Data
public class ChatRoomDto {
    private int roomId;
    private String master;
    private String member;
    private int status;
}

package com.app.dto;

import lombok.Data;

@Data
public class ChatRoomDto {
    private int planId;
    private int chatId;
    private String userId;
    private String title;
    private String imageId;
    private String sender;
    private String content;
    private String time;
    private int chatCount;
}

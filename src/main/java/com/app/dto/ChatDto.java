package com.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class ChatDto {

    private int chatId;
    private int planId;
    private String userId;
    private String content;
    private String time;
    private String type;
    private int unRead;
    private Object data;
    private String title;
}
package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatDto {

    private int chatId;
    private int roomId;
    private String sender;
    private String content;
    private String type;
    private String timeStamp;
    private int unRead;

}
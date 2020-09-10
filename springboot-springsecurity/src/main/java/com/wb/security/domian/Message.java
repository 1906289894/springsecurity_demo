package com.wb.security.domian;

import lombok.Data;

@Data
public class Message {
    //信息标题
    private String title;
    //信息主体
    private String content;
    //额外信息
    private String etraInfo;

    public Message(String title, String content, String etraInfo) {
    }
}

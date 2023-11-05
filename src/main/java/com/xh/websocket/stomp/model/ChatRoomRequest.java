package com.xh.websocket.stomp.model;

import lombok.Data;

/**
 * @author H.Yang
 * @date 2023/11/5
 */
@Data
public class ChatRoomRequest {
    /*发送者名字*/
    private String name;
    /*聊天内容*/
    private String chatValue;
    /*单聊里接收者的ID*/
    private String userId;
}

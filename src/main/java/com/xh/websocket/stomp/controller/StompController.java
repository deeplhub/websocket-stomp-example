package com.xh.websocket.stomp.controller;

import com.xh.websocket.stomp.model.ChatRoomRequest;
import com.xh.websocket.stomp.model.ChatRoomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * @author H.Yang
 * @date 2023/11/5
 */
@Controller
public class StompController {

    /**
     * 实现向浏览器发送消息的功能
     */
    @Autowired
    private SimpMessagingTemplate template;


    /**
     * 消息群发，接受发送至自massRequest的请求
     *
     * @param chatRoomRequest
     * @return
     */
    @MessageMapping("/massRequest")
    @SendTo("/topic/message")
    //SendTo 发送至 Broker 下的指定订阅路径/topic,Broker再根据/message发送消息到订阅了/topic/message的用户处
    public ChatRoomResponse mass(ChatRoomRequest chatRoomRequest) {
        System.out.println("name = " + chatRoomRequest.getName());
        System.out.println("chatValue = " + chatRoomRequest.getChatValue());
        ChatRoomResponse response = new ChatRoomResponse();
        response.setName(chatRoomRequest.getName());
        response.setChatValue(chatRoomRequest.getChatValue());

        // 这里是有return，如果不写@SendTo默认和/topic/message一样
        return response;
    }

    /*单独聊天，接受发送至自aloneRequest的请求*/
    @MessageMapping("/aloneRequest")
    public ChatRoomResponse alone(ChatRoomRequest chatRoomRequest) {
        System.out.println("SendToUser = " + chatRoomRequest.getUserId()
                + " FromName = " + chatRoomRequest.getName()
                + " ChatValue = " + chatRoomRequest.getChatValue());
        ChatRoomResponse response = new ChatRoomResponse();
        response.setName(chatRoomRequest.getName());
        response.setChatValue(chatRoomRequest.getChatValue());

        //会发送到订阅了 /user/{用户的id}/alone 的用户处
        template.convertAndSendToUser(chatRoomRequest.getUserId() + "", "/alone", response);
        return response;
    }
}

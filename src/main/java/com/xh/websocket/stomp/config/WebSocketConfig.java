package com.xh.websocket.stomp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * websocket 配置类
 *
 * @author H.Yang
 * @date 2023/11/5
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 注册stomp端点，主要是起到连接作用
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册STOMP协议的节点(endpoint),并映射指定的url
        //添加一个访问端点“/endpointMark”,客户端打开双通道时需要的url,
        //允许所有的域名跨域访问，指定使用SockJS协议。
        registry.addEndpoint("/endpointMark")  //端点名称
                //.setHandshakeHandler() 握手处理，主要是连接的时候认证获取其他数据验证等
                //.addInterceptors() 拦截处理，和http拦截类似
                .setAllowedOrigins("*") //跨域
                .withSockJS(); //使用sockJS
    }


    /**
     * 配置消息代理
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 定义客户端访问服务端消息接口时的前缀
        //registry.setApplicationDestinationPrefixes("/app");

        //这里使用的是内存模式，生产环境可以使用rabbitmq或者其他mq。
        //这里注册两个，主要是目的是将广播和队列分开。
        //registry.enableStompBrokerRelay().setRelayHost().setRelayPort() 其他方式
        registry.enableSimpleBroker("/topic", "/queue");

        // 客户端给服务端发消息的地址前缀
        registry.setUserDestinationPrefix("/queue");


        //   Use this for enabling a Full featured broker like RabbitMQ
        /*
        registry.enableStompBrokerRelay("/topic")
                .setRelayHost("localhost")
                .setRelayPort(61613)
                .setClientLogin("guest")
                .setClientPasscode("guest");
        */
    }

}


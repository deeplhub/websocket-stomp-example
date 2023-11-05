@EnableWebSocketMessageBroker：用于开启stomp协议，这样就能支持@MessageMapping注解，类似于@requestMapping一样，同时前端可以使用Stomp客户端进行通讯；

WebSocketMessageBrokerConfigurer接口：实现了其中的两个方法：

- registerStompEndpoints实现：

  - 注册一个websocket端点，客户端将使用它连接到我们的websocket服务器。

  - withSockJS()是用来为不支持websocket的浏览器启用后备选项，使用了SockJS。
  - 
  - 方法名中的STOMP是来自Spring框架STOMP实现。 STOMP代表简单文本导向的消息传递协议。它是一种消息传递协议，用于定义数据交换的格式和规则。为啥我们需要这个东西？因为WebSocket只是一种通信协议。它没有定义诸如以下内容：如何仅向订阅特定主题的用户发送消息，或者如何向特定用户发送消息。我们需要STOMP来实现这些功能


- configureMessageBroker实现：主要用来设置客户端订阅消息的路径(可以多个)、点对点订阅路径前缀的设置、访问服务端@MessageMapping接口的前缀路径、心跳设置等；

  - 第一行定义了以“/app”开头的消息应该路由到消息处理方法（之后会定义这个方法）。

  - 第二行定义了以“/topic”开头的消息应该路由到消息代理。消息代理向订阅特定主题的所有连接客户端广播消息


**参考**

* https://blog.51cto.com/u_16213578/7742286
* https://blog.csdn.net/weixin_39724194/article/details/127320311
* https://blog.csdn.net/weixin_44483838/article/details/130569222

package com.college.bus.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class LiveLocationWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Received from client: " + message.getPayload());

        // Echo the message or handle it however you want
        session.sendMessage(new TextMessage("Received: " + message.getPayload()));
    }
}


package com.college.bus.websocket;
/*
 * import org.springframework.web.socket.TextMessage; import
 * org.springframework.web.socket.WebSocketSession; import
 * org.springframework.web.socket.handler.TextWebSocketHandler;
 * 
 * public class LiveLocationWebSocketHandler extends TextWebSocketHandler {
 * 
 * @Override public void handleTextMessage(WebSocketSession session, TextMessage
 * message) throws Exception { System.out.println("Received from client: " +
 * message.getPayload());
 * 
 * // Echo the message or handle it however you want session.sendMessage(new
 * TextMessage("Received: " + message.getPayload())); } }
 */




import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;

public class LiveLocationWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Map<String, String> params = getQueryParams(session);
        String token = params.get("token");

        if (token == null || token.isEmpty()) {
            System.out.println("❌ No token provided. Closing session.");
            session.close();
            return;
        }

        System.out.println("✅ WebSocket connection with token: " + token);
        // TODO: Add actual token validation here in the next step
    }

    private Map<String, String> getQueryParams(WebSocketSession session) {
        String query = session.getUri().getQuery();
        return query != null ? Map.of(query.split("=")[0], query.split("=")[1]) : Map.of();
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("📨 Message from client: " + message.getPayload());
    }
}

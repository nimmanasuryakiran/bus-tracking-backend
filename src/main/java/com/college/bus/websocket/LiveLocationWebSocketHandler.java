/*
 * package com.college.bus.websocket;
 * 
 * 
 * import org.springframework.web.socket.TextMessage; import
 * org.springframework.web.socket.WebSocketSession; import
 * org.springframework.web.socket.handler.TextWebSocketHandler;
 * 
 * import java.util.Map;
 * 
 * public class LiveLocationWebSocketHandler extends TextWebSocketHandler {
 * 
 * @Override public void afterConnectionEstablished(WebSocketSession session)
 * throws Exception { Map<String, String> params = getQueryParams(session);
 * String token = params.get("token");
 * 
 * if (token == null || token.isEmpty()) {
 * System.out.println("❌ No token provided. Closing session."); session.close();
 * return; }
 * 
 * System.out.println("✅ WebSocket connection with token: " + token); // TODO:
 * Add actual token validation here in the next step }
 * 
 * private Map<String, String> getQueryParams(WebSocketSession session) { String
 * query = session.getUri().getQuery(); return query != null ?
 * Map.of(query.split("=")[0], query.split("=")[1]) : Map.of(); }
 * 
 * @Override public void handleTextMessage(WebSocketSession session, TextMessage
 * message) throws Exception { System.out.println("📨 Message from client: " +
 * message.getPayload()); } }
 */



package com.college.bus.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LiveLocationWebSocketHandler extends TextWebSocketHandler {

    // Store all connected sessions
    private static final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Map<String, String> params = getQueryParams(session);
        String token = params.get("token");

        if (token == null || token.isEmpty()) {
            System.out.println("❌ No token provided. Closing session.");
            session.close();
            return;
        }

        System.out.println("✅ WebSocket connected: " + session.getId());
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        System.out.println("🔌 WebSocket disconnected: " + session.getId());
        sessions.remove(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("📨 From driver: " + message.getPayload());

        // Broadcast this message to all sessions (students)
        synchronized (sessions) {
            for (WebSocketSession s : sessions) {
                if (s.isOpen()) {
                    s.sendMessage(new TextMessage(message.getPayload()));
                }
            }
        }
    }

    private Map<String, String> getQueryParams(WebSocketSession session) {
        String query = session.getUri().getQuery();
        return query != null ? Map.of(query.split("=")[0], query.split("=")[1]) : Map.of();
    }
}

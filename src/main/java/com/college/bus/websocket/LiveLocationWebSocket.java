/*
 * package com.college.bus.websocket;
 * 
 * 
 * import javax.websocket.OnClose; import javax.websocket.OnMessage; import
 * javax.websocket.OnOpen; import javax.websocket.Session; import
 * javax.websocket.server.ServerEndpoint; import java.io.IOException; import
 * java.util.Collections; import java.util.HashSet; import java.util.Set;
 * 
 * @ServerEndpoint("/live-location") public class LiveLocationWebSocket {
 * 
 * private static final Set<Session> sessions = Collections.synchronizedSet(new
 * HashSet<>());
 * 
 * @OnOpen public void onOpen(Session session) { sessions.add(session);
 * System.out.println("✅ WebSocket opened: " + session.getId()); }
 * 
 * @OnMessage public void onMessage(String message, Session session) throws
 * IOException { System.out.println("📡 Location message received: " + message);
 * 
 * // Broadcast the location to all connected clients synchronized (sessions) {
 * for (Session s : sessions) { if (s.isOpen() &&
 * !s.getId().equals(session.getId())) { s.getBasicRemote().sendText(message); }
 * } } }
 * 
 * @OnClose public void onClose(Session session) { sessions.remove(session);
 * System.out.println("❌ WebSocket closed: " + session.getId()); } }
 */
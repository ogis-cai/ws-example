package com.example;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by one on 17/04/16.
 */
@Component
public class SessionManager {
    private final ConcurrentHashMap<String, SessionDto> sessionMap;

    @EventListener
    public void handleSessionConnectedEvent(SessionConnectedEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = sha.getSessionId();
        this.sessionMap.put(sessionId, new SessionDto(sessionId, event.getTimestamp()));
    }

    @EventListener
    public void handleSessionDisconnectedEvent(SessionDisconnectEvent event) {
        this.sessionMap.remove(event.getSessionId());
    }

    public SessionManager() {
        this.sessionMap = new ConcurrentHashMap<>();
    }

    public Collection<SessionDto> allSessions() {
        return sessionMap.values();
    }
}

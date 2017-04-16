package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.util.Map;

/**
 * Created by one on 17/04/16.
 */
@Component
public class StompConnectEvent implements ApplicationListener<SessionConnectedEvent> {
    @Autowired
    SessionManager sessionManager;

    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = sha.getSessionId();
        sessionManager.setSession(new SessionDto(sessionId, event.getTimestamp()));
    }
}

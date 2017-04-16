package com.example;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by one on 17/04/16.
 */
@Component
public class SessionManager {
    private final ConcurrentHashMap<String, SessionDto> sessionMap;

    public SessionManager() {
        this.sessionMap = new ConcurrentHashMap<>();
    }

    public void setSession(SessionDto session) {
        this.sessionMap.put(session.getId(), session);
    }

    public void removeSession(String sessionId) {
        this.sessionMap.remove(sessionId);
    }

    public Collection<SessionDto> allSessions() {
        return sessionMap.values();
    }
}

package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Created by one on 17/04/16.
 */
@Controller
public class GreetingController {

    @Autowired
    public SimpMessagingTemplate messaging;

    @Autowired
    SessionManager sessionManager;

    @MessageMapping("/hello")
    public void greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000);
        sessionManager.allSessions().forEach(s -> {
            SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor
                    .create(SimpMessageType.MESSAGE);
            Greeting greeting = new Greeting("Hello, " + s.getId() + "!");
            headerAccessor.setSessionId(s.getId());
            headerAccessor.setLeaveMutable(true);
            messaging.convertAndSendToUser(s.getId(), "/topic/greetings",
                    greeting,
                    headerAccessor.getMessageHeaders());
        });
    }
}

package com.someoddring.sportsbet.sportsmatches.broadcast;

import com.someoddring.sportsbet.sportsmatches.integration.SportsMatchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WebSocketLiveBroadcastChannel implements LiveBroadcastChannel {

    private final SimpMessagingTemplate template;

    @Autowired
    public WebSocketLiveBroadcastChannel(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Override
    public void send(List<SportsMatchDTO> items) {
        template.convertAndSend("/topic/bids", items);
    }
}

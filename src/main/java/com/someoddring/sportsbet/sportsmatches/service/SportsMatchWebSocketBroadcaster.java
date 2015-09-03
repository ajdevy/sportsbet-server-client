package com.someoddring.sportsbet.sportsmatches.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class SportsMatchWebSocketBroadcaster {
    final static int BROADCAST_DELAY = 500;

    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private SportsMatchService sportsMatchService;

    private TaskScheduler scheduler = new ConcurrentTaskScheduler();
    private List<String> stockPrices = new ArrayList<>();
    private Random rand = new Random(System.currentTimeMillis());

    private Logger logger = LoggerFactory.getLogger(getClass());

    private void getLatestSportsMatchesAndBroadcast() {
        template.convertAndSend("/topic/bids", sportsMatchService.findAll());
    }

    @PostConstruct
    private void broadcastTimePeriodically() {
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                getLatestSportsMatchesAndBroadcast();
            }
        }, BROADCAST_DELAY);
    }

}
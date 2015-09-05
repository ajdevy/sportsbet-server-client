package com.someoddring.sportsbet.sportsmatches.service;

import com.someoddring.sportsbet.sportsmatches.broadcast.LiveBroadcastChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final SportsMatchService sportsMatchService;

    private final LiveBroadcastChannel liveBroadcastChannel;

    private final TaskScheduler scheduler = new ConcurrentTaskScheduler();
    private List<String> stockPrices = new ArrayList<>();
    private Random rand = new Random(System.currentTimeMillis());
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public SportsMatchWebSocketBroadcaster(SportsMatchService sportsMatchService, LiveBroadcastChannel liveBroadcastChannel) {
        this.sportsMatchService = sportsMatchService;
        this.liveBroadcastChannel = liveBroadcastChannel;
    }

    private void getLatestSportsMatchesAndBroadcast() {
        liveBroadcastChannel.send(sportsMatchService.findAll());
    }

    @PostConstruct
    public void broadcastTimePeriodically() {
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                getLatestSportsMatchesAndBroadcast();
            }
        }, BROADCAST_DELAY);
    }

}
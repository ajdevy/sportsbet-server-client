package com.someoddring.sportsbet.sportsmatches.coefficientrandomizer;

import com.someoddring.sportsbet.sportsmatches.dao.SportsMatchRepository;
import com.someoddring.sportsbet.sportsmatches.dao.entity.SportsMatchEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;

@Component
public class ScheduledRandomCoefficientGenerator {

    private final TaskScheduler scheduler = new ConcurrentTaskScheduler();
    private final Random rand = new Random(System.currentTimeMillis());
    @Autowired
    private SportsMatchRepository sportsMatchRepository;
    private Logger logger = LoggerFactory.getLogger(getClass());

    private void getLatestSportsMatchesAndBroadcast() {
        final List<SportsMatchEntity> sportsMatches = sportsMatchRepository.findAll();

        for (SportsMatchEntity sportsMatch : sportsMatches) {
            Random random = new Random(System.currentTimeMillis());
            if (random.nextBoolean())
                sportsMatch.setWin(SportsMatchEntity.Builder.randomCoefficient());

            if (random.nextBoolean())
                sportsMatch.setDraw(SportsMatchEntity.Builder.randomCoefficient());

            if (random.nextBoolean())
                sportsMatch.setLose(SportsMatchEntity.Builder.randomCoefficient());
//            logger.info("saving sportsmatch " + sportsMatch);
            sportsMatchRepository.save(sportsMatch);
        }
    }

    @PostConstruct
    private void broadcastTimePeriodically() {
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                getLatestSportsMatchesAndBroadcast();
            }
        }, atLeastOnceASecond());
    }

    private long atLeastOnceASecond() {
        return rand.nextInt(700) + 300;
    }

}
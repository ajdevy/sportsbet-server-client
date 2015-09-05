package com.someoddring.sportsbet.test;


import com.someoddring.sportsbet.AppConfig;
import com.someoddring.sportsbet.betting.BetType;
import com.someoddring.sportsbet.betting.dao.BetRepository;
import com.someoddring.sportsbet.betting.dao.entity.BetEntity;
import com.someoddring.sportsbet.sportsmatches.dao.entity.SportsMatchEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = AppConfig.class)
public abstract class BaseBetRepositoryDbTest<T extends BetRepository> {

    @Autowired
    protected T betRepository;


    protected void deleteTestBet(BetEntity betEntity) {
        betRepository.delete(betEntity);
    }

    protected BetEntity createAndSaveTestBet() {
        BetEntity betEntity = new BetEntity();
        betEntity.setBetAmount(new BigDecimal(20));
        betEntity.setBetType(BetType.win);
        betEntity.setSportsMatchName(SportsMatchEntity.Builder.SPORTS_MATCH_GERMANY_ITALY);
        betEntity.setCoefficient(SportsMatchEntity.Builder.randomCoefficient());
        betEntity.setIp("127.0.0.1");
        betEntity.setTimestamp(System.currentTimeMillis());

        betRepository.save(betEntity);

        return betEntity;
    }

    protected BetEntity createTestBet() {
        BetEntity betEntity = new BetEntity();
        betEntity.setBetAmount(new BigDecimal(20));
        betEntity.setBetType(BetType.win);
        betEntity.setSportsMatchName(SportsMatchEntity.Builder.SPORTS_MATCH_GERMANY_ITALY);
        betEntity.setCoefficient(SportsMatchEntity.Builder.randomCoefficient());
        betEntity.setIp("127.0.0.1");
        betEntity.setTimestamp(System.currentTimeMillis());

        return betEntity;
    }


    @Before
    public void setUp() {
        betRepository.deleteAll();
    }

    @After
    public void tearDown() {
        betRepository.deleteAll();
    }
}

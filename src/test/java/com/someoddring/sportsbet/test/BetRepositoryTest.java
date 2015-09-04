package com.someoddring.sportsbet.test;

import com.someoddring.sportsbet.AppConfig;
import com.someoddring.sportsbet.betting.BetType;
import com.someoddring.sportsbet.betting.dao.BetRepository;
import com.someoddring.sportsbet.betting.dao.entity.BetEntity;
import com.someoddring.sportsbet.sportsmatches.dao.entity.SportsMatchEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = AppConfig.class)
public class BetRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(BetRepositoryTest.class);

    @Autowired
    private BetRepository betRepository;
    private BetEntity betEntity;

    private void deleteTestBet() {
        betRepository.delete(betEntity);
    }

    private void createTestBet() {
        betEntity = new BetEntity();
        betEntity.setBetAmount(new BigDecimal(20));
        betEntity.setBetType(BetType.win);
        betEntity.setSportsMatchName(SportsMatchEntity.Builder.SPORTS_MATCH_GERMANY_ITALY);
        betEntity.setCoefficient(SportsMatchEntity.Builder.randomCoefficient());
        betEntity.setIp("127.0.0.1");
        betEntity.setTimestamp(System.currentTimeMillis());

        betRepository.save(betEntity);
    }

    @Before
    public void setUp() {
        betRepository.deleteAll();
    }

    @After
    public void tearDown() {
        betRepository.deleteAll();
    }

    @Test
    public void testSaveDeleteBet() {
        createTestBet();
        assertThat(betRepository.findAll()).contains(betEntity);
        deleteTestBet();
        assertThat(betRepository.findAll()).doesNotContain(betEntity);
    }


    @Test
    public void testFindWinBetsOnGermanyItaly() {
        createTestBet();
        assertThat(betRepository.findWinBetsOnGermanyItaly()).contains(betEntity);
        deleteTestBet();
    }

    @Test
    public void testDeleteByTimestamp() {
        createTestBet();
        assertThat(betRepository.findAll()).contains(betEntity);
        betRepository.deleteByTimestamp(betEntity.getTimestamp());
        assertThat(betRepository.findAll()).doesNotContain(betEntity);
        deleteTestBet();
    }

    @Test
    public void testCountBetsForIp() {
        createTestBet();
        assertThat(betRepository.findAll()).contains(betEntity);
        betRepository.deleteByTimestamp(betEntity.getTimestamp());
        assertThat(betRepository.findAll()).doesNotContain(betEntity);
        deleteTestBet();
    }
}
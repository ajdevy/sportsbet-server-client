package com.someoddring.sportsbet.test.sportsmatches;

import com.someoddring.sportsbet.AppConfig;
import com.someoddring.sportsbet.betting.BetType;
import com.someoddring.sportsbet.betting.dao.entity.BetEntity;
import com.someoddring.sportsbet.sportsmatches.dao.SportsMatchRepository;
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
public class SportsMatchRepositoryTestBetRepository {

    private static final Logger logger = LoggerFactory.getLogger(SportsMatchRepositoryTestBetRepository.class);
    @Autowired
    protected SportsMatchRepository sportsMatchRepository;

    protected void deleteTestMatch(SportsMatchEntity entity) {
        sportsMatchRepository.delete(entity);
    }

    protected SportsMatchEntity createAndSaveTestMatch() {
        SportsMatchEntity sportsMatchEntity = SportsMatchEntity
                .builder()
                .random();

        sportsMatchRepository.save(sportsMatchEntity);

        return sportsMatchEntity;
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
        sportsMatchRepository.deleteAll();
    }

    @After
    public void tearDown() {
        sportsMatchRepository.deleteAll();
    }

    @Test
    public void testSaveDeleteBet() {
        final SportsMatchEntity entity = createAndSaveTestMatch();
        assertThat(sportsMatchRepository.findAll()).contains(entity);
        deleteTestMatch(entity);
        assertThat(sportsMatchRepository.findAll()).doesNotContain(entity);
    }


    @Test
    public void testFindByName() {
        final SportsMatchEntity sportsMatch = createAndSaveTestMatch();
        assertThat(sportsMatchRepository.findByName(sportsMatch.getName()))
                .isEqualTo(sportsMatch);
        deleteTestMatch(sportsMatch);
    }

    @Test
    public void testCount() {
        final SportsMatchEntity sportsMatch = createAndSaveTestMatch();
        assertThat(new Long(sportsMatchRepository.findAll().size()))
                .isEqualTo(sportsMatchRepository.count())
                .isEqualTo(1l);
        deleteTestMatch(sportsMatch);

        assertThat(new Long(sportsMatchRepository.findAll().size()))
                .isEqualTo(sportsMatchRepository.count())
                .isEqualTo(0l);
    }


    @Test
    public void testFindAll() {
        final SportsMatchEntity sportsMatch = createAndSaveTestMatch();
        final SportsMatchEntity secondSportsMatch = createAndSaveTestMatch();

        assertThat(sportsMatchRepository.findAll())
                .contains(sportsMatch)
                .contains(secondSportsMatch);
        deleteTestMatch(sportsMatch);
        deleteTestMatch(secondSportsMatch);
    }
}
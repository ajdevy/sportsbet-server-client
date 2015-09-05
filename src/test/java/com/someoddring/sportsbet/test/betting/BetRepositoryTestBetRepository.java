package com.someoddring.sportsbet.test.betting;

import com.someoddring.sportsbet.betting.dao.entity.BetEntity;
import com.someoddring.sportsbet.test.BaseBetRepositoryDbTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class BetRepositoryTestBetRepository extends BaseBetRepositoryDbTest {

    private static final Logger logger = LoggerFactory.getLogger(BetRepositoryTestBetRepository.class);

    @Test
    public void testSaveDeleteBet() {
        final BetEntity betEntity = createAndSaveTestBet();
        assertThat(betRepository.findAll()).contains(betEntity);
        deleteTestBet(betEntity);
        assertThat(betRepository.findAll()).doesNotContain(betEntity);
    }


    @Test
    public void testFindWinBetsOnGermanyItaly() {
        final BetEntity betEntity = createAndSaveTestBet();
        assertThat(betRepository.findWinBetsOnGermanyItaly()).contains(betEntity);
        deleteTestBet(betEntity);
    }

    @Test
    public void testDeleteByTimestamp() {
        final BetEntity betEntity = createAndSaveTestBet();
        assertThat(betRepository.findAll()).contains(betEntity);
        betRepository.deleteByTimestamp(betEntity.getTimestamp());
        assertThat(betRepository.findAll()).doesNotContain(betEntity);
        deleteTestBet(betEntity);
    }

    @Test
    public void testCountBetsForIp() {
        final BetEntity bet = createAndSaveTestBet();
        assertThat(betRepository.findAll()).contains(bet);
        betRepository.deleteByTimestamp(bet.getTimestamp());
        assertThat(betRepository.findAll()).doesNotContain(bet);
        deleteTestBet(bet);
    }

    @Test
    public void testFindAll() {
        final BetEntity bet = createAndSaveTestBet();
        final BetEntity secondBet = createAndSaveTestBet();

        assertThat(betRepository.findAll())
                .contains(bet)
                .contains(secondBet);
        deleteTestBet(bet);
        deleteTestBet(secondBet);
    }
}
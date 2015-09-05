package com.someoddring.sportsbet.test.reports;

import com.someoddring.sportsbet.betting.dao.entity.BetEntity;
import com.someoddring.sportsbet.betting.integration.BetDTO;
import com.someoddring.sportsbet.reports.integration.CountBetsForIpResponseDTO;
import com.someoddring.sportsbet.reports.integration.DeleteResponseDTO;
import com.someoddring.sportsbet.reports.service.ReportsService;
import com.someoddring.sportsbet.test.BaseBetRepositoryDbTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class ReportsServiceTestBetRepository extends BaseBetRepositoryDbTest {

    private static final Logger logger = LoggerFactory.getLogger(ReportsServiceTestBetRepository.class);

    @Autowired
    private ReportsService reportsService;

    @Test
    public void testFindWinBetsOnGermanyItaly() {
        final BetEntity betEntity = createAndSaveTestBet();
        assertThat(reportsService.findWinBetsOnGermanyItaly())
                .contains(new BetDTO(betEntity));
        deleteTestBet(betEntity);
    }

    @Test
    public void testDeleteByTimestamp() {
        final BetEntity betEntity = createAndSaveTestBet();
        assertThat(betRepository.findAll()).contains(betEntity);
        final DeleteResponseDTO deleteResponseDTO = reportsService.deleteByTimestamp(betEntity.getTimestamp());
        assertThat(deleteResponseDTO)
                .isNotNull();
        assertThat(deleteResponseDTO.getStatus())
                .isEqualTo(DeleteResponseDTO.SUCCESS);
        assertThat(betRepository.findAll())
                .doesNotContain(betEntity);
        deleteTestBet(betEntity);

        final DeleteResponseDTO secondDeleteResponseDTO = reportsService.deleteByTimestamp(betEntity.getTimestamp());
        assertThat(secondDeleteResponseDTO)
                .isNotNull();
        assertThat(secondDeleteResponseDTO.getStatus())
                .isEqualTo(DeleteResponseDTO.FAILURE);

    }

    @Test
    public void testCountBetsForIp() {
        final BetEntity betEntity = createAndSaveTestBet();
        assertThat(betRepository.findAll()).contains(betEntity);
        final CountBetsForIpResponseDTO countBetsForIpResponseDTO = reportsService.countBetsForIp(betEntity.getIp());
        assertThat(countBetsForIpResponseDTO)
                .isNotNull();
        assertThat(countBetsForIpResponseDTO.getCount())
                .isEqualTo(1);
        deleteTestBet(betEntity);
    }
}
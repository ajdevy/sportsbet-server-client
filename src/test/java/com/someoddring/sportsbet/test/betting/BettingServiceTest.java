package com.someoddring.sportsbet.test.betting;

import com.someoddring.sportsbet.betting.BetType;
import com.someoddring.sportsbet.betting.dao.BetRepository;
import com.someoddring.sportsbet.betting.dao.entity.BetEntity;
import com.someoddring.sportsbet.betting.integration.PlaceBetRequestDTO;
import com.someoddring.sportsbet.betting.integration.PlaceBetResponseDTO;
import com.someoddring.sportsbet.betting.service.BettingService;
import com.someoddring.sportsbet.betting.service.CoefficientDiffBettingService;
import com.someoddring.sportsbet.sportsmatches.dao.SportsMatchRepository;
import com.someoddring.sportsbet.sportsmatches.dao.entity.SportsMatchEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class BettingServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(BettingServiceTest.class);


    @Mock
    private SportsMatchRepository sportsMatchRepository;
    @Mock
    private BetRepository betRepository;

    private BettingService bettingService;

    @Before
    public void setUp() {
        this.bettingService = new CoefficientDiffBettingService(betRepository, sportsMatchRepository);
    }

    private void setSportsMatchCoefficient(BetEntity bet, SportsMatchEntity sportsMatchEntity, double coefficient) {
        switch (bet.getBetType()) {
            case win:
                sportsMatchEntity.setWin(coefficient);
                break;
            case draw:
                sportsMatchEntity.setDraw(coefficient);
                break;
            case lose:
                sportsMatchEntity.setLose(coefficient);
                break;
        }
    }

    @Test
    public void testPlaceBetSuccess() {
        PlaceBetRequestDTO placeBetRequest = new PlaceBetRequestDTO();
        placeBetRequest.setBetAmount(new BigDecimal(20));
        placeBetRequest.setBetType(BetType.win);
        placeBetRequest.setSportsMatchName(SportsMatchEntity.Builder.SPORTS_MATCH_GERMANY_ITALY);

        final SportsMatchEntity sportsMatchEntity = SportsMatchEntity.builder().random();
        sportsMatchEntity.setName(SportsMatchEntity.Builder.SPORTS_MATCH_GERMANY_ITALY);

        placeBetRequest.setCoefficient(sportsMatchEntity.getWin());

        when(sportsMatchRepository.findByName(SportsMatchEntity.Builder.SPORTS_MATCH_GERMANY_ITALY)).thenAnswer(new Answer<SportsMatchEntity>() {
            @Override
            public SportsMatchEntity answer(InvocationOnMock invocationOnMock) throws Throwable {
                return sportsMatchEntity;
            }
        });

        final String testIp = "192.168.0.106";

        final PlaceBetResponseDTO response = bettingService.placeBet(testIp, placeBetRequest);

        final ArgumentCaptor<BetEntity> savedBetArgumentCaptor = ArgumentCaptor.forClass(BetEntity.class);
        verify(betRepository, times(1)).save(savedBetArgumentCaptor.capture());
        verifyNoMoreInteractions(betRepository);

        assertThat(response)
                .isNotNull();
        assertThat(response.getStatus())
                .isEqualTo(PlaceBetResponseDTO.SUCCESS);
    }

    @Test
    public void testPlaceBetFailure() {
        PlaceBetRequestDTO placeBetRequest = new PlaceBetRequestDTO();
        placeBetRequest.setBetAmount(new BigDecimal(20));
        placeBetRequest.setBetType(BetType.win);
        placeBetRequest.setSportsMatchName(SportsMatchEntity.Builder.SPORTS_MATCH_GERMANY_ITALY);

        final SportsMatchEntity sportsMatchEntity = SportsMatchEntity.builder().random();
        sportsMatchEntity.setName(SportsMatchEntity.Builder.SPORTS_MATCH_GERMANY_ITALY);

        placeBetRequest.setCoefficient(sportsMatchEntity.getWin() + 1);

        when(sportsMatchRepository.findByName(SportsMatchEntity.Builder.SPORTS_MATCH_GERMANY_ITALY)).thenAnswer(new Answer<SportsMatchEntity>() {
            @Override
            public SportsMatchEntity answer(InvocationOnMock invocationOnMock) throws Throwable {
                return sportsMatchEntity;
            }
        });

        final String testIp = "192.168.0.106";

        final PlaceBetResponseDTO response = bettingService.placeBet(testIp, placeBetRequest);

        verifyNoMoreInteractions(betRepository);

        assertThat(response)
                .isNotNull();
        assertThat(response.getStatus())
                .isEqualTo(PlaceBetResponseDTO.FAILURE);

    }

}
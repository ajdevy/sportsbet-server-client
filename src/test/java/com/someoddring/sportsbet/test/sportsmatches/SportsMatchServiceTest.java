package com.someoddring.sportsbet.test.sportsmatches;

import com.someoddring.sportsbet.sportsmatches.dao.SportsMatchRepository;
import com.someoddring.sportsbet.sportsmatches.dao.entity.SportsMatchEntity;
import com.someoddring.sportsbet.sportsmatches.integration.SportsMatchDTO;
import com.someoddring.sportsbet.sportsmatches.service.MongoDbSportsMatchService;
import com.someoddring.sportsbet.sportsmatches.service.SportsMatchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class SportsMatchServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(SportsMatchServiceTest.class);

    @Mock
    private SportsMatchRepository sportsMatchRepository;

    private SportsMatchService service;

    @Before
    public void setUp() {
        this.service = new MongoDbSportsMatchService(sportsMatchRepository);
    }

    @Test
    public void testFindAll() {

        final SportsMatchEntity sportsMatchEntity = SportsMatchEntity.builder().random();

        when(sportsMatchRepository.findAll()).thenAnswer(new Answer<List<SportsMatchEntity>>() {
            @Override
            public List<SportsMatchEntity> answer(InvocationOnMock invocationOnMock) throws Throwable {
                logger.error(" sportsMatchEntity =  " + sportsMatchEntity);


                sportsMatchEntity.setId(UUID.randomUUID().toString());
                return Arrays.asList(sportsMatchEntity);
            }
        });

        final List<SportsMatchDTO> sportsMatches = service.findAll();

        verify(sportsMatchRepository, times(1)).findAll();
        verifyNoMoreInteractions(sportsMatchRepository);

        assertThat(sportsMatches)
                .isNotNull()
                .hasSize(1);
        assertThat(sportsMatches.get(0))
                .isEqualTo(new SportsMatchDTO(sportsMatchEntity));
    }


}
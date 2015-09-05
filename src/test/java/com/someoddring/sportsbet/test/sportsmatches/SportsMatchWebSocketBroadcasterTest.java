package com.someoddring.sportsbet.test.sportsmatches;

import com.someoddring.sportsbet.sportsmatches.broadcast.LiveBroadcastChannel;
import com.someoddring.sportsbet.sportsmatches.integration.SportsMatchDTO;
import com.someoddring.sportsbet.sportsmatches.service.SportsMatchService;
import com.someoddring.sportsbet.sportsmatches.service.SportsMatchWebSocketBroadcaster;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

public class SportsMatchWebSocketBroadcasterTest {

    @Mock
    private LiveBroadcastChannel liveBroadcastChannel;
    @Mock
    private SportsMatchService sportsMatchService;

    private SportsMatchWebSocketBroadcaster service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.service = new SportsMatchWebSocketBroadcaster(sportsMatchService, liveBroadcastChannel);
    }

    @Test
    public void testChannelBroadcast() throws Exception {
        service.broadcastTimePeriodically();
        //should be called at least 3 times in 3 seconds
        verify(liveBroadcastChannel, timeout(3000).times(3))
                .send(anyListOf(SportsMatchDTO.class));
    }

}

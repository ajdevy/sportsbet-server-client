package com.someoddring.sportsbet.test.betting;


import com.someoddring.sportsbet.betting.controller.BettingRestController;
import com.someoddring.sportsbet.betting.integration.PlaceBetRequestDTO;
import com.someoddring.sportsbet.betting.service.BettingService;
import com.someoddring.sportsbet.test.BaseRestControllerTest;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class BettingRestControllerTest extends BaseRestControllerTest {

    @Mock
    private BettingService service;

    @Override
    protected Object createController() {
        return new BettingRestController(service);
    }

    @Test
    public void testPlaceBet() throws Exception {
        final PlaceBetRequestDTO request = new PlaceBetRequestDTO();

        mockMvc.perform(
                post("/rest/bet")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(request)));

        ArgumentCaptor<PlaceBetRequestDTO> betRequestArgument = ArgumentCaptor.forClass(PlaceBetRequestDTO.class);
        ArgumentCaptor<String> ipArgument = ArgumentCaptor.forClass(String.class);
        verify(service, times(1)).placeBet(ipArgument.capture(), betRequestArgument.capture());
        verifyNoMoreInteractions(service);
    }

}

package com.someoddring.sportsbet.test.reports;


import com.someoddring.sportsbet.reports.controller.ReportsRestController;
import com.someoddring.sportsbet.reports.integration.CountBetsForIpRequestDTO;
import com.someoddring.sportsbet.reports.integration.DeleteRequestDTO;
import com.someoddring.sportsbet.reports.service.ReportsService;
import com.someoddring.sportsbet.test.BaseRestControllerTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class ReportsRestControllerTest extends BaseRestControllerTest {

    @Mock
    private ReportsService service;

    @Override
    protected Object createController() {
        return new ReportsRestController(service);
    }


    @Test
    public void testDelete() throws Exception {
        final DeleteRequestDTO request = new DeleteRequestDTO();


        mockMvc.perform(
                delete("/rest/reports")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(request)));

        ArgumentCaptor<Long> timestampArgument = ArgumentCaptor.forClass(Long.class);
        verify(service, times(1)).deleteByTimestamp(timestampArgument.capture());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testCountBetsForIp() throws Exception {
        CountBetsForIpRequestDTO request = new CountBetsForIpRequestDTO();
        mockMvc.perform(
                post("/rest/reports/bet-count")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(request)));
        final ArgumentCaptor<String> ipArgument = ArgumentCaptor.forClass(String.class);
        verify(service, times(1)).countBetsForIp(ipArgument.capture());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void testFindWinBetsOnGermanyItaly() throws Exception {
        mockMvc.perform(get("/rest/reports/germany-italy-win"));

        verify(service, times(1)).findWinBetsOnGermanyItaly();
        verifyNoMoreInteractions(service);
    }

}

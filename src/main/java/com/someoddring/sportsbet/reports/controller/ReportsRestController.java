package com.someoddring.sportsbet.reports.controller;


import com.someoddring.sportsbet.betting.integration.BetDTO;
import com.someoddring.sportsbet.reports.integration.CountBetsForIpRequestDTO;
import com.someoddring.sportsbet.reports.integration.CountBetsForIpResponseDTO;
import com.someoddring.sportsbet.reports.integration.DeleteRequestDTO;
import com.someoddring.sportsbet.reports.integration.DeleteResponseDTO;
import com.someoddring.sportsbet.reports.service.ReportsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportsRestController {
    private static final Logger logger = LoggerFactory.getLogger(ReportsRestController.class);

    private final ReportsService reportsService;

    @Autowired
    public ReportsRestController(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/germany-italy-win")
    @ResponseBody
    public List<BetDTO> findWinBetsOnGermanyItaly() {
        return reportsService.findWinBetsOnGermanyItaly();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/bet-count")
    @ResponseBody
    public CountBetsForIpResponseDTO countBetsForIp(@RequestBody CountBetsForIpRequestDTO request) {
        return reportsService.countBetsForIp(request.getIp());
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public DeleteResponseDTO delete(@RequestBody DeleteRequestDTO request) {
        return reportsService.deleteByTimestamp(request.getTimestamp());
    }
}

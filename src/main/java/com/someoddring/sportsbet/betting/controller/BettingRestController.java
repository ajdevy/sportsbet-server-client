package com.someoddring.sportsbet.betting.controller;


import com.someoddring.sportsbet.betting.integration.PlaceBetRequestDTO;
import com.someoddring.sportsbet.betting.integration.PlaceBetResponseDTO;
import com.someoddring.sportsbet.betting.service.BettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest")
public class BettingRestController {
    private static final Logger logger = LoggerFactory.getLogger(BettingRestController.class);

    private final BettingService bettingService;

    @Autowired
    public BettingRestController(BettingService bettingService) {
        this.bettingService = bettingService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/bet")
    @ResponseBody
    public PlaceBetResponseDTO placeBet(@RequestBody PlaceBetRequestDTO bet, HttpServletRequest request) {
        logger.info("placing a bet... " + bet);
        return bettingService.placeBet(request.getLocalAddr(), bet);
    }

}

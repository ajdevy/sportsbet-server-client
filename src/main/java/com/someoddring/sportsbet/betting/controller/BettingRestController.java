package com.someoddring.sportsbet.betting.controller;


import com.someoddring.sportsbet.betting.integration.BetDTO;
import com.someoddring.sportsbet.betting.integration.BetResponseDTO;
import com.someoddring.sportsbet.betting.service.BettingService;
import com.someoddring.sportsbet.sportsmatches.dao.SportsMatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest")
public class BettingRestController {
    private static final Logger logger = LoggerFactory.getLogger(BettingRestController.class);

    private final SportsMatchRepository sportsMatchRepository;
    private final BettingService bettingService;

    @Autowired
    public BettingRestController(SportsMatchRepository sportsMatchRepository, BettingService bettingService) {
        this.sportsMatchRepository = sportsMatchRepository;
        this.bettingService = bettingService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/bet")
    @ResponseBody
    public BetResponseDTO placeBet(@RequestBody BetDTO bet, HttpServletRequest request) {
        logger.info("placing a bet... " + bet);
        return bettingService.placeBet(request.getLocalAddr(), bet);
    }

}

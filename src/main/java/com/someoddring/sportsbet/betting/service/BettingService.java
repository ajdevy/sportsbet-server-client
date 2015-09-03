package com.someoddring.sportsbet.betting.service;

import com.someoddring.sportsbet.betting.integration.BetDTO;
import com.someoddring.sportsbet.betting.integration.BetResponseDTO;


public interface BettingService {

    BetResponseDTO placeBet(String userIp,BetDTO bet);
}

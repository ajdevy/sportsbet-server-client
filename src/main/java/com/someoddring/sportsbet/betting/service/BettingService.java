package com.someoddring.sportsbet.betting.service;

import com.someoddring.sportsbet.betting.integration.PlaceBetRequestDTO;
import com.someoddring.sportsbet.betting.integration.PlaceBetResponseDTO;

public interface BettingService {
    PlaceBetResponseDTO placeBet(String userIp, PlaceBetRequestDTO bet);
}

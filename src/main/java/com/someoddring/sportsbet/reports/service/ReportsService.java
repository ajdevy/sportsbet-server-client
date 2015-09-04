package com.someoddring.sportsbet.reports.service;

import com.someoddring.sportsbet.betting.dao.entity.BetEntity;
import com.someoddring.sportsbet.betting.integration.BetDTO;
import com.someoddring.sportsbet.reports.integration.CountBetsForIpResponseDTO;
import com.someoddring.sportsbet.reports.integration.DeleteResponseDTO;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReportsService {
    CountBetsForIpResponseDTO countBetsForIp(String ip);

    DeleteResponseDTO deleteByTimestamp(long timestamp);

    List<BetDTO> findWinBetsOnGermanyItaly();
}

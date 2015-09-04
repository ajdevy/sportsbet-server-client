package com.someoddring.sportsbet.reports.service;

import com.someoddring.sportsbet.betting.dao.BetRepository;
import com.someoddring.sportsbet.betting.dao.entity.BetEntity;
import com.someoddring.sportsbet.betting.integration.BetDTO;
import com.someoddring.sportsbet.reports.integration.CountBetsForIpResponseDTO;
import com.someoddring.sportsbet.reports.integration.DeleteResponseDTO;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportsServiceImpl implements ReportsService {

    private final BetRepository betRepository;

    @Autowired
    public ReportsServiceImpl(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    public CountBetsForIpResponseDTO countBetsForIp(String ip) {
        InetAddressValidator validator = InetAddressValidator.getInstance();
        final CountBetsForIpResponseDTO response = new CountBetsForIpResponseDTO();
        if (validator.isValid(ip)) {
            final long betCount = betRepository.countBetsForIp(ip);
            response.setIp(ip);
            response.setCount(betCount);
        } else {
            response.setIp(ip);
            response.setCount(0);
        }

        return response;
    }

    public DeleteResponseDTO deleteByTimestamp(long timestamp) {
        betRepository.deleteByTimestamp(timestamp);
        return new DeleteResponseDTO(DeleteResponseDTO.SUCCESS);
    }

    public List<BetDTO> findWinBetsOnGermanyItaly() {
        List<BetEntity> bets = betRepository.findWinBetsOnGermanyItaly();
        List<BetDTO> response = new ArrayList<>();
        for (BetEntity bet : bets) {
            response.add(new BetDTO(bet));
        }
        return response;
    }
}

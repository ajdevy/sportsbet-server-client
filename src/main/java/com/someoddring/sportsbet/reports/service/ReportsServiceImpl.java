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
    private final InetAddressValidator validator;

    @Autowired
    public ReportsServiceImpl(BetRepository betRepository) {
        this.betRepository = betRepository;
        this.validator = InetAddressValidator.getInstance();
    }

    public CountBetsForIpResponseDTO countBetsForIp(String ip) {

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
        final Long recordsDeleted = betRepository.deleteByTimestamp(timestamp);
        return recordsDeleted > 0 ? new DeleteResponseDTO(DeleteResponseDTO.SUCCESS) : new DeleteResponseDTO(DeleteResponseDTO.FAILURE);
    }

    public List<BetDTO> findWinBetsOnGermanyItaly() {
        final List<BetEntity> bets = betRepository.findWinBetsOnGermanyItaly();
        final List<BetDTO> response = new ArrayList<>();
        for (BetEntity bet : bets) {
            response.add(new BetDTO(bet));
        }
        return response;
    }
}

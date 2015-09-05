package com.someoddring.sportsbet.betting.service;

import com.someoddring.sportsbet.betting.dao.BetRepository;
import com.someoddring.sportsbet.betting.dao.entity.BetEntity;
import com.someoddring.sportsbet.betting.integration.PlaceBetRequestDTO;
import com.someoddring.sportsbet.betting.integration.PlaceBetResponseDTO;
import com.someoddring.sportsbet.integration.StatusResponseDTO;
import com.someoddring.sportsbet.sportsmatches.dao.SportsMatchRepository;
import com.someoddring.sportsbet.sportsmatches.dao.entity.SportsMatchEntity;
import com.someoddring.sportsbet.sportsmatches.dao.exception.SportsMatchDataCorruptionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CoefficientDiffBettingService implements BettingService {

    private static final Logger logger = LoggerFactory.getLogger(CoefficientDiffBettingService.class);

    private final BetRepository betRepository;
    private final SportsMatchRepository sportsMatchRepository;

    @Autowired
    public CoefficientDiffBettingService(BetRepository betRepository, SportsMatchRepository sportsMatchRepository) {
        this.betRepository = betRepository;
        this.sportsMatchRepository = sportsMatchRepository;
    }

    @Override
    public PlaceBetResponseDTO placeBet(String userIp, PlaceBetRequestDTO bet) {
        final SportsMatchEntity sportsMatchEntity = sportsMatchRepository.findByName(bet.getSportsMatchName());
        logger.info("placing bet... found a sports match = " + sportsMatchEntity + " the bet = " + bet);

        PlaceBetResponseDTO response;
        final double serverCoefficient;
        try {
            serverCoefficient = getCoefficientFromSportsMatch(bet, sportsMatchEntity);
            logger.info("serverCoefficient = " + serverCoefficient + " bet.getCoefficient() = " + bet.getCoefficient());
            if (bet.getCoefficient() == serverCoefficient) {
                //save the bet
                persistBet(new BetEntity(userIp, bet));

                response = new PlaceBetResponseDTO(StatusResponseDTO.SUCCESS);
                logger.info("bet placed successfully");
            } else {
                response = new PlaceBetResponseDTO(StatusResponseDTO.FAILURE, serverCoefficient);
            }
        } catch (SportsMatchDataCorruptionException e) {
            logger.error("data corruption detected", e);
            response = new PlaceBetResponseDTO(StatusResponseDTO.FAILURE);
        } catch (DataAccessException e) {
            logger.error("exception occurred while persisting a bet", e);
            response = new PlaceBetResponseDTO(StatusResponseDTO.FAILURE);
        }

        return response;
    }

    private void persistBet(BetEntity betEntity) throws DataAccessException {
        betRepository.save(betEntity);
    }

    private double getCoefficientFromSportsMatch(PlaceBetRequestDTO bet, SportsMatchEntity sportsMatchEntity) throws SportsMatchDataCorruptionException {
        switch (bet.getBetType()) {
            case win:
                return sportsMatchEntity.getWin();
            case draw:
                return sportsMatchEntity.getDraw();
            case lose:
                return sportsMatchEntity.getLose();
        }
        throw new SportsMatchDataCorruptionException("No corresponding sports match type found, probably data is corrupted or the logic has changed");
    }
}

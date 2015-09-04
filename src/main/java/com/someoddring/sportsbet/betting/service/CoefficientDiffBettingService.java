package com.someoddring.sportsbet.betting.service;

import com.someoddring.sportsbet.betting.dao.BetRepository;
import com.someoddring.sportsbet.betting.dao.entity.BetEntity;
import com.someoddring.sportsbet.betting.integration.BetDTO;
import com.someoddring.sportsbet.betting.integration.BetResponseDTO;
import com.someoddring.sportsbet.integration.StatusResponseDTO;
import com.someoddring.sportsbet.sportsmatches.dao.SportsMatchRepository;
import com.someoddring.sportsbet.sportsmatches.dao.entity.SportsMatchEntity;
import com.someoddring.sportsbet.sportsmatches.dao.exception.SportsMatchDataCorrupctionException;
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
    public BetResponseDTO placeBet(String userIp, BetDTO bet) {
        final SportsMatchEntity sportsMatchEntity = sportsMatchRepository.findByName(bet.getSportsMatchName());
        logger.info("placing bet... found a sports match for the bet " + sportsMatchEntity +" the bet = " + bet);

        BetResponseDTO response;
        final double serverCoefficient;
        try {
            serverCoefficient = getCoefficientFromSportsMatch(bet, sportsMatchEntity);
            logger.info("serverCoefficient = " + serverCoefficient + " bet.getCoefficient() = " + bet.getCoefficient());
            if (bet.getCoefficient() == serverCoefficient) {
                //save the bet
                persistBet(new BetEntity(userIp, bet));

                response = new BetResponseDTO(StatusResponseDTO.SUCCESS);
                logger.info("bet placed successfully");
            } else {
                response = new BetResponseDTO(StatusResponseDTO.FAILURE, serverCoefficient);
            }
        } catch (SportsMatchDataCorrupctionException e) {
            logger.error("data corruption detected", e);
            response = new BetResponseDTO(StatusResponseDTO.FAILURE);
        } catch (DataAccessException e) {
            logger.error("exception occured while persisting a bet", e);
            response = new BetResponseDTO(StatusResponseDTO.FAILURE);
        }

        return response;
    }

    private void persistBet(BetEntity betEntity) throws DataAccessException {
        betRepository.save(betEntity);
    }

    private double getCoefficientFromSportsMatch(BetDTO bet, SportsMatchEntity sportsMatchEntity) throws SportsMatchDataCorrupctionException {
        switch (bet.getBetType()) {
            case win:
                return sportsMatchEntity.getWin();
            case draw:
                return sportsMatchEntity.getDraw();
            case lose:
                return sportsMatchEntity.getLose();
        }
        throw new SportsMatchDataCorrupctionException("No corresponding sports match type found, probably data is corrupted or the logic has changed");
    }
}

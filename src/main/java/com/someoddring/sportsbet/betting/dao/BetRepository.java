package com.someoddring.sportsbet.betting.dao;

import com.someoddring.sportsbet.betting.dao.entity.BetEntity;
import org.springframework.dao.DataAccessException;

import java.util.List;


public interface BetRepository {

    List<BetEntity> findAll();

    BetEntity save(BetEntity saved) throws DataAccessException;

    Long countBetsForIp(String ip);

    Long deleteByTimestamp(long timestamp);

    List<BetEntity> findWinBetsOnGermanyItaly();

    BetEntity delete(BetEntity betEntity);

    void deleteAll();
}

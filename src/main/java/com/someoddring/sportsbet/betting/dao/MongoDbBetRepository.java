package com.someoddring.sportsbet.betting.dao;

import com.someoddring.sportsbet.betting.BetType;
import com.someoddring.sportsbet.betting.dao.entity.BetEntity;
import com.someoddring.sportsbet.sportsmatches.dao.entity.SportsMatchEntity;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface MongoDbBetRepository extends BetRepository, Repository<BetEntity, String> {
    public static final String WIN_BET_ON_GERMANY_ITALY_QUERY_STRING = "{'sportsMatchName' : '" + SportsMatchEntity.Builder.SPORTS_MATCH_GERMANY_ITALY + "', 'betType' : '" + BetType.WIN + "'}";


    @Override
    @Query(value = "{'ip' : ?0}", count = true)
    Long countBetsForIp(String ip);

    @Override
    @Query(value = "{'timestamp' : ?0}", delete = true)
    Long deleteByTimestamp(long timestamp);

    @Override
    @Query(value = WIN_BET_ON_GERMANY_ITALY_QUERY_STRING)
    List<BetEntity> findWinBetsOnGermanyItaly();

}

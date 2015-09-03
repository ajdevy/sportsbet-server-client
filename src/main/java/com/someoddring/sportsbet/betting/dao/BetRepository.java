package com.someoddring.sportsbet.betting.dao;

import com.someoddring.sportsbet.betting.dao.entity.BetEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

import java.util.List;


public interface BetRepository extends Repository<BetEntity, String> {

    List<BetEntity> findAll();

    BetEntity save(BetEntity saved) throws DataAccessException;
}

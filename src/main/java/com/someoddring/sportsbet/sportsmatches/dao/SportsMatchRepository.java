package com.someoddring.sportsbet.sportsmatches.dao;

import com.someoddring.sportsbet.sportsmatches.dao.entity.SportsMatchEntity;
import org.springframework.data.repository.Repository;

import java.util.List;


public interface SportsMatchRepository extends Repository<SportsMatchEntity, String> {

    void delete(SportsMatchEntity deleted);

    List<SportsMatchEntity> findAll();

    SportsMatchEntity findOne(String id);

    SportsMatchEntity save(SportsMatchEntity saved);

    SportsMatchEntity findByName(String sportsMatchName);
}

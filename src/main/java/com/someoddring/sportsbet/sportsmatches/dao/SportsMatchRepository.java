package com.someoddring.sportsbet.sportsmatches.dao;

import com.someoddring.sportsbet.sportsmatches.dao.entity.SportsMatchEntity;
import org.springframework.data.repository.Repository;

import java.util.List;


@SuppressWarnings("UnusedReturnValue")
public interface SportsMatchRepository extends Repository<SportsMatchEntity, String> {

    void delete(SportsMatchEntity deleted);

    List<SportsMatchEntity> findAll();

    SportsMatchEntity save(SportsMatchEntity saved);

    SportsMatchEntity findByName(String sportsMatchName);

    Long count();

    void deleteAll();
}

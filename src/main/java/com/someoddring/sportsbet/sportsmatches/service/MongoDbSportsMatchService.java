package com.someoddring.sportsbet.sportsmatches.service;

import com.someoddring.sportsbet.sportsmatches.dao.SportsMatchRepository;
import com.someoddring.sportsbet.sportsmatches.dao.entity.SportsMatchEntity;
import com.someoddring.sportsbet.sportsmatches.integration.SportsMatchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoDbSportsMatchService extends SportsMatchService {

    private static final Logger logger = LoggerFactory.getLogger(MongoDbSportsMatchService.class);

    private final SportsMatchRepository repository;

    @Autowired
    public MongoDbSportsMatchService(SportsMatchRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SportsMatchDTO> findAll() {

        List<SportsMatchEntity> sportsMatchEntries = repository.findAll();


        return convertToDTOs(sportsMatchEntries);
    }

}

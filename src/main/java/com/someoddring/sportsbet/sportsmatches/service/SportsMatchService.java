package com.someoddring.sportsbet.sportsmatches.service;

import com.someoddring.sportsbet.sportsmatches.integration.SportsMatchDTO;

import java.util.List;

public interface SportsMatchService {

    SportsMatchDTO create(SportsMatchDTO sportsMatch);

    List<SportsMatchDTO> findAll();

}

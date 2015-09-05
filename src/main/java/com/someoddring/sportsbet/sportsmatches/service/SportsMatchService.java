package com.someoddring.sportsbet.sportsmatches.service;

import com.someoddring.sportsbet.sportsmatches.dao.entity.SportsMatchEntity;
import com.someoddring.sportsbet.sportsmatches.integration.SportsMatchDTO;

import java.util.ArrayList;
import java.util.List;

public abstract class SportsMatchService {

    public abstract List<SportsMatchDTO> findAll();

    protected List<SportsMatchDTO> convertToDTOs(List<SportsMatchEntity> models) {
        List<SportsMatchDTO> sportsMatchDtoList = new ArrayList<>();
        if (models != null) {
            for (SportsMatchEntity sportsMatch : models) {

                sportsMatchDtoList.add(new SportsMatchDTO(sportsMatch));
            }
        }
        return sportsMatchDtoList;
    }

}

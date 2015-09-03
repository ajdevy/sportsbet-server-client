package com.someoddring.sportsbet.sportsmatches.service;

import com.someoddring.sportsbet.sportsmatches.dao.SportsMatchRepository;
import com.someoddring.sportsbet.sportsmatches.dao.entity.SportsMatchEntity;
import com.someoddring.sportsbet.sportsmatches.integration.SportsMatchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MongoDbSportsMatchService implements SportsMatchService {

    private static final Logger logger = LoggerFactory.getLogger(MongoDbSportsMatchService.class);

    private final SportsMatchRepository repository;

    @Autowired
    public MongoDbSportsMatchService(SportsMatchRepository repository) {
        this.repository = repository;
    }

    @Override
    public SportsMatchDTO create(SportsMatchDTO sportsMatch) {
        logger.info("Creating a new sportsMatch entry with information: ", sportsMatch);

        SportsMatchEntity persisted = SportsMatchEntity.builder()
                .name(sportsMatch.getName())
                .win(sportsMatch.getWin())
                .lose(sportsMatch.getLose())
                .draw(sportsMatch.getDraw())
                .type(sportsMatch.getType())
                .build();

        persisted = repository.save(persisted);
        logger.info("Created a new sportsMatch entry with information ", persisted);

        return convertToDTO(persisted);
    }

    @Override
    public List<SportsMatchDTO> findAll() {

        List<SportsMatchEntity> sportsMatchEntries = repository.findAll();


        return convertToDTOs(sportsMatchEntries);
    }

    private List<SportsMatchDTO> convertToDTOs(List<SportsMatchEntity> models) {
        List<SportsMatchDTO> sportsMatchDtoList = new ArrayList<>();
        if (models != null) {
            for (SportsMatchEntity sportsMatch : models) {

                sportsMatchDtoList.add(convertToDTO(sportsMatch));
            }
        }
        return sportsMatchDtoList;
    }

    private SportsMatchDTO convertToDTO(SportsMatchEntity model) {
        SportsMatchDTO dto = new SportsMatchDTO();

        dto.setWin(model.getWin());
        dto.setName(model.getName());
        dto.setType(model.getType());
        dto.setLose(model.getLose());
        dto.setDraw(model.getDraw());

        return dto;
    }
}

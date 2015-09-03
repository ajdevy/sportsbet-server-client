package com.someoddring.sportsbet.sportsmatches.controller;


import com.someoddring.sportsbet.sportsmatches.dao.SportsMatchRepository;
import com.someoddring.sportsbet.sportsmatches.dao.entity.SportsMatchEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class SportsMatchRestController {
    private static final Logger logger = LoggerFactory.getLogger(SportsMatchRestController.class);

    private final SportsMatchRepository sportsMatchRepository;

    @Autowired
    public SportsMatchRestController(SportsMatchRepository sportsMatchRepository) {
        this.sportsMatchRepository = sportsMatchRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/insertdata")
    @ResponseStatus(HttpStatus.CREATED)
    public void insert() {
        if (sportsMatchRepository.count() == 0) {
            logger.debug("starting to insert data...");
            for (int i = 0; i < 5; i++) {
                sportsMatchRepository.save(SportsMatchEntity.builder().random());
            }

        }
    }
}

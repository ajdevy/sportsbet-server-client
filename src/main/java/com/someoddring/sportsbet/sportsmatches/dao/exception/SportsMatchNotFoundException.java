package com.someoddring.sportsbet.sportsmatches.dao.exception;

public class SportsMatchNotFoundException extends Exception {

    public SportsMatchNotFoundException(String name) {
        super(String.format("No sports match found with name: <%s>", name));
    }
}

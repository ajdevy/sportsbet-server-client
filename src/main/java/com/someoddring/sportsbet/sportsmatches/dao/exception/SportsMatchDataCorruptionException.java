package com.someoddring.sportsbet.sportsmatches.dao.exception;

public class SportsMatchDataCorruptionException extends Exception {
    public SportsMatchDataCorruptionException(String message) {
        super(message);
    }

    public SportsMatchDataCorruptionException(String message, Throwable cause) {
        super(message, cause);
    }

    public SportsMatchDataCorruptionException(Throwable cause) {
        super(cause);
    }

    public SportsMatchDataCorruptionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SportsMatchDataCorruptionException() {
    }
}

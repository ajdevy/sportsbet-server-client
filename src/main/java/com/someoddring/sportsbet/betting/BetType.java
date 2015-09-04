package com.someoddring.sportsbet.betting;

public enum BetType {
        win, draw, lose;

    //enums are not compile time constants, can't use in queries :(
    public static final String WIN = "win";
}
package com.someoddring.sportsbet.betting.integration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.someoddring.sportsbet.betting.BetType;

import java.math.BigDecimal;

public class BetDTO {


    private String sportsMatchName;
    private BigDecimal betAmount;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BetType betType;

    private double coefficient;

    public String getSportsMatchName() {
        return sportsMatchName;
    }

    public void setSportsMatchName(String sportsMatchName) {
        this.sportsMatchName = sportsMatchName;
    }

    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }

    public BetType getBetType() {
        return betType;
    }

    public void setBetType(BetType betType) {
        this.betType = betType;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return "BetDTO{" +
                "sportsMatchName='" + sportsMatchName + '\'' +
                ", betAmount=" + betAmount +
                ", betType='" + betType + '\'' +
                ", coefficient=" + coefficient +
                '}';
    }
}

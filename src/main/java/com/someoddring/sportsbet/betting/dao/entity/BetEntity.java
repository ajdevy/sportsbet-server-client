package com.someoddring.sportsbet.betting.dao.entity;

import com.someoddring.sportsbet.betting.BetType;
import com.someoddring.sportsbet.betting.integration.BetDTO;

import java.math.BigDecimal;

public class BetEntity {
    private long timestamp;
    private String ip;
    private String sportsMatchName;
    private BetType betType;
    private double coefficient;
    private BigDecimal betAmount;

    public BetEntity() {
    }

    public BetEntity(String userIp, BetDTO bet) {
        this.ip = userIp;
        this.timestamp = System.currentTimeMillis();
        this.sportsMatchName = bet.getSportsMatchName();
        this.betType = bet.getBetType();
        this.coefficient = bet.getCoefficient();
        this.betAmount = bet.getBetAmount();
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSportsMatchName() {
        return sportsMatchName;
    }

    public void setSportsMatchName(String sportsMatchName) {
        this.sportsMatchName = sportsMatchName;
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

    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }
}

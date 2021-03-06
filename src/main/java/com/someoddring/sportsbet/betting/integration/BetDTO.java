package com.someoddring.sportsbet.betting.integration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.someoddring.sportsbet.betting.BetType;
import com.someoddring.sportsbet.betting.dao.entity.BetEntity;

import java.math.BigDecimal;

public class BetDTO {

    private String sportsMatchName;
    private BigDecimal betAmount;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BetType betType;
    private String ip;
    private long timestamp;
    private double coefficient;

    public BetDTO() {
    }

    public BetDTO(BetEntity bet) {
        this.sportsMatchName = bet.getSportsMatchName();
        this.betAmount = bet.getBetAmount();
        this.betType = bet.getBetType();
        this.coefficient = bet.getCoefficient();
        this.timestamp = bet.getTimestamp();
        this.ip = bet.getIp();
    }

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BetDTO betDTO = (BetDTO) o;

        if (Double.compare(betDTO.coefficient, coefficient) != 0) return false;
        if (timestamp != betDTO.timestamp) return false;
        if (betAmount != null ? !betAmount.equals(betDTO.betAmount) : betDTO.betAmount != null) return false;
        if (betType != betDTO.betType) return false;
        if (ip != null ? !ip.equals(betDTO.ip) : betDTO.ip != null) return false;
        return !(sportsMatchName != null ? !sportsMatchName.equals(betDTO.sportsMatchName) : betDTO.sportsMatchName != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = sportsMatchName != null ? sportsMatchName.hashCode() : 0;
        result = 31 * result + (betAmount != null ? betAmount.hashCode() : 0);
        result = 31 * result + (betType != null ? betType.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        temp = Double.doubleToLongBits(coefficient);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "BetDTO{" +
                "sportsMatchName='" + sportsMatchName + '\'' +
                ", betAmount=" + betAmount +
                ", betType=" + betType +
                ", ip='" + ip + '\'' +
                ", timestamp=" + timestamp +
                ", coefficient=" + coefficient +
                '}';
    }
}

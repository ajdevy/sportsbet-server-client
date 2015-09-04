package com.someoddring.sportsbet.betting.dao.entity;

import com.someoddring.sportsbet.betting.BetType;
import com.someoddring.sportsbet.betting.integration.BetDTO;

import java.math.BigDecimal;

public class BetEntity {
    private String id;
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

    @Override
    public String toString() {
        return "BetEntity{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", ip='" + ip + '\'' +
                ", sportsMatchName='" + sportsMatchName + '\'' +
                ", betType=" + betType +
                ", coefficient=" + coefficient +
                ", betAmount=" + betAmount +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BetEntity betEntity = (BetEntity) o;

        if (Double.compare(betEntity.coefficient, coefficient) != 0) return false;
        if (timestamp != betEntity.timestamp) return false;
        if (betAmount != null ? !betAmount.equals(betEntity.betAmount) : betEntity.betAmount != null) return false;
        if (betType != betEntity.betType) return false;
        if (ip != null ? !ip.equals(betEntity.ip) : betEntity.ip != null) return false;
        if (sportsMatchName != null ? !sportsMatchName.equals(betEntity.sportsMatchName) : betEntity.sportsMatchName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (timestamp ^ (timestamp >>> 32));
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (sportsMatchName != null ? sportsMatchName.hashCode() : 0);
        result = 31 * result + (betType != null ? betType.hashCode() : 0);
        temp = Double.doubleToLongBits(coefficient);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (betAmount != null ? betAmount.hashCode() : 0);
        return result;
    }
}

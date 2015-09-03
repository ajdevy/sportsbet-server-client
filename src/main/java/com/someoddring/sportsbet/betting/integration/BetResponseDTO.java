package com.someoddring.sportsbet.betting.integration;

import com.fasterxml.jackson.annotation.JsonInclude;

public class BetResponseDTO {
    public static final int FAILURE = 0;
    public static final int SUCCESS = 1;
    private int status;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private double newCoefficient;

    public BetResponseDTO() {
    }

    public BetResponseDTO(int status) {
        this.status = status;
    }

    public BetResponseDTO(int status, double newCoefficient) {
        this.status = status;
        this.newCoefficient = newCoefficient;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getNewCoefficient() {
        return newCoefficient;
    }

    public void setNewCoefficient(double newCoefficient) {
        this.newCoefficient = newCoefficient;
    }

    @Override
    public String toString() {
        return "BetResponseDTO{" +
                "status=" + status +
                ", newCoefficient=" + newCoefficient +
                '}';
    }
}

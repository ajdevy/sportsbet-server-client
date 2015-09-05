package com.someoddring.sportsbet.betting.integration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.someoddring.sportsbet.integration.StatusResponseDTO;

public class PlaceBetResponseDTO extends StatusResponseDTO {
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private double newCoefficient;

    public PlaceBetResponseDTO() {
        super();
    }

    public PlaceBetResponseDTO(int status) {
       super(status);
    }

    public PlaceBetResponseDTO(int status, double newCoefficient) {
        super(status);
        this.newCoefficient = newCoefficient;
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
                 super.toString() +
                ", newCoefficient=" + newCoefficient +
                '}';
    }
}

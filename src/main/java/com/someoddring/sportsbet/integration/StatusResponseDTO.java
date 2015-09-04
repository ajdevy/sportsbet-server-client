package com.someoddring.sportsbet.integration;

public class StatusResponseDTO {
    public static final int FAILURE = 0;
    public static final int SUCCESS = 1;
    private int status;

    public StatusResponseDTO() {
    }

    public StatusResponseDTO(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StatusResponseDTO{" +
                "status=" + status +
                '}';
    }
}

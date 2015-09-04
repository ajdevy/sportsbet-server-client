package com.someoddring.sportsbet.reports.integration;

public class DeleteRequestDTO {
    private long timestamp;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "DeleteRequestDTO{" +
                "timestamp=" + timestamp +
                '}';
    }
}

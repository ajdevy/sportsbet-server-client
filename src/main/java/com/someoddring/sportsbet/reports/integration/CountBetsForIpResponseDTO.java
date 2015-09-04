package com.someoddring.sportsbet.reports.integration;

public class CountBetsForIpResponseDTO {
    private String ip;
    private long count;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CountBetsForIpResponseDTO{" +
                "ip='" + ip + '\'' +
                ", count=" + count +
                '}';
    }
}

package com.someoddring.sportsbet.reports.integration;

public class CountBetsForIpRequestDTO {
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "CountBetsForIpRequestDTO{" +
                "ip='" + ip + '\'' +
                '}';
    }
}

package com.someoddring.sportsbet.sportsmatches.broadcast;

import com.someoddring.sportsbet.sportsmatches.integration.SportsMatchDTO;

import java.util.List;

public interface LiveBroadcastChannel {

    void send(List<SportsMatchDTO> items);
}

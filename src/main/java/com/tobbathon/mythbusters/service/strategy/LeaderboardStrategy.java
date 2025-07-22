package com.tobbathon.mythbusters.service.strategy;

import java.util.List;
import com.tobbathon.mythbusters.model.dto.LeaderboardDTO;

public interface LeaderboardStrategy {
    List<LeaderboardDTO> getLeaderboard();
}

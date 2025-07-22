package com.tobbathon.mythbusters.service.strategy;

import java.util.List;
import com.tobbathon.mythbusters.model.dto.LeaderboardDTO;
import com.tobbathon.mythbusters.repository.LeaderboardRepository;

public class BalloonLeaderboardStrategy implements LeaderboardStrategy {
    private final LeaderboardRepository leaderboardRepository;

    public BalloonLeaderboardStrategy(LeaderboardRepository leaderboardRepository) {
        this.leaderboardRepository = leaderboardRepository;
    }

    @Override
    public List<LeaderboardDTO> getLeaderboard() {
        return leaderboardRepository.orderLeaderboardByGameTypeDesc("baloon");
    }
}

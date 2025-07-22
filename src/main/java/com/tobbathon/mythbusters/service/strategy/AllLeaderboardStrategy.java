package com.tobbathon.mythbusters.service.strategy;

import java.util.List;
import com.tobbathon.mythbusters.model.dto.LeaderboardDTO;
import com.tobbathon.mythbusters.repository.LeaderboardRepository;

public class AllLeaderboardStrategy implements LeaderboardStrategy {
    private final LeaderboardRepository leaderboardRepository;

    public AllLeaderboardStrategy(LeaderboardRepository leaderboardRepository) {
        this.leaderboardRepository = leaderboardRepository;
    }

    @Override
    public List<LeaderboardDTO> getLeaderboard() {
        return leaderboardRepository.findAllLeaderboardDTO();
    }
}

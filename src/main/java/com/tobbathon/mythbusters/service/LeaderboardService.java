package com.tobbathon.mythbusters.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tobbathon.mythbusters.model.dto.LeaderboardDTO;
import com.tobbathon.mythbusters.model.entity.Leaderboard;
import com.tobbathon.mythbusters.repository.LeaderboardRepository;
import com.tobbathon.mythbusters.service.strategy.LeaderboardStrategyFactory;
import com.tobbathon.mythbusters.service.strategy.LeaderboardStrategy;

@Service
public class LeaderboardService {
    private final LeaderboardStrategyFactory strategyFactory;

    public LeaderboardService(LeaderboardRepository leaderboardRepository) {
        this.strategyFactory = new LeaderboardStrategyFactory(leaderboardRepository);
    }

    public List<LeaderboardDTO> orderLeaderboardByGameTypeDesc(String gameType) {
        try {
            LeaderboardStrategy strategy = strategyFactory.getStrategy(gameType);
            return strategy.getLeaderboard();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid game type", e);
        }
    }
}

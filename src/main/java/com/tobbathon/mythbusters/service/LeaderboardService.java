package com.tobbathon.mythbusters.service;

import java.util.List;
import org.springframework.util.StringUtils;

import org.springframework.stereotype.Service;

import com.tobbathon.mythbusters.model.dto.LeaderboardDTO;
import com.tobbathon.mythbusters.repository.LeaderboardRepository;
import com.tobbathon.mythbusters.service.strategy.LeaderboardStrategyFactory;
import com.tobbathon.mythbusters.service.strategy.LeaderboardStrategy;

@Service
public class LeaderboardService {
    private final LeaderboardStrategyFactory strategyFactory;
    private final LeaderboardRepository leaderboardRepository;

    public LeaderboardService(LeaderboardRepository leaderboardRepository) {
        this.strategyFactory = new LeaderboardStrategyFactory(leaderboardRepository);
        this.leaderboardRepository = leaderboardRepository;
    }

    public List<LeaderboardDTO> orderLeaderboardByGameTypeDesc(String gameType) {
        if (!StringUtils.hasText(gameType)) {
            throw new IllegalArgumentException("Game type must not be empty");
        }
        try {
            LeaderboardStrategy strategy = strategyFactory.getStrategy(gameType);
            return strategy.getLeaderboard();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid game type: " + gameType, e);
        }
    }

    public void addNewPoint(String username, Integer point, String gameType) {
        if (!StringUtils.hasText(username)) {
            throw new IllegalArgumentException("Username must not be empty");
        }
        if (point == null || point < 0) {
            throw new IllegalArgumentException("Point must be a non-negative integer");
        }
        if (!StringUtils.hasText(gameType)) {
            throw new IllegalArgumentException("Game type must not be empty");
        }
        leaderboardRepository.upsertScoreByUsername(username, point, gameType);
    }
}

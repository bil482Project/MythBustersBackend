package com.tobbathon.mythbusters.service.strategy;

import com.tobbathon.mythbusters.repository.LeaderboardRepository;

public class LeaderboardStrategyFactory {
    private final LeaderboardRepository leaderboardRepository;

    public LeaderboardStrategyFactory(LeaderboardRepository leaderboardRepository) {
        this.leaderboardRepository = leaderboardRepository;
    }

    public LeaderboardStrategy getStrategy(String gameType) {
        if (gameType == null) {
            throw new IllegalArgumentException("Game type cannot be null");
        }
        switch (gameType.toLowerCase()) {
            case "balloon":
                return new BalloonLeaderboardStrategy(leaderboardRepository);
            case "car":
                return new CarRaceLeaderboardStrategy(leaderboardRepository);
            case "hangman":
                return new HangmanLeaderboardStrategy(leaderboardRepository);
            case "all":
                return new AllLeaderboardStrategy(leaderboardRepository);
            default:
                throw new IllegalArgumentException("Invalid game type: " + gameType);
        }
    }
}

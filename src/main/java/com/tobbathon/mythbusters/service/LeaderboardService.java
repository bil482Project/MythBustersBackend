package com.tobbathon.mythbusters.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tobbathon.mythbusters.model.dto.LeaderboardDTO;
import com.tobbathon.mythbusters.model.entity.Leaderboard;
import com.tobbathon.mythbusters.repository.LeaderboardRepository;

@Service
public class LeaderboardService {
    private final LeaderboardRepository leaderboardRepository;

    public LeaderboardService(LeaderboardRepository leaderboardRepository) {
        this.leaderboardRepository = leaderboardRepository;
    }

    public List<LeaderboardDTO> orderLeaderboardByGameTypeDesc(String gameType) {
        // Bu metot, gameType'a göre profilleri sıralar.
        // Örneğin, gameType 1 ise RaceGameAvatar'a göre, 2 ise BaloonGameAvatar'a göre sıralama yapılabilir.
        try{
            return leaderboardRepository.orderLeaderboardByGameTypeDesc(gameType);
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid game type", e);
        }
    }
}

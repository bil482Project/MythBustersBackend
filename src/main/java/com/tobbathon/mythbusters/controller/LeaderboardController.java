package com.tobbathon.mythbusters.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tobbathon.mythbusters.model.dto.LeaderboardDTO;
import com.tobbathon.mythbusters.service.LeaderboardService;

@RestController
@RequestMapping("/api/leaderboard")
@CrossOrigin(origins = "*")
public class LeaderboardController {
    private final LeaderboardService leaderboardService;

    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }
    //şu an çalışan örnek:
    //http://localhost:8080/api/leaderboard?gameType=car
    //gameType parametresi sayı mı olsa?
    @GetMapping
    public List<LeaderboardDTO> orderProfilesByGameTypeDesc(@RequestParam("gameType") String gameType) {
        return leaderboardService.orderLeaderboardByGameTypeDesc(gameType);
    }
}

package com.tobbathon.mythbusters.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tobbathon.mythbusters.model.dto.LeaderboardDTO;
import com.tobbathon.mythbusters.model.dto.AddLeaderboardDTO;
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
    @PostMapping("/newPoint/{username}")
    public void addNewPoint(@PathVariable("username") String username, @RequestBody AddLeaderboardDTO addLeaderboardDTO) {
        leaderboardService.addNewPoint(username, addLeaderboardDTO.getScore(), addLeaderboardDTO.getGameType());
    }
    //String username, Integer point, String gameType
}

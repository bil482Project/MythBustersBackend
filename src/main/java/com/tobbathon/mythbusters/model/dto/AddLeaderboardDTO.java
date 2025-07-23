package com.tobbathon.mythbusters.model.dto;

import lombok.Data;

@Data
public class AddLeaderboardDTO {
    private String gameType;
    private Integer score;

    public AddLeaderboardDTO(String gameType, Integer score) {
        this.gameType = gameType;
        this.score = score == null ? 0 : score;
    }

}
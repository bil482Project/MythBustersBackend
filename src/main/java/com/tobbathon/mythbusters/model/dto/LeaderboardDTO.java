package com.tobbathon.mythbusters.model.dto;

import lombok.Data;

@Data
public class LeaderboardDTO {
    private Integer profileId;
    private String username;
    private String profilePhoto;
    private Long score;

    public LeaderboardDTO(Integer profileId, String username, String profilePhoto, Long score) {
        this.profileId = profileId;
        this.username = username;
        this.profilePhoto = profilePhoto;
        this.score = score;
    }

}
package com.tobbathon.mythbusters.model.dto;

//belki işime yarar
public class LeaderboardDTO {
    private Integer profileId;
    private String username;
    private String profilePhoto;
    private Integer score;

    public LeaderboardDTO(Integer profileId, String username, String profilePhoto, Integer score) {
        this.profileId = profileId;
        this.username = username;
        this.profilePhoto = profilePhoto;
        this.score = score;
    }

    public Integer getProfileId() {
        return profileId;
    }
    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    
}
package com.tobbathon.mythbusters.model.dto;

//belki işime yarar
public class ProfileDTO {
    private Integer id;
    private String username;
    private String email;
    private String profilePhoto;
    private Integer raceGameAvatarId;
    private Integer baloonGameAvatarId;
    private Integer hangmanGameAvatarId;

    public ProfileDTO(Integer id, String username, String email, String profilePhoto, 
                     Integer raceGameAvatarId, Integer baloonGameAvatarId, Integer hangmanGameAvatarId) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.profilePhoto = profilePhoto;
        this.raceGameAvatarId = raceGameAvatarId;
        this.baloonGameAvatarId = baloonGameAvatarId;
        this.hangmanGameAvatarId = hangmanGameAvatarId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public Integer getRaceGameAvatarId() {
        return raceGameAvatarId;
    }

    public void setRaceGameAvatarId(Integer raceGameAvatarId) {
        this.raceGameAvatarId = raceGameAvatarId;
    }

    public Integer getBaloonGameAvatarId() {
        return baloonGameAvatarId;
    }

    public void setBaloonGameAvatarId(Integer baloonGameAvatarId) {
        this.baloonGameAvatarId = baloonGameAvatarId;
    }

    public Integer getHangmanGameAvatarId() {
        return hangmanGameAvatarId;
    }

    public void setHangmanGameAvatarId(Integer hangmanGameAvatarId) {
        this.hangmanGameAvatarId = hangmanGameAvatarId;
    }
}
package com.tobbathon.mythbusters.model.dto;

import lombok.Data;

//belki işime yarar
@Data
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

}
package com.tobbathon.mythbusters.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ProfileCreateDTO {

    @NotBlank(message = "Kullanıcı adı boş olamaz")
    @Size(min = 3, max = 50, message = "Kullanıcı adı 3-50 karakter olmalı")
    private String username;

    @NotBlank(message = "E-posta boş olamaz")
    @Email(message = "Geçerli bir e-posta adresi girin")
    private String email;

    @NotBlank(message = "Şifre boş olamaz")
    @Size(min = 6, message = "Şifre en az 6 karakter olmalı")
    private String password;

    private String profilePhoto;

    private Integer raceGameAvatarId;
    private Integer baloonGameAvatarId;
    private Integer hangmanGameAvatarId;

    public ProfileCreateDTO(String username, String email, String password, String profilePhoto,
                           Integer raceGameAvatarId, Integer baloonGameAvatarId, Integer hangmanGameAvatarId) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.profilePhoto = profilePhoto;
        this.raceGameAvatarId = raceGameAvatarId;
        this.baloonGameAvatarId = baloonGameAvatarId;
        this.hangmanGameAvatarId = hangmanGameAvatarId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
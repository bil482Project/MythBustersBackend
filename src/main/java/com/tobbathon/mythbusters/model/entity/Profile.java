package com.tobbathon.mythbusters.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;//hassas veri dto'ya eklenmemeli

    @Column(name = "profile_photo")
    private String profilePhoto;

    @ManyToOne
    @JoinColumn(name = "race_game_avatar_id")
    private Avatar raceGameAvatar;

    @ManyToOne
    @JoinColumn(name = "baloon_game_avatar_id")
    private Avatar baloonGameAvatar;

    @ManyToOne
    @JoinColumn(name = "hangman_game_avatar_id")
    private Avatar hangmanGameAvatar;

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
    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    public String getProfilePhoto() {
        return profilePhoto;
    }
    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
    public Avatar getRaceGameAvatar() {
        return raceGameAvatar;
    }
    public void setRaceGameAvatar(Avatar raceGameAvatar) {
        this.raceGameAvatar = raceGameAvatar;
    }
    public Avatar getBaloonGameAvatar() {
        return baloonGameAvatar;
    }
    public void setBaloonGameAvatar(Avatar baloonGameAvatar) {
        this.baloonGameAvatar = baloonGameAvatar;
    }
    public Avatar getHangmanGameAvatar() {
        return hangmanGameAvatar;
    }
    public void setHangmanGameAvatar(Avatar hangmanGameAvatar) {
        this.hangmanGameAvatar = hangmanGameAvatar;
    }

}

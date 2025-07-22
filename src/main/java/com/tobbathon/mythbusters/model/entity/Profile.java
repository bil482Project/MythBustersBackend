package com.tobbathon.mythbusters.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "profile")
@Data
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
    @JoinColumn(name = "balloon_game_avatar_id")
    private Avatar balloonGameAvatar;

    @ManyToOne
    @JoinColumn(name = "hangman_game_avatar_id")
    private Avatar hangmanGameAvatar;

    @Column(name = "coin")
    private Integer coin;
}

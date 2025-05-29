package com.tobbathon.mythbusters.model.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "leaderboard") 
public class Leaderboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    //aynı profileId ile birden fazla satır olabilir mi? if so add @ManyToOne
    @JoinColumn(name = "profile_id")
    private Integer profileId;
    
    @Column(name = "score", nullable = false)
    private int score;

    @Column(name = "game_type", nullable = false)
    private String gameType;

    @Column(name = "date", nullable = false)
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
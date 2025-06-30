package com.tobbathon.mythbusters.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "leaderboard")
@Data
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

}
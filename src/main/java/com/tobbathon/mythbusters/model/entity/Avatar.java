package com.tobbathon.mythbusters.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "avatar")
@Data
public class Avatar {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @Column(name = "name", nullable = false)
	private String name;

    @Column(name = "emoji", nullable = false)
    private String imageUrl;

    @Column(name = "game_type", nullable = false)
    private String gameType;
    
}

package com.tobbathon.mythbusters.repository;

import org.springframework.stereotype.Repository;

import com.tobbathon.mythbusters.model.entity.Game;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    //inherit ettiği bir findAll var zaten
    @Query("SELECT g.name FROM Game g")
    List<String> findAllNames();
}
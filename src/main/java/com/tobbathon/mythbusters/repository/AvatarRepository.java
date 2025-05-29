package com.tobbathon.mythbusters.repository;

import org.springframework.stereotype.Repository;

import com.tobbathon.mythbusters.model.entity.Avatar;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    //inherit ettiği bir findAll var zaten
    @Query("SELECT a.name FROM Avatar a")
    List<String> findAllNames();

    @Query("SELECT a FROM Avatar a WHERE a.gameType = :gameType")
    List<Avatar> findAvatarByGameType(@Param("gameType") String gameType);

    @Query("SELECT a FROM Avatar a WHERE a.id = :id")
    Optional<Avatar> findById(Integer id);
}
//findGameAvatar -> findAvatarByGameType oldu
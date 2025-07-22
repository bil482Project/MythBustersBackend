package com.tobbathon.mythbusters.repository;

import org.springframework.stereotype.Repository;

import com.tobbathon.mythbusters.model.entity.Profile;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{
    //inherit ettiği bir findAll var zaten
    @Query("SELECT p FROM Profile p WHERE p.email = :email")
    Profile findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE Profile p SET p.coin = :coin WHERE p.username = :username")
    void updateCoin(@Param("username") String username, @Param("coin") Integer coin);
}

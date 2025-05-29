package com.tobbathon.mythbusters.repository;

import org.springframework.stereotype.Repository;

import com.tobbathon.mythbusters.model.entity.Profile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{
    //inherit ettiği bir findAll var zaten
}

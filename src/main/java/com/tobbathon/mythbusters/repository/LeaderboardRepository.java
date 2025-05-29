package com.tobbathon.mythbusters.repository;
    
import org.springframework.stereotype.Repository;

import com.tobbathon.mythbusters.model.dto.LeaderboardDTO;
import com.tobbathon.mythbusters.model.entity.Leaderboard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {
    // Önemli: Bu metot, gameType'a göre profilleri sıralar. jpa'da nasıl geçiyorsa variable ismi de o şekilde sorguya eklenir!
    //bu eski hali
    // @Query("SELECT l FROM Leaderboard l WHERE l.gameType = :gameType ORDER BY l.score DESC")
    // List<Leaderboard> orderLeaderboardByGameTypeDesc(@Param("gameType") String gameType);

    //select * from leaderboard join profile p on profile_id=p.id;
    @Query("SELECT NEW com.tobbathon.mythbusters.model.dto.LeaderboardDTO(profileId, username, profilePhoto, score) FROM Leaderboard l JOIN Profile p ON l.profileId=p.id WHERE l.gameType = :gameType ORDER BY l.score DESC")
    List<LeaderboardDTO> orderLeaderboardByGameTypeDesc(@Param("gameType") String gameType);
}

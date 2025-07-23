package com.tobbathon.mythbusters.repository;
    
import org.springframework.stereotype.Repository;

import com.tobbathon.mythbusters.model.dto.LeaderboardDTO;
import com.tobbathon.mythbusters.model.entity.Leaderboard;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {
    // Önemli: Bu metot, gameType'a göre profilleri sıralar. jpa'da nasıl geçiyorsa variable ismi de o şekilde sorguya eklenir!
    //bu eski hali
    // @Query("SELECT l FROM Leaderboard l WHERE l.gameType = :gameType ORDER BY l.score DESC")
    // List<Leaderboard> orderLeaderboardByGameTypeDesc(@Param("gameType") String gameType);

    //select * from leaderboard join profile p on profile_id=p.id;
    @Query("SELECT NEW com.tobbathon.mythbusters.model.dto.LeaderboardDTO(" +
            "p.id, p.username, p.profilePhoto, SUM(l.score)) " +
            "FROM Leaderboard l JOIN Profile p ON l.profileId = p.id " +
            "WHERE l.gameType = :gameType " +
            "GROUP BY p.id, p.username, p.profilePhoto " +
            "ORDER BY SUM(l.score) DESC")
    List<LeaderboardDTO> orderLeaderboardByGameTypeDesc(@Param("gameType") String gameType);

    @Query("SELECT NEW com.tobbathon.mythbusters.model.dto.LeaderboardDTO(" +
            "p.id, p.username, p.profilePhoto, SUM(l.score)) " +
            "FROM Leaderboard l JOIN Profile p ON l.profileId = p.id " +
            "GROUP BY p.id, p.username, p.profilePhoto " +
            "ORDER BY SUM(l.score) DESC")
    List<LeaderboardDTO> findAllLeaderboardDTO();

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO leaderboard (profile_id, score, game_type, date)
        SELECT p.id, :score, :gameType, now()
        FROM profile p
        WHERE p.username = :username
        ON CONFLICT (profile_id, game_type)
        DO UPDATE SET
            score = GREATEST(leaderboard.score, EXCLUDED.score),
            date = CASE 
                WHEN EXCLUDED.score > leaderboard.score THEN now()
                ELSE leaderboard.date
            END;
        """, nativeQuery = true)
    void upsertScoreByUsername(@Param("username") String username,
                            @Param("score") Integer score,
                            @Param("gameType") String gameType);


}

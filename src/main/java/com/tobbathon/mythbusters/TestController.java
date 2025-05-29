package com.tobbathon.mythbusters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tobbathon.mythbusters.model.entity.Avatar;
import com.tobbathon.mythbusters.model.entity.Game;
import com.tobbathon.mythbusters.model.entity.Leaderboard;
import com.tobbathon.mythbusters.model.entity.Profile;
import com.tobbathon.mythbusters.repository.AvatarRepository;
import com.tobbathon.mythbusters.repository.GameRepository;
import com.tobbathon.mythbusters.repository.LeaderboardRepository;
import com.tobbathon.mythbusters.repository.ProfileRepository;

@RestController
public class TestController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private AvatarRepository avatarRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    @GetMapping("/test")
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/testNames")
    public String testDbConnection() {
        return "DB bağlantısı başarılı. Game tablosunda " + gameRepository.findAllNames().toString() + " kayıt var.";
    }

    @GetMapping("/test2")
    public List<Avatar> getAllAvatars() {
        return avatarRepository.findAll();
    }

    //örneğin http://localhost:8080/avatar?gameType=1
    //direkt avatar objesi dönse daha iyi isim de lazım
    // @GetMapping("/avatar")
    // public List<String> getAvatarImageUrlsByGameType(@RequestParam("gameType") String gameType) {
    //     return avatarRepository.findGameAvatar(gameType);
    // }

    // @GetMapping("/profile")
    // public List<Profile> getProfiles() {
    //     return profileRepository.findAll();
    // }

    // @GetMapping("/leaderboard/{game}")
    // public List<Leaderboard> getOrderedLeaderboard(@PathVariable String game) {
    //     return leaderboardRepository.getOrderedDesc(game);
    // }

    // @GetMapping("/leaderboards")
    // public List<Leaderboard> getLeaderboards() {
    //     return leaderboardRepository.findAll();
    // }
}

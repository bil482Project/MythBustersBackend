package com.tobbathon.mythbusters.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tobbathon.mythbusters.model.entity.Avatar;
import com.tobbathon.mythbusters.service.AvatarService;

@RestController
@RequestMapping("/api/avatars")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }
    //örnek: http://localhost:8080/api/avatars?gameType=car
    @GetMapping
    public List<Avatar> findAvatarByGameType(@RequestParam("gameType") String gameType) {
        return avatarService.findAvatarByGameType(gameType);
    }
}

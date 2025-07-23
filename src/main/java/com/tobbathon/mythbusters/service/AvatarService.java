package com.tobbathon.mythbusters.service;

import java.util.List;
import org.springframework.util.StringUtils;

import org.springframework.stereotype.Service;

import com.tobbathon.mythbusters.model.entity.Avatar;
import com.tobbathon.mythbusters.repository.AvatarRepository;

@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;

    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }
 
    public List<Avatar> findAvatarByGameType(String gameType) {
        if (!StringUtils.hasText(gameType)) {
            throw new IllegalArgumentException("Game type must not be empty");
        }
        List<Avatar> avatars = avatarRepository.findAvatarByGameType(gameType);
        return avatars == null ? List.of() : avatars;
    }
}

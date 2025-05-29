package com.tobbathon.mythbusters.service;

import java.util.List;

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
        return avatarRepository.findAvatarByGameType(gameType);
    }
}

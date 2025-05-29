package com.tobbathon.mythbusters.service;

import com.tobbathon.mythbusters.model.entity.Avatar;
import com.tobbathon.mythbusters.model.entity.Profile;
import com.tobbathon.mythbusters.model.dto.ProfileCreateDTO;
import com.tobbathon.mythbusters.model.dto.ProfileDTO;
import com.tobbathon.mythbusters.repository.AvatarRepository;
import com.tobbathon.mythbusters.repository.ProfileRepository;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final AvatarRepository avatarRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileService(ProfileRepository profileRepository, AvatarRepository avatarRepository,
                          PasswordEncoder passwordEncoder) {
        this.profileRepository = profileRepository;
        this.avatarRepository = avatarRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ProfileDTO createProfile(ProfileCreateDTO createDTO) {
        // 1. Şifreyi hash'le
        String hashedPassword = passwordEncoder.encode(createDTO.getPassword());

        // 2. Profile entity oluştur
        Profile profile = new Profile();
        profile.setUsername(createDTO.getUsername());
        profile.setEmail(createDTO.getEmail());
        profile.setPasswordHash(hashedPassword);
        profile.setProfilePhoto(createDTO.getProfilePhoto());

        // 3. Avatar'ları set et (ID'lerden Avatar nesnelerini bul)
        if (createDTO.getRaceGameAvatarId() != null) {
            Avatar raceGameAvatar = avatarRepository.findById(createDTO.getRaceGameAvatarId())
                    .orElseThrow(() -> new IllegalArgumentException("RaceGameAvatar not found"));
            profile.setRaceGameAvatar(raceGameAvatar);
        }
        if (createDTO.getBaloonGameAvatarId() != null) {
            Avatar baloonGameAvatar = avatarRepository.findById(createDTO.getBaloonGameAvatarId())
                    .orElseThrow(() -> new IllegalArgumentException("BaloonGameAvatar not found"));
            profile.setBaloonGameAvatar(baloonGameAvatar);
        }
        if (createDTO.getHangmanGameAvatarId() != null) {
            Avatar hangmanGameAvatar = avatarRepository.findById(createDTO.getHangmanGameAvatarId())
                    .orElseThrow(() -> new IllegalArgumentException("HangmanGameAvatar not found"));
            profile.setHangmanGameAvatar(hangmanGameAvatar);
        }

        // 4. Veritabanına kaydet
        Profile savedProfile = profileRepository.save(profile);

        // 5. Response DTO döndür
        return new ProfileDTO(
                savedProfile.getId(),
                savedProfile.getUsername(),
                savedProfile.getEmail(),
                savedProfile.getProfilePhoto(),
                savedProfile.getRaceGameAvatar() != null ? savedProfile.getRaceGameAvatar().getId() : null,
                savedProfile.getBaloonGameAvatar() != null ? savedProfile.getBaloonGameAvatar().getId() : null,
                savedProfile.getHangmanGameAvatar() != null ? savedProfile.getHangmanGameAvatar().getId() : null
        );
    }
}
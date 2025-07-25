package com.tobbathon.mythbusters.service;

import com.tobbathon.mythbusters.model.dto.LoginRequestDTO;
import com.tobbathon.mythbusters.model.entity.Profile;
import com.tobbathon.mythbusters.model.dto.ProfileCreateDTO;
import com.tobbathon.mythbusters.model.dto.ProfileDTO;
import com.tobbathon.mythbusters.repository.AvatarRepository;
import com.tobbathon.mythbusters.repository.ProfileRepository;

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
        if (createDTO == null || !org.springframework.util.StringUtils.hasText(createDTO.getUsername()) ||
            !org.springframework.util.StringUtils.hasText(createDTO.getEmail()) ||
            !org.springframework.util.StringUtils.hasText(createDTO.getPassword())) {
            throw new IllegalArgumentException("Username, email, and password must not be empty");
        }
        if (createDTO.getCoin() == null || createDTO.getCoin() < 0) {
            throw new IllegalArgumentException("Coin must be a non-negative integer");
        }
        // 1. Şifreyi hash'le
        String hashedPassword = passwordEncoder.encode(createDTO.getPassword());

        // 2. Profile entity oluştur
        Profile profile = new Profile();
        profile.setUsername(createDTO.getUsername());
        profile.setEmail(createDTO.getEmail());
        profile.setPasswordHash(hashedPassword);
        profile.setCoin(createDTO.getCoin());

        // Default kullanıcı profili.
        profile.setProfilePhoto("https://www.w3schools.com/howto/img_avatar.png");

        // Default olarak avatar ekler.
        profile.setRaceGameAvatar(
                avatarRepository.findById(1)
                        .orElseThrow(() -> new IllegalArgumentException("Default race avatar bulunamadı"))
        );
        profile.setBalloonGameAvatar(
                avatarRepository.findById(2)
                        .orElseThrow(() -> new IllegalArgumentException("Default balloon avatar bulunamadı"))
        );
        profile.setHangmanGameAvatar(
                avatarRepository.findById(3)
                        .orElseThrow(() -> new IllegalArgumentException("Default hangman avatar bulunamadı"))
        );

        // 4. Veritabanına kaydet
        Profile savedProfile = profileRepository.save(profile);

        // 5. Response DTO döndür
        return new ProfileDTO(
                savedProfile.getId(),
                savedProfile.getUsername(),
                savedProfile.getEmail(),
                savedProfile.getProfilePhoto(),
                savedProfile.getRaceGameAvatar().getId(),
                savedProfile.getBalloonGameAvatar().getId(),
                savedProfile.getHangmanGameAvatar().getId(),
                savedProfile.getCoin()
        );
    }

    public ProfileDTO login(LoginRequestDTO loginRequestDTO) {
        if (loginRequestDTO == null || !org.springframework.util.StringUtils.hasText(loginRequestDTO.getEmail()) ||
            !org.springframework.util.StringUtils.hasText(loginRequestDTO.getPassword())) {
            throw new IllegalArgumentException("Email and password must not be empty");
        }
        // 1. Kullanıcıyı email ile bul
        Profile profile = profileRepository.findByEmail(loginRequestDTO.getEmail());
        if (profile == null) {
            throw new IllegalArgumentException("User not found with email: " + loginRequestDTO.getEmail());
        }
        // 2. Şifreyi kontrol et
        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), profile.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        // 3. DTO olarak dön
        return new ProfileDTO(
                profile.getId(),
                profile.getUsername(),
                profile.getEmail(),
                profile.getProfilePhoto(),
                profile.getRaceGameAvatar().getId(),
                profile.getBalloonGameAvatar().getId(),
                profile.getHangmanGameAvatar().getId(),
                profile.getCoin()
        );
    }

    public void updateCoin(String username, Integer coin) {
        if (!org.springframework.util.StringUtils.hasText(username)) {
            throw new IllegalArgumentException("Username must not be empty");
        }
        if (coin == null || coin < 0) {
            throw new IllegalArgumentException("Coin must be a non-negative integer");
        }
        profileRepository.updateCoin(username, coin);
    }
}
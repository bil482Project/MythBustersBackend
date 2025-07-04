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
        // 1. Şifreyi hash'le
        String hashedPassword = passwordEncoder.encode(createDTO.getPassword());

        // 2. Profile entity oluştur
        Profile profile = new Profile();
        profile.setUsername(createDTO.getUsername());
        profile.setEmail(createDTO.getEmail());
        profile.setPasswordHash(hashedPassword);

        // Default kullanıcı profili.
        profile.setProfilePhoto("https://www.w3schools.com/howto/img_avatar.png");

        // Default olarak avatar ekler.
        profile.setRaceGameAvatar(
                avatarRepository.findById(1)
                        .orElseThrow(() -> new IllegalArgumentException("Default race avatar bulunamadı"))
        );
        profile.setBaloonGameAvatar(
                avatarRepository.findById(3)
                        .orElseThrow(() -> new IllegalArgumentException("Default baloon avatar bulunamadı"))
        );
        profile.setHangmanGameAvatar(
                avatarRepository.findById(5)
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
                savedProfile.getBaloonGameAvatar().getId(),
                savedProfile.getHangmanGameAvatar().getId()
        );
    }

    public ProfileDTO login(LoginRequestDTO loginRequestDTO) {
        // 1. Kullanıcıyı email ile bul
        Profile profile = profileRepository.findByEmail(loginRequestDTO.getEmail());

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
                profile.getBaloonGameAvatar().getId(),
                profile.getHangmanGameAvatar().getId()
        );
    }
}
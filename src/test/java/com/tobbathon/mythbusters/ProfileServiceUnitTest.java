package com.tobbathon.mythbusters;

import com.tobbathon.mythbusters.model.dto.LoginRequestDTO;
import com.tobbathon.mythbusters.model.dto.ProfileCreateDTO;
import com.tobbathon.mythbusters.model.dto.ProfileDTO;
import com.tobbathon.mythbusters.model.entity.Profile;
import com.tobbathon.mythbusters.repository.AvatarRepository;
import com.tobbathon.mythbusters.repository.ProfileRepository;
import com.tobbathon.mythbusters.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceUnitTest {
    @Mock
    private ProfileRepository profileRepository;
    @Mock
    private AvatarRepository avatarRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private ProfileService profileService;

    /**
     * Test service method for creating a profile.
     * Verifies that the returned DTO matches the saved profile.
     */
    @Test
    void testCreateProfile_ReturnsProfileDTO() {
        ProfileCreateDTO createDTO = new ProfileCreateDTO.Builder()
                .username("user1")
                .email("user1@email.com")
                .password("pass123")
                .coin(150)
                .build();
        Profile profile = mock(Profile.class);
        when(passwordEncoder.encode("pass123")).thenReturn("hashed");
        when(avatarRepository.findById(1)).thenReturn(Optional.of(mock(com.tobbathon.mythbusters.model.entity.Avatar.class)));
        when(avatarRepository.findById(2)).thenReturn(Optional.of(mock(com.tobbathon.mythbusters.model.entity.Avatar.class)));
        when(avatarRepository.findById(3)).thenReturn(Optional.of(mock(com.tobbathon.mythbusters.model.entity.Avatar.class)));
        when(profileRepository.save(any(Profile.class))).thenReturn(profile);
        when(profile.getId()).thenReturn(1);
        when(profile.getUsername()).thenReturn("user1");
        when(profile.getEmail()).thenReturn("user1@email.com");
        when(profile.getProfilePhoto()).thenReturn("photo.png");
        when(profile.getRaceGameAvatar()).thenReturn(mock(com.tobbathon.mythbusters.model.entity.Avatar.class));
        when(profile.getBalloonGameAvatar()).thenReturn(mock(com.tobbathon.mythbusters.model.entity.Avatar.class));
        when(profile.getHangmanGameAvatar()).thenReturn(mock(com.tobbathon.mythbusters.model.entity.Avatar.class));
        when(profile.getRaceGameAvatar().getId()).thenReturn(1);
        when(profile.getBalloonGameAvatar().getId()).thenReturn(2);
        when(profile.getHangmanGameAvatar().getId()).thenReturn(3);
        when(profile.getCoin()).thenReturn(150);

        ProfileDTO result = profileService.createProfile(createDTO);
        assertEquals("user1", result.getUsername());
        assertEquals("user1@email.com", result.getEmail());
        assertEquals(1, result.getRaceGameAvatarId());
        assertEquals(2, result.getBalloonGameAvatarId());
        assertEquals(3, result.getHangmanGameAvatarId());
        assertEquals(150, result.getCoin());
    }

    /**
     * Test service method for login with valid credentials.
     * Verifies that the returned DTO matches the found profile.
     */
    @Test
    void testLogin_ReturnsProfileDTO() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setEmail("user1@email.com");
        loginRequestDTO.setPassword("pass123");
        Profile profile = mock(Profile.class);
        when(profileRepository.findByEmail("user1@email.com")).thenReturn(profile);
        when(passwordEncoder.matches("pass123", null)).thenReturn(true);
        when(profile.getId()).thenReturn(1);
        when(profile.getUsername()).thenReturn("user1");
        when(profile.getEmail()).thenReturn("user1@email.com");
        when(profile.getProfilePhoto()).thenReturn("photo.png");
        when(profile.getRaceGameAvatar()).thenReturn(mock(com.tobbathon.mythbusters.model.entity.Avatar.class));
        when(profile.getBalloonGameAvatar()).thenReturn(mock(com.tobbathon.mythbusters.model.entity.Avatar.class));
        when(profile.getHangmanGameAvatar()).thenReturn(mock(com.tobbathon.mythbusters.model.entity.Avatar.class));
        when(profile.getRaceGameAvatar().getId()).thenReturn(1);
        when(profile.getBalloonGameAvatar().getId()).thenReturn(2);
        when(profile.getHangmanGameAvatar().getId()).thenReturn(3);
        when(profile.getCoin()).thenReturn(50);

        ProfileDTO result = profileService.login(loginRequestDTO);
        assertEquals("user1", result.getUsername());
        assertEquals("user1@email.com", result.getEmail());
        assertEquals(1, result.getRaceGameAvatarId());
        assertEquals(2, result.getBalloonGameAvatarId());
        assertEquals(3, result.getHangmanGameAvatarId());
        assertEquals(50, result.getCoin());
    }

    /**
     * Test service method for login with invalid credentials.
     * Verifies that an IllegalArgumentException is thrown.
     */
    @Test
    void testLogin_InvalidCredentialsThrowsException() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setEmail("user1@email.com");
        loginRequestDTO.setPassword("wrongpass");
        Profile profile = mock(Profile.class);
        when(profileRepository.findByEmail("user1@email.com")).thenReturn(profile);
        when(passwordEncoder.matches("wrongpass", null)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> profileService.login(loginRequestDTO));
    }

    /**
     * Test service method for updating coin.
     * Verifies that the repository method is called with correct arguments.
     */
    @Test
    void testUpdateCoin_CallsRepositoryUpdate() {
        profileService.updateCoin("user1", 100);
        verify(profileRepository, times(1)).updateCoin("user1", 100);
    }
}

package com.tobbathon.mythbusters;

import com.tobbathon.mythbusters.controller.ProfileController;
import com.tobbathon.mythbusters.model.dto.LoginRequestDTO;
import com.tobbathon.mythbusters.model.dto.ProfileCreateDTO;
import com.tobbathon.mythbusters.model.dto.ProfileDTO;
import com.tobbathon.mythbusters.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ProfileControllerTest {
    @Mock
    private ProfileService profileService;
    @InjectMocks
    private ProfileController profileController;

    /**
     * Test POST /api/profiles endpoint for creating a profile.
     * Verifies that the service is called and returns 200 OK.
     */
    @Test
    void testCreateProfileEndpoint() throws Exception {
        // Setup MockMvc for controller testing
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();
        // Prepare profile creation DTO and expected response
        ProfileCreateDTO createDTO = new ProfileCreateDTO.Builder()
                .username("user1")
                .email("user1@email.com")
                .password("pass123")
                .coin(50)
                .build();
        ProfileDTO profileDTO = new ProfileDTO(1, "user1", "user1@email.com", "photo.png", 1, 2, 3, 50);
        // Mock service response
        when(profileService.createProfile(any(ProfileCreateDTO.class))).thenReturn(profileDTO);
        String json = "{\"username\":\"user1\",\"email\":\"user1@email.com\",\"password\":\"pass123\",\"coin\":50}";
        // Perform POST request and verify status
        mockMvc.perform(post("/api/profiles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
        // Verify service method was called
        verify(profileService, times(1)).createProfile(any(ProfileCreateDTO.class));
    }

    /**
     * Test POST /api/profiles/login endpoint for user login.
     * Verifies that the service is called and returns 200 OK.
     */
    @Test
    void testLoginEndpoint() throws Exception {
        // Setup MockMvc for controller testing
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();
        // Prepare login DTO and expected response
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        ProfileDTO profileDTO = new ProfileDTO(1, "user1", "user1@email.com", "photo.png", 1, 2, 3, 50);
        // Mock service response
        when(profileService.login(any(LoginRequestDTO.class))).thenReturn(profileDTO);
        String json = "{\"username\":\"user1\",\"password\":\"pass123\"}";
        // Perform POST request and verify status
        mockMvc.perform(post("/api/profiles/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
        // Verify service method was called
        verify(profileService, times(1)).login(any(LoginRequestDTO.class));
    }
}

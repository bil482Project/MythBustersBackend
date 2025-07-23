package com.tobbathon.mythbusters;

import com.tobbathon.mythbusters.controller.LeaderboardController;
import com.tobbathon.mythbusters.model.dto.LeaderboardDTO;
import com.tobbathon.mythbusters.model.dto.AddLeaderboardDTO;
import com.tobbathon.mythbusters.service.LeaderboardService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class LeaderboardControllerTest {

    @Mock
    private LeaderboardService leaderboardService;

    @InjectMocks
    private LeaderboardController leaderboardController;

    /**
     * Test GET /api/leaderboard endpoint with gameType parameter.
     * Verifies that the service is called and returns 200 OK.
     */
    @Test
    void testOrderProfilesByGameTypeDescEndpoint() throws Exception {
        // Setup MockMvc for controller testing
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(leaderboardController).build();
        // Prepare mock leaderboard data
        LeaderboardDTO dto1 = new LeaderboardDTO(1, "user1", "photo1.png", 100L);
        LeaderboardDTO dto2 = new LeaderboardDTO(2, "user2", "photo2.png", 80L);
        List<LeaderboardDTO> mockList = Arrays.asList(dto1, dto2);
        // Mock service response
        when(leaderboardService.orderLeaderboardByGameTypeDesc("car")).thenReturn(mockList);

        // Perform GET request and verify status
        mockMvc.perform(get("/api/leaderboard")
                .param("gameType", "car")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // Verify service method was called
        verify(leaderboardService, times(1)).orderLeaderboardByGameTypeDesc("car");
    }

    /**
     * Test POST /api/leaderboard/newPoint/{username} endpoint.
     * Verifies that the service is called and returns 200 OK.
     */
    @Test
    void testAddNewPointEndpoint() throws Exception {
        // Setup MockMvc for controller testing
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(leaderboardController).build();
        // Prepare request body JSON
        AddLeaderboardDTO addLeaderboardDTO = new AddLeaderboardDTO("car", 120);
        String json = "{\"score\":120,\"gameType\":\"car\"}";

        // Perform POST request and verify status
        mockMvc.perform(post("/api/leaderboard/newPoint/user1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
        // Verify service method was called
        verify(leaderboardService, times(1)).addNewPoint("user1", 120, "car");
    }
}

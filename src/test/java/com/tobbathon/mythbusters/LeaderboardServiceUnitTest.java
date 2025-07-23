package com.tobbathon.mythbusters;

import com.tobbathon.mythbusters.model.dto.LeaderboardDTO;
import com.tobbathon.mythbusters.repository.LeaderboardRepository;
import com.tobbathon.mythbusters.service.LeaderboardService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LeaderboardServiceUnitTest {

    @Mock
    private LeaderboardRepository leaderboardRepository;

    @InjectMocks
    private LeaderboardService leaderboardService;

    /**
     * Test service method for ordering leaderboard by game type.
     * Verifies that the returned list matches the mocked data.
     */
    @Test
    void testOrderLeaderboardByGameTypeDesc_ReturnsLeaderboardDTOList() {
        // Prepare mock leaderboard data
        LeaderboardDTO dto1 = new LeaderboardDTO(1, "user1", "photo1.png", 100L);
        LeaderboardDTO dto2 = new LeaderboardDTO(2, "user2", "photo2.png", 80L);
        List<LeaderboardDTO> mockList = Arrays.asList(dto1, dto2);
        // Mock repository response
        when(leaderboardRepository.orderLeaderboardByGameTypeDesc("car")).thenReturn(mockList);

        // Call service and verify results
        List<LeaderboardDTO> result = leaderboardService.orderLeaderboardByGameTypeDesc("car");
        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals(100L, result.get(0).getScore());
    }

    /**
     * Test service method for adding a new point.
     * Verifies that the repository method is called with correct arguments.
     */
    @Test
    void testAddNewPoint_CallsRepositoryUpsert() {
        // Call service method
        leaderboardService.addNewPoint("user1", 120, "car");
        // Verify repository method was called
        verify(leaderboardRepository, times(1)).upsertScoreByUsername("user1", 120, "car");
    }

    /**
     * Test service method for invalid game type.
     * Verifies that an IllegalArgumentException is thrown.
     */
    @Test
    void testOrderLeaderboardByGameTypeDesc_InvalidGameTypeThrowsException() {
        // Mock repository to throw exception
        lenient().when(leaderboardRepository.orderLeaderboardByGameTypeDesc("car")).thenThrow(new IllegalArgumentException("Invalid game type"));
        // Verify exception is thrown by service
        assertThrows(IllegalArgumentException.class, () -> leaderboardService.orderLeaderboardByGameTypeDesc("invalid"));
    }
}

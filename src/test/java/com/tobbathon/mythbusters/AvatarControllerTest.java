package com.tobbathon.mythbusters;

import com.tobbathon.mythbusters.controller.AvatarController;
import com.tobbathon.mythbusters.model.entity.Avatar;
import com.tobbathon.mythbusters.service.AvatarService;
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
public class AvatarControllerTest {
    @Mock
    private AvatarService avatarService;
    @InjectMocks
    private AvatarController avatarController;

    /**
     * Test GET /api/avatars endpoint with gameType parameter.
     * Verifies that the service is called and returns 200 OK.
     */
    @Test
    void testFindAvatarByGameTypeEndpoint() throws Exception {
        // Setup MockMvc for controller testing
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(avatarController).build();
        // Prepare mock avatar data
        Avatar avatar1 = mock(Avatar.class);
        Avatar avatar2 = mock(Avatar.class);
        List<Avatar> mockList = Arrays.asList(avatar1, avatar2);
        // Mock service response
        when(avatarService.findAvatarByGameType("car")).thenReturn(mockList);
        // Perform GET request and verify status
        mockMvc.perform(get("/api/avatars")
                .param("gameType", "car")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // Verify service method was called
        verify(avatarService, times(1)).findAvatarByGameType("car");
    }
}

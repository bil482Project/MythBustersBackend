package com.tobbathon.mythbusters;

import com.tobbathon.mythbusters.model.entity.Avatar;
import com.tobbathon.mythbusters.repository.AvatarRepository;
import com.tobbathon.mythbusters.service.AvatarService;
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
public class AvatarServiceUnitTest {
    @Mock
    private AvatarRepository avatarRepository;
    @InjectMocks
    private AvatarService avatarService;

    /**
     * Test service method for finding avatars by game type.
     * Verifies that the returned list matches the mocked data.
     */
    @Test
    void testFindAvatarByGameType_ReturnsAvatarList() {
        Avatar avatar1 = mock(Avatar.class);
        Avatar avatar2 = mock(Avatar.class);
        List<Avatar> mockList = Arrays.asList(avatar1, avatar2);
        when(avatarRepository.findAvatarByGameType("car")).thenReturn(mockList);

        List<Avatar> result = avatarService.findAvatarByGameType("car");
        assertEquals(2, result.size());
        assertSame(avatar1, result.get(0));
        assertSame(avatar2, result.get(1));
    }

    /**
     * Test service method for empty result.
     * Verifies that an empty list is returned when no avatars are found.
     */
    @Test
    void testFindAvatarByGameType_ReturnsEmptyList() {
        when(avatarRepository.findAvatarByGameType("unknown")).thenReturn(Arrays.asList());
        List<Avatar> result = avatarService.findAvatarByGameType("unknown");
        assertTrue(result.isEmpty());
    }
}

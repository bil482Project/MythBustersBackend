package com.tobbathon.mythbusters;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;
import com.tobbathon.mythbusters.controller.LeaderboardController;
import com.tobbathon.mythbusters.controller.ProfileController;
import com.tobbathon.mythbusters.controller.AvatarController;
import com.tobbathon.mythbusters.service.LeaderboardService;
import com.tobbathon.mythbusters.service.ProfileService;
import com.tobbathon.mythbusters.service.AvatarService;

@SpringBootTest
class MythbustersApplicationTests {

	@Autowired
	private LeaderboardController leaderboardController;
	@Autowired
	private ProfileController profileController;
	@Autowired
	private AvatarController avatarController;
	@Autowired
	private LeaderboardService leaderboardService;
	@Autowired
	private ProfileService profileService;
	@Autowired
	private AvatarService avatarService;

	/**
	 * Test that the Spring application context loads and all main beans are present.
	 * Verifies that controllers and services are autowired and not null.
	 */
	@Test
	void contextLoads() {
		// Application context should load and beans should not be null
		assertNotNull(leaderboardController);
		assertNotNull(profileController);
		assertNotNull(avatarController);
		assertNotNull(leaderboardService);
		assertNotNull(profileService);
		assertNotNull(avatarService);
	}
}

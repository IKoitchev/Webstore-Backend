package com.store.VideoGameStore;

import com.store.VideoGameStore.controllers.GameController;
import com.store.VideoGameStore.controllers.GameControllerTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		GameControllerTest.class})
class VideoGameStoreApplicationTests {

	@Test
	void contextLoads() {
	}

}

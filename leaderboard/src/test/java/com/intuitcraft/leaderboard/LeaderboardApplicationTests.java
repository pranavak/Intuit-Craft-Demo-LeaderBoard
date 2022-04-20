package com.intuitcraft.leaderboard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intuitcraft.leaderboard.entity.playerScore;
import com.intuitcraft.leaderboard.exceptions.CacheInitializationException;
import com.intuitcraft.leaderboard.exceptions.LeaderboardNotInitializedException;
import com.intuitcraft.leaderboard.exceptions.MessageQueueFailureException;
import com.intuitcraft.leaderboard.repository.playerScoreRepository;
import com.intuitcraft.leaderboard.services.cacheService;
import com.intuitcraft.leaderboard.services.leaderBoardService;
import com.intuitcraft.leaderboard.services.client.newDataProducerService;

@SpringBootTest(classes = LeaderboardApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class LeaderboardApplicationTests {

	@Autowired
	leaderBoardService leaderBoard;
	
	@Autowired
	newDataProducerService<playerScore> producer;
	
	@Autowired
	cacheService<playerScore> cacheServiceTest;
	
	@Autowired
	playerScoreRepository scoreRepository;
	
	@Test
	public void allServiceIntegrityTest() throws InterruptedException {
		try {
			leaderBoard.createBoard(5);
		} catch (CacheInitializationException e) {
			fail(e.getMessage());
		} catch (LeaderboardNotInitializedException e) {
			fail(e.getMessage());
		}
		try {
			producer.addDataToQueue(new playerScore("Goutam", 2000));
		} catch (MessageQueueFailureException e) {
			fail(e.getMessage());
		}
		Thread.sleep(5);
		try {
			playerScore[] outputList = {new playerScore("Goutam", 2000),
					new playerScore("IS", 500), new playerScore("RP", 200), new playerScore("GB", 100),
					new playerScore("IM", 10)};
			int i = 0;
			for (playerScore p : leaderBoard.getTopNPlayers()) {
				assertEquals(outputList[i++], p);
			}
		} catch (LeaderboardNotInitializedException e) {
			fail(e.getMessage());
		}
	}
	
	@After
	public void tearDown() {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scoreRepository.deleteAll();
	}

}

package com.intuitcraft.leaderboard;

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
			for (playerScore p : leaderBoard.getTopNPlayers()) {
				System.out.println(p);
			}
		} catch (LeaderboardNotInitializedException e) {
			fail(e.getMessage());
		}
		//assertEquals(outputList, cacheServiceTest.getTopNplayers());
	}
	
	@After
	public void tearDown() {
		scoreRepository.deleteAll();
	}

}

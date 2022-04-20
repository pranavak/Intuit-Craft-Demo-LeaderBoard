package com.intuitcraft.leaderboard;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intuitcraft.leaderboard.entity.playerScore;
import com.intuitcraft.leaderboard.exceptions.CacheInitializationException;
import com.intuitcraft.leaderboard.exceptions.LeaderboardNotInitializedException;
import com.intuitcraft.leaderboard.exceptions.LeaderboardUpdateFailureException;
import com.intuitcraft.leaderboard.repository.playerScoreRepository;
import com.intuitcraft.leaderboard.services.leaderBoardService;
import com.intuitcraft.leaderboard.services.scoreIngestionServiceImpl;

@SpringBootTest(classes = LeaderboardApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class scoreIngestorTest {
	@Autowired
	leaderBoardService leaderBoard;
	@Autowired
	scoreIngestionServiceImpl scoreIngestor;
	@Autowired
	playerScoreRepository scoreRepository;
	
	@Test
	public void test() {
		try {
			try {
				scoreIngestor.publish(new playerScore("OP", 700));
			} catch (LeaderboardUpdateFailureException e) {
				fail(e.getMessage());
			}
			for (playerScore p : leaderBoard.getTopNPlayers())
				System.out.println(p);
			try {
				leaderBoard.createBoard(3);
			} catch (CacheInitializationException e) {
				fail(e.getMessage());
			}
			try {
				scoreIngestor.publish(new playerScore("OP", 600));
				scoreIngestor.publish(new playerScore("GB", 700));
			} catch (LeaderboardUpdateFailureException e) {
				fail(e.getMessage());
			}
			
		
			for (playerScore p : leaderBoard.getTopNPlayers())
				System.out.println(p);
		} catch (LeaderboardNotInitializedException e) {
			fail(e.getMessage());
		}
		
	}

}

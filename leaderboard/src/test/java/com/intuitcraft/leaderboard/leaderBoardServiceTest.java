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
import com.intuitcraft.leaderboard.repository.playerScoreRepository;
import com.intuitcraft.leaderboard.services.leaderBoardService;

@SpringBootTest(classes = LeaderboardApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class leaderBoardServiceTest {

	@Autowired
	leaderBoardService leaderBoard;
	
	@Autowired
	playerScoreRepository scoreRepository;
	
	@Test
	public void test() {
		try {
			int i = 0;
			playerScore[] outputList = { new playerScore("OP", 700), new playerScore("RP", 200), new playerScore("GB", 100)};
			leaderBoard.createBoard(3);
			for (playerScore p : leaderBoard.getTopNPlayers()) {
				assertEquals(p, outputList[i++]);
			}
				
			leaderBoard.publish(new playerScore("GB", 5000));
			outputList[0] = new playerScore("GB", 5000);
			outputList[1] = new playerScore("IS", 500);
			outputList[2] = new playerScore("RP", 200);
			i = 0;
			Thread.sleep(20);
			for (playerScore p : leaderBoard.getTopNPlayers()) {
				assertEquals(p, outputList[i++]);
			}
		} catch (Exception e) {
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

package com.intuitcraft.leaderboard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intuitcraft.leaderboard.entity.playerScore;
import com.intuitcraft.leaderboard.services.leaderBoardService;

@SpringBootTest(classes = LeaderboardApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class leaderBoardServiceTest {

	@Autowired
	leaderBoardService leaderBoard;
	
	@Test
	public void test() {
		
		leaderBoard.createBoard(3);
		for (playerScore p : leaderBoard.getTopNPlayers())
			System.out.println(p);
		leaderBoard.publish(new playerScore("GB", 10));
		System.out.println("************");
		for (playerScore p : leaderBoard.getTopNPlayers())
			System.out.println(p);
	}

}

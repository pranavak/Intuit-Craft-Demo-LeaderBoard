package com.intuitcraft.leaderboard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intuitcraft.leaderboard.entity.playerScore;
import com.intuitcraft.leaderboard.services.cacheService;

@SpringBootTest(classes = LeaderboardApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class LeaderboardApplicationTests {

	@Autowired
	cacheService<playerScore> cacheServiceTest;
	
	@Test
	public void contextLoads() {
		playerScore p1 = new playerScore("GB", 100);
		playerScore p2 = new playerScore("RP", 200);
		playerScore p3 = new playerScore("IS", 300);
		playerScore p4 = new playerScore("IM", 270);
		List<playerScore> inputList = new ArrayList<playerScore>();
		inputList.add(p1);
		inputList.add(p2);
		inputList.add(p3);
		inputList.add(p4);
		List<playerScore> outputList = new ArrayList<playerScore>();
		outputList.add(p3);
		outputList.add(p4);
		outputList.add(p2);
		cacheServiceTest.initialize(3, inputList);
		assertEquals(outputList, cacheServiceTest.getTopNplayers());
	}

}
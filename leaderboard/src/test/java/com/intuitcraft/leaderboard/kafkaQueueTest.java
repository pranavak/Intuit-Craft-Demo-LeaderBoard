package com.intuitcraft.leaderboard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intuitcraft.leaderboard.entity.playerScore;
import com.intuitcraft.leaderboard.services.newDataConsumerService;
import com.intuitcraft.leaderboard.services.client.newDataProducerService;

@SpringBootTest(classes = LeaderboardApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class kafkaQueueTest {

	@Autowired
	newDataProducerService<playerScore> producer;

	@Test
	public void test() {
		producer.addDataToQueue(new playerScore("GB", 100));
	}

}

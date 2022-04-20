package com.intuitcraft.leaderboard;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intuitcraft.leaderboard.entity.playerScore;
import com.intuitcraft.leaderboard.exceptions.MessageQueueFailureException;
import com.intuitcraft.leaderboard.services.client.newDataProducerService;

@SpringBootTest(classes = LeaderboardApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class kafkaQueueTest {

	@Autowired
	newDataProducerService<playerScore> producer;

	@Test
	public void test() {
		try {
			producer.addDataToQueue(new playerScore("GB", 100));
		} catch (MessageQueueFailureException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
	}
}

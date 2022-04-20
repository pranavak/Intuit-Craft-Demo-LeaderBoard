package com.intuitcraft.leaderboard.services.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.intuitcraft.leaderboard.constants.constants;
import com.intuitcraft.leaderboard.entity.playerScore;

@Service
public class newScoreProducerServiceImpl implements newDataProducerService<playerScore> {
	
	@Autowired
	private KafkaTemplate<String, playerScore> kafkaTemplate;

	public void addDataToQueue(playerScore newScore) {
		kafkaTemplate.send(constants.KafkaTopic, newScore);
		/*for (int i = 0; i < 20; i++)
			System.out.println("Sent message " + newScore);*/
	}

}

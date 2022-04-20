package com.intuitcraft.leaderboard.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.intuitcraft.leaderboard.constants.constants;
import com.intuitcraft.leaderboard.entity.playerScore;

@Service
public class newScoreConsumerService implements newDataConsumerService<playerScore> {

	@KafkaListener(topics = constants.KafkaTopic, groupId = constants.kafkaGroupId)
	public void consumeDataFromQueue(playerScore newData) {
		//for (int i = 0; i < 10; i++)
			System.out.println(newData);
	}

}
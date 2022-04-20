package com.intuitcraft.leaderboard.services.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.intuitcraft.leaderboard.constants.constants;
import com.intuitcraft.leaderboard.entity.playerScore;
import com.intuitcraft.leaderboard.exceptions.MessageQueueFailureException;

@Service
public class newScoreProducerServiceImpl implements newDataProducerService<playerScore> {
	
	@Autowired
	private KafkaTemplate<String, playerScore> kafkaTemplate;

	public void addDataToQueue(playerScore newScore) throws MessageQueueFailureException {
		try {
			kafkaTemplate.send(constants.KAFKA_TOPIC, newScore);
		} catch (Exception e) {
			throw new MessageQueueFailureException(e.getMessage());
		}
		
	}

}

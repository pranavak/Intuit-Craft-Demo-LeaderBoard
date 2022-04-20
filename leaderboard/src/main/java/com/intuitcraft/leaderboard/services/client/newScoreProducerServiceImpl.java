package com.intuitcraft.leaderboard.services.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.intuitcraft.leaderboard.constants.constants;
import com.intuitcraft.leaderboard.entity.playerScore;
import com.intuitcraft.leaderboard.exceptions.MessageQueueFailureException;

@Service
public class newScoreProducerServiceImpl implements newDataProducerService<playerScore> {
	
	Logger logger = LoggerFactory.getLogger(newScoreProducerServiceImpl.class);
	
	@Autowired
	private KafkaTemplate<String, playerScore> kafkaTemplate;

	public void addDataToQueue(playerScore newScore) throws MessageQueueFailureException {
		try {
			kafkaTemplate.send(constants.KAFKA_TOPIC, newScore);
		} catch (Exception e) {
			logger.error("Send message failed - " + e.getMessage());
			throw new MessageQueueFailureException(e.getMessage());
		}
		
	}

}

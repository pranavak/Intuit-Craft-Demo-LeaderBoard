package com.intuitcraft.leaderboard.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.intuitcraft.leaderboard.constants.constants;
import com.intuitcraft.leaderboard.entity.playerScore;

@Service
public class newScoreConsumerService implements newDataConsumerService<playerScore> {
	
	@Autowired
	scoreIngestionService scoreIngestor;
	
	Logger logger = LoggerFactory.getLogger(newScoreConsumerService.class);

	@KafkaListener(topics = constants.KAFKA_TOPIC, groupId = constants.KAFKA_GROUP_ID)
	public void consumeDataFromQueue(playerScore newData) {
		try {
			scoreIngestor.publish(newData);
		} catch (Exception e) {
			logger.error("Could not publish new score - " + e.getMessage());
			return;
		}
		logger.debug("Published " + newData);	
	}

}

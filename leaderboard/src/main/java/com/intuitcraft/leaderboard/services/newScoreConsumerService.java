package com.intuitcraft.leaderboard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.intuitcraft.leaderboard.constants.constants;
import com.intuitcraft.leaderboard.entity.playerScore;
import com.intuitcraft.leaderboard.exceptions.LeaderboardUpdateFailureException;

@Service
public class newScoreConsumerService implements newDataConsumerService<playerScore> {
	
	@Autowired
	scoreIngestionService scoreIngestor;

	@KafkaListener(topics = constants.KAFKA_TOPIC, groupId = constants.KAFKA_GROUP_ID)
	public void consumeDataFromQueue(playerScore newData) {
		//for (int i = 0; i < 10; i++)
		try {
			scoreIngestor.publish(newData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Published " + newData);
			//System.out.println(newData);
		
	}

}

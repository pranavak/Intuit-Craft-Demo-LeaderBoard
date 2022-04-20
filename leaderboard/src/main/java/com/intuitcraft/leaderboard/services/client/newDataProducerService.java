package com.intuitcraft.leaderboard.services.client;

import com.intuitcraft.leaderboard.entity.playerScore;
import com.intuitcraft.leaderboard.exceptions.MessageQueueFailureException;

public interface newDataProducerService<T> {
	public void addDataToQueue(T newData) throws MessageQueueFailureException;
}

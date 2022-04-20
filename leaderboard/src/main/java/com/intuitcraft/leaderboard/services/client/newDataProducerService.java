package com.intuitcraft.leaderboard.services.client;

import com.intuitcraft.leaderboard.entity.playerScore;

public interface newDataProducerService<T> {
	public void addDataToQueue(T newData);
}

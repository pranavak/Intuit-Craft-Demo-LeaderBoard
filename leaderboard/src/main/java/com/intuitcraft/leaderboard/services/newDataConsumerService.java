package com.intuitcraft.leaderboard.services;

public interface newDataConsumerService<T> {
	public void consumeDataFromQueue(T newData);
}

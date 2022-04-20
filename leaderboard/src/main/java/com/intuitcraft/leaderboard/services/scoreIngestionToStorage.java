package com.intuitcraft.leaderboard.services;

import com.intuitcraft.leaderboard.entity.playerScore;
import com.intuitcraft.leaderboard.exceptions.DatabaseStorageException;

public interface scoreIngestionToStorage {
	public void publishToStore(playerScore newScore) throws DatabaseStorageException;
}

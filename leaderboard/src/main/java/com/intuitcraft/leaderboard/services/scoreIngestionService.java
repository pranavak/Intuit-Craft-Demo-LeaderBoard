package com.intuitcraft.leaderboard.services;

import com.intuitcraft.leaderboard.entity.playerScore;
import com.intuitcraft.leaderboard.exceptions.DatabaseStorageException;
import com.intuitcraft.leaderboard.exceptions.LeaderboardUpdateFailureException;

public interface scoreIngestionService {
	public void publish(playerScore newScore) throws LeaderboardUpdateFailureException, DatabaseStorageException;
}

package com.intuitcraft.leaderboard.services;

import com.intuitcraft.leaderboard.entity.playerScore;

public interface scoreIngestionToStorage {
	public void publishToStore(playerScore newScore);
}

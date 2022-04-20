package com.intuitcraft.leaderboard.services;

import com.intuitcraft.leaderboard.entity.playerScore;

public interface scoreIngestionService {
	public void publish(playerScore newScore);
}

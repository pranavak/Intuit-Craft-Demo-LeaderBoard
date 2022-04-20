package com.intuitcraft.leaderboard.services;

import java.util.List;

import com.intuitcraft.leaderboard.entity.playerScore;
import com.intuitcraft.leaderboard.exceptions.LeaderboardNotInitialized;

public interface leaderBoardService {
	public void createBoard(int topN);
	public List<playerScore> getTopNPlayers() throws LeaderboardNotInitialized;
	public void publish(playerScore newScore);
}

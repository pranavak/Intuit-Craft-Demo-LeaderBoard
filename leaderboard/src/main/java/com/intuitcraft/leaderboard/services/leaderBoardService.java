package com.intuitcraft.leaderboard.services;

import java.util.List;

import com.intuitcraft.leaderboard.entity.playerScore;

public interface leaderBoardService {
	public void createBoard(int topN);
	public List<playerScore> getTopNPlayers();
	public void publish(playerScore newScore);
}

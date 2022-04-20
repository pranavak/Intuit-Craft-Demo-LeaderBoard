package com.intuitcraft.leaderboard.services;

import java.util.List;

import com.intuitcraft.leaderboard.entity.playerScore;
import com.intuitcraft.leaderboard.exceptions.CacheInitializationException;
import com.intuitcraft.leaderboard.exceptions.LeaderboardNotInitializedException;
import com.intuitcraft.leaderboard.exceptions.LeaderboardUpdateFailureException;

public interface leaderBoardService {
	public void createBoard(int topN) throws CacheInitializationException, LeaderboardNotInitializedException;
	public List<playerScore> getTopNPlayers() throws LeaderboardNotInitializedException;
	public void publish(playerScore newScore) throws LeaderboardUpdateFailureException;
}

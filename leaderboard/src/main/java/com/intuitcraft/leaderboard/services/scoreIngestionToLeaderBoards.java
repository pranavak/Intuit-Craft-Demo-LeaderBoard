package com.intuitcraft.leaderboard.services;

import com.intuitcraft.leaderboard.entity.playerScore;
import com.intuitcraft.leaderboard.exceptions.LeaderboardUpdateFailureException;

public interface scoreIngestionToLeaderBoards {
	public void registerLeaderBoard(leaderBoardService leaderBoard);
	public void publishToLeaderBoards(playerScore newScore) throws LeaderboardUpdateFailureException;
}

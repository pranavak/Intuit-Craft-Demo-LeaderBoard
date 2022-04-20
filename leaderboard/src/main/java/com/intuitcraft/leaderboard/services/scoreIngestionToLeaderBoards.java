package com.intuitcraft.leaderboard.services;

import com.intuitcraft.leaderboard.entity.playerScore;

public interface scoreIngestionToLeaderBoards {
	public void registerLeaderBoard(leaderBoardService leaderBoard);
	public void publishToLeaderBoards(playerScore newScore);
}

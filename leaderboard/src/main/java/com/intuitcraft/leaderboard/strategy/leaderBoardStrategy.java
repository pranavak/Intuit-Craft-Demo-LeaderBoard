package com.intuitcraft.leaderboard.strategy;

import java.util.Comparator;

import com.intuitcraft.leaderboard.entity.playerScore;

public interface leaderBoardStrategy extends Comparator<playerScore> {
	public int compareTo(playerScore score1, playerScore score2);
}

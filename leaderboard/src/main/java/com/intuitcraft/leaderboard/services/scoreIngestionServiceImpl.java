package com.intuitcraft.leaderboard.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuitcraft.leaderboard.entity.playerScore;
import com.intuitcraft.leaderboard.exceptions.LeaderboardUpdateFailureException;
import com.intuitcraft.leaderboard.repository.playerScoreRepository;

@Service
public class scoreIngestionServiceImpl implements scoreIngestionToLeaderBoards, scoreIngestionToStorage, scoreIngestionService {
	
	List<leaderBoardService> leaderBoards = new ArrayList<leaderBoardService>();
	
	@Autowired
	playerScoreRepository scoreRepository;

	public void publishToStore(playerScore newScore) {
		Optional<playerScore> scoreAlreadyPresent = scoreRepository.findById(newScore.getPlayerId());
		if (scoreAlreadyPresent.isPresent() && scoreAlreadyPresent.get().getScore() >= newScore.getScore()) {
			return;
		}
		scoreRepository.save(newScore);
	}

	public void registerLeaderBoard(leaderBoardService leaderBoard) {
		leaderBoards.add(leaderBoard);
	}

	public void publishToLeaderBoards(playerScore newScore) throws LeaderboardUpdateFailureException {
		for (leaderBoardService leaderBoard : leaderBoards) {
			leaderBoard.publish(newScore);
		}
	}

	@Transactional
	public void publish(playerScore newScore) throws LeaderboardUpdateFailureException {
		publishToLeaderBoards(newScore);
		publishToStore(newScore);
	}

}

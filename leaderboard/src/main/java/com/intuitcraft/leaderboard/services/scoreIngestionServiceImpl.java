package com.intuitcraft.leaderboard.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuitcraft.leaderboard.entity.playerScore;
import com.intuitcraft.leaderboard.repository.playerScoreRepository;

@Service
public class scoreIngestionServiceImpl implements scoreIngestionToLeaderBoards, scoreIngestionToStorage, scoreIngestionService {
	
	List<leaderBoardService> leaderBoards = new ArrayList<leaderBoardService>();
	
	@Autowired
	playerScoreRepository scoreRepository;

	public void publishToStore(playerScore newScore) {
		scoreRepository.save(newScore);
	}

	public void registerLeaderBoard(leaderBoardService leaderBoard) {
		leaderBoards.add(leaderBoard);
	}

	public void publishToLeaderBoards(playerScore newScore) {
		for (leaderBoardService leaderBoard : leaderBoards) {
			leaderBoard.publish(newScore);
		}
	}

	public void publish(playerScore newScore) {
		publishToLeaderBoards(newScore);
		publishToStore(newScore);
	}

}

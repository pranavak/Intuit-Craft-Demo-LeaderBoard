package com.intuitcraft.leaderboard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuitcraft.leaderboard.entity.playerScore;
import com.intuitcraft.leaderboard.repository.playerScoreRepository;

@Service
public class leaderBoardServiceImpl implements leaderBoardService {
	
	@Autowired
	cacheService<playerScore> cache;
	
	@Autowired
	playerScoreRepository scoreRepository;
	
	@Autowired
	scoreIngestionToLeaderBoards scoreIngestor;
	
	public void createBoard(int topN) {
		List<playerScore> allScores = scoreRepository.findAll();
		cache.initialize(topN, allScores);
		scoreIngestor.registerLeaderBoard(this);
	}

	public List<playerScore> getTopNPlayers() {
		return cache.getTopNplayers();
	}

	public void publish(playerScore newScore) {
		cache.addtoCache(newScore);
	}

}

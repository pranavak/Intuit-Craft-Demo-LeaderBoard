package com.intuitcraft.leaderboard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.intuitcraft.leaderboard.entity.playerScore;
import com.intuitcraft.leaderboard.exceptions.LeaderboardNotInitialized;
import com.intuitcraft.leaderboard.repository.playerScoreRepository;

@Service
public class leaderBoardServiceImpl implements leaderBoardService {
	
	@Autowired
	cacheService<playerScore> cache;
	
	@Autowired
	playerScoreRepository scoreRepository;
	
	@Autowired
	scoreIngestionToLeaderBoards scoreIngestor;
	
	boolean leaderBoardInitialized;
	
	public void createBoard(@Value("${leaderboard.topn.default}") int topN) {
		List<playerScore> allScores = scoreRepository.findAll();
		cache.initialize(topN, allScores);
		scoreIngestor.registerLeaderBoard(this);
		leaderBoardInitialized = true;
	}

	public List<playerScore> getTopNPlayers() throws LeaderboardNotInitialized {
		if (!leaderBoardInitialized) {
			throw new LeaderboardNotInitialized("LeaderBoard not yet initialized");
		}
		return cache.getTopNplayers();
	}

	public void publish(playerScore newScore) {
		cache.addtoCache(newScore);
	}

}

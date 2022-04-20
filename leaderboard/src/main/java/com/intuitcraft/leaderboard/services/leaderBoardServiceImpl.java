package com.intuitcraft.leaderboard.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuitcraft.leaderboard.constants.constants;
import com.intuitcraft.leaderboard.entity.playerScore;
import com.intuitcraft.leaderboard.exceptions.CacheInitializationException;
import com.intuitcraft.leaderboard.exceptions.CacheUpdateFailureException;
import com.intuitcraft.leaderboard.exceptions.LeaderboardNotInitializedException;
import com.intuitcraft.leaderboard.exceptions.LeaderboardUpdateFailureException;
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
	
	@PostConstruct
	public void createBoard() throws LeaderboardNotInitializedException {
		initializeBoard(constants.DEFAULT_LEADERBOARD_SIZE);
	}
	
	private void initializeBoard(int topN) throws LeaderboardNotInitializedException {
		try {
			List<playerScore> allScores = scoreRepository.findAll();
			cache.initialize(topN, allScores);
			scoreIngestor.registerLeaderBoard(this);
			leaderBoardInitialized = true;
		} catch (CacheInitializationException e) {
			throw new LeaderboardNotInitializedException(e.getMessage());
		}
		
	}
	
	public void createBoard(int topN) throws LeaderboardNotInitializedException {
		initializeBoard(topN);
	}

	public List<playerScore> getTopNPlayers() throws LeaderboardNotInitializedException {
		if (!leaderBoardInitialized) {
			throw new LeaderboardNotInitializedException("LeaderBoard not yet initialized");
		}
		return cache.getTopNplayers();
	}

	public void publish(playerScore newScore) throws LeaderboardUpdateFailureException {
		try {
			cache.addtoCache(newScore);
		} catch (CacheUpdateFailureException e) {
			// TODO Auto-generated catch block
			throw new LeaderboardUpdateFailureException(e.getMessage());
		}
	}

}

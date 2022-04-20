package com.intuitcraft.leaderboard.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.intuitcraft.leaderboard.entity.playerScore;
import com.intuitcraft.leaderboard.exceptions.CacheInitializationException;
import com.intuitcraft.leaderboard.exceptions.CacheUpdateFailureException;

@Service
public class cacheServiceImpl implements cacheService<playerScore> {

	int topN;
	PriorityQueue<playerScore> minHeap;
	Map<String, playerScore> playerToScore;
	
	Logger logger = LoggerFactory.getLogger(cacheServiceImpl.class);
	
	public void initialize(int topN, List<playerScore> dataSet) throws CacheInitializationException {
		this.topN = topN;
		try {
			minHeap = new PriorityQueue<playerScore>();
			playerToScore = new HashMap<String, playerScore>();
			for (playerScore score : dataSet) {
				if (minHeap.size() < topN) {
					minHeap.add(score);
					playerToScore.put(score.getPlayerId(), score);
				} else {
					if (score.getScore() > minHeap.peek().getScore()) {
						playerScore removedScore = minHeap.poll();
						minHeap.add(score);
						playerToScore.remove(removedScore.getPlayerId());
						playerToScore.put(score.getPlayerId(), score);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Failed to initialize cache - " + e.getMessage());
			throw new CacheInitializationException("Failed to initialize cache");
		}
	}

	public void addtoCache(playerScore score) throws CacheUpdateFailureException {
		try {
			if (playerToScore.containsKey(score.getPlayerId())) {
				playerScore scoreToBeUpdated = playerToScore.get(score.getPlayerId());
				
				if (scoreToBeUpdated.getScore() < score.getScore()) {
					logger.debug("Updating " + scoreToBeUpdated.getPlayerId() + " to " + score.getScore());
					minHeap.remove(scoreToBeUpdated);
					playerToScore.put(score.getPlayerId(), score);
					minHeap.add(score);
				}
				return;
			}
			if (minHeap.size() < topN) {
				minHeap.add(score);
				playerToScore.put(score.getPlayerId(), score);
			} else {
				if (score.getScore() > minHeap.peek().getScore()) {
					playerScore removedScore = minHeap.poll();
					minHeap.add(score);
					playerToScore.remove(removedScore.getPlayerId());
					playerToScore.put(score.getPlayerId(), score);
				}
			}
		} catch (Exception e) {
			logger.error("Failed to update cache - " + e.getMessage());
			throw new CacheUpdateFailureException(e.getMessage());
		}
		
	}

	public List<playerScore> getTopNplayers() {
		List<playerScore> res = new ArrayList<playerScore>(minHeap);
		Collections.sort(res, Collections.reverseOrder());
		return res;
	}

}

package com.intuitcraft.leaderboard.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.stereotype.Service;

import com.intuitcraft.leaderboard.entity.playerScore;

@Service
public class cacheServiceImpl implements cacheService<playerScore> {

	int topN;
	PriorityQueue<playerScore> minHeap;
	Map<String, playerScore> playerToScore;
	
	public void initialize(int topN, List<playerScore> dataSet) {
		this.topN = topN;
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
		
	}

	public void addtoCache(playerScore score) {
		if (playerToScore.containsKey(score.getPlayerId())) {
			playerScore scoreToBeUpdated = playerToScore.get(score.getPlayerId());
			minHeap.remove(scoreToBeUpdated);
			playerToScore.put(score.getPlayerId(), score);
			minHeap.add(score);
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
	}

	public List<playerScore> getTopNplayers() {
		List<playerScore> res = new ArrayList<playerScore>(minHeap);
		Collections.sort(res, Collections.reverseOrder());
		return res;
	}

}

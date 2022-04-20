package com.intuitcraft.leaderboard.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.intuitcraft.leaderboard.entity.playerScore;
import com.intuitcraft.leaderboard.services.scoreIngestionService;

@RestController
public class gameController {

	@Autowired
	scoreIngestionService scoreIngestor;
	
	@PostMapping("/updateScore")
	public void updateScore(@RequestBody playerScore newScore) {
		try {
			scoreIngestor.publish(newScore);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
}

package com.intuitcraft.leaderboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.intuitcraft.leaderboard.entity.inputForLeaderBoard;
import com.intuitcraft.leaderboard.entity.playerScore;
import com.intuitcraft.leaderboard.exceptions.LeaderboardNotInitializedException;
import com.intuitcraft.leaderboard.services.leaderBoardService;

@RestController
public class leaderBoardController {
	
	@Autowired
	leaderBoardService leaderBoard;

	@GetMapping("/getTopScorers")
	public List<playerScore> getTopScorers() {
		try {
			return leaderBoard.getTopNPlayers();
		} catch (LeaderboardNotInitializedException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Please register/create LeaderBoard first");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	@PostMapping("/createBoard")
	public void createBoard(@RequestBody inputForLeaderBoard in) {
		try {
			leaderBoard.createBoard(in.getLeaderBoardSize());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
}

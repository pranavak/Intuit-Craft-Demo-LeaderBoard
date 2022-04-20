package com.intuitcraft.leaderboard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PLAYERSCORE")
public class playerScore implements Comparable<playerScore> {
	@Id
	@Column(name="player_id")
	String playerId;
	Long score;
	
	public playerScore() {
		
	}
	public playerScore(String playerId, long score) {
		this.playerId = playerId;
		this.score = score;
	}
	
	public long getScore() {
		return this.score;
	}
	
	public String getPlayerId() {
		return playerId;
	}
	
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public int compareTo(playerScore p) {
		return Long.compare(this.score, p.getScore());
	}
	
	@Override
	public String toString() {
		return "{" + playerId + " " + score + "}";
	}
	
	@Override
	public boolean equals(Object o) {
		return this.playerId.equals(((playerScore)o).getPlayerId())
				&& this.score == ((playerScore)o).getScore();
	}
}

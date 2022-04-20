package com.intuitcraft.leaderboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intuitcraft.leaderboard.entity.playerScore;

@Repository
public interface playerScoreRepository extends JpaRepository<playerScore, Long>{

}

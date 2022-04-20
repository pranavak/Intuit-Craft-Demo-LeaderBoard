package com.intuitcraft.leaderboard.services;

import java.util.List;

import com.intuitcraft.leaderboard.exceptions.CacheInitializationException;
import com.intuitcraft.leaderboard.exceptions.CacheUpdateFailureException;


public interface cacheService<T> {
	public void initialize(int topN, List<T> data) throws CacheInitializationException;
	public void addtoCache(T data) throws CacheUpdateFailureException;
	public List<T> getTopNplayers();
}

package com.intuitcraft.leaderboard.services;

import java.util.List;


public interface cacheService<T> {
	public void initialize(int topN, List<T> data);
	public void addtoCache(T data);
	public List<T> getTopNplayers();
}

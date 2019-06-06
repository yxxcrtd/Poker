package cn.com.galaxymaster.service;

import cn.com.galaxymaster.domain.Game;

/**
 * Game Service Interface
 */
public interface GameService extends BaseService<Game, Integer> {
	public abstract boolean updateStatus(String[] ids, String status);
}

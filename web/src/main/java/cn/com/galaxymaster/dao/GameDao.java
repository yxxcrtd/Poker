package cn.com.galaxymaster.dao;

import cn.com.galaxymaster.domain.Game;

/**
 * Game DAO
 */
public interface GameDao extends BaseDao<Game, Integer> {
	/* 批量更新状态(显示，隐藏) */
	public int updateStatus(String[] ids, int status);
}

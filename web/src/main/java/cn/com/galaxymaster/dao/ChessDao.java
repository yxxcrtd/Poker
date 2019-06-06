package cn.com.galaxymaster.dao;

import cn.com.galaxymaster.domain.Chess;

/**
 * Chess DAO
 */
public interface ChessDao extends BaseDao<Chess, Integer> {
	public void updateStatus(String[] ids, int status);
}

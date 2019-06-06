package cn.com.galaxymaster.service;

import cn.com.galaxymaster.domain.Chess;

/**
 * Chess Service Interface
 */
public interface ChessService extends BaseService<Chess, Integer> {
	public abstract void updateStatus(String[] ids, String status);

	void increase(int id);

}

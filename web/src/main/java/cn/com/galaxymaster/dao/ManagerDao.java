package cn.com.galaxymaster.dao;

import cn.com.galaxymaster.domain.Manager;

/**
 * Manager DAO
 */
public interface ManagerDao extends BaseDao<Manager, Integer> {
	
	void updateStatus(String obj, boolean bol, int id);
	
	void reset(int id);

}

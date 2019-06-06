package cn.com.galaxymaster.dao;

import cn.com.galaxymaster.domain.Ad;

/**
 * Ad DAO
 */
public interface AdDao extends BaseDao<Ad, Integer> {
	
	void updateStatus(String[] ids, int status);

}

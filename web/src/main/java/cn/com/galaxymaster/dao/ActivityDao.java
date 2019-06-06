package cn.com.galaxymaster.dao;

import cn.com.galaxymaster.domain.Activity;

/**
 * Activity DAO
 */
public interface ActivityDao extends BaseDao<Activity, Integer> {
	/* 批量更新状态(停止) */
	public int updateStatus(String[] ids, int status);
}

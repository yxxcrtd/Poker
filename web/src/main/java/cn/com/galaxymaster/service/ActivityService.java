package cn.com.galaxymaster.service;

import cn.com.galaxymaster.domain.Activity;

/**
 * Activity Service Interface
 */
public interface ActivityService extends BaseService<Activity, Integer> {
	public abstract boolean updateStatus(String[] ids, String status);
	void increase(int id);

}

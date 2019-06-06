package cn.com.galaxymaster.service;

import cn.com.galaxymaster.domain.Tutorial;

/**
 * Tutorial Service Interface
 */
public interface TutorialService extends BaseService<Tutorial, Integer> {
	public abstract boolean updateStatus(String[] ids, String status);

	public int findCount(String where);
	void increase(int id);

}

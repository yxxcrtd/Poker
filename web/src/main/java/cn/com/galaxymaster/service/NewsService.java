package cn.com.galaxymaster.service;

import cn.com.galaxymaster.domain.News;

/**
 * News Service Interface
 */
public interface NewsService extends BaseService<News, Integer> {
	
	boolean updateStatus(String[] ids, String status);

	int findCount(String where);
	
	void increase(int id);

}

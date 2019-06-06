package cn.com.galaxymaster.service;

import cn.com.galaxymaster.domain.Video;

/**
 * Video Service Interface
 */
public interface VideoService extends BaseService<Video, Integer> {
	
	boolean updateStatus(String[] ids, String status);
	
	void increase(int id);

}

package cn.com.galaxymaster.dao;

import cn.com.galaxymaster.domain.Video;

/**
 * Video DAO
 */
public interface VideoDao extends BaseDao<Video, Integer> {
	
	int updateStatus(String[] ids, int status);

}

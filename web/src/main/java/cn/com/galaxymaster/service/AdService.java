package cn.com.galaxymaster.service;

import cn.com.galaxymaster.domain.Ad;

/**
 * Ad Service Interface
 */
public interface AdService extends BaseService<Ad, Integer> {
	
	void updateStatus(String[] ids, int status);

}

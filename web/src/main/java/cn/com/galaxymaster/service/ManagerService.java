package cn.com.galaxymaster.service;

import cn.com.galaxymaster.domain.Manager;

/**
 * Manager Service Interface
 */
public interface ManagerService extends BaseService<Manager, Integer> {

	String getManagerByName(String name);

	boolean deleteById(int id);

	String updateStatus(String obj, int id);
	
	boolean reset(int id);

}

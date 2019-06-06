package cn.com.galaxymaster.service;

import javax.servlet.http.HttpServletRequest;

import cn.com.galaxymaster.domain.Log;

/**
 * Log Service Interface
 */
public interface LogService extends BaseService<Log, Integer> {
	void saveLog(HttpServletRequest request, String obj, String title);

}

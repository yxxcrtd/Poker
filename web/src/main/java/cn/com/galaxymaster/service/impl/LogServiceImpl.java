package cn.com.galaxymaster.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import cn.com.galaxymaster.dao.LogDao;
import cn.com.galaxymaster.domain.Log;
import cn.com.galaxymaster.domain.Manager;
import cn.com.galaxymaster.service.LogService;
import cn.com.galaxymaster.util.Constants;

/**
 * Log Service Implementation
 */
@Service
public class LogServiceImpl extends BaseServiceImpl<Log, Integer> implements LogService {

	@Override
	public void saveLog(HttpServletRequest request, String obj, String title) {
		Log log = new Log();
		Manager manager = (Manager) request.getSession().getAttribute(Constants.SESSION_USER_KEY);
		if (title.length() >= 32) {
			title = title.substring(0, 28)+"...";
		}
		log.setLogTitle(title);
		log.setLogObj(obj);
		log.setLogCreateTime(new Date());
		log.setLogUser(manager.getManagerName());
		log.setLogIP(request.getRemoteAddr());
		((LogDao) baseDao).save(log);
	}
}

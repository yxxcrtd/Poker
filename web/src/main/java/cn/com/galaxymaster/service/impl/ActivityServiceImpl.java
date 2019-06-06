package cn.com.galaxymaster.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.galaxymaster.dao.ActivityDao;
import cn.com.galaxymaster.domain.Activity;
import cn.com.galaxymaster.service.ActivityService;

/**
 * Activity Service Implementation
 */
@Service
public class ActivityServiceImpl extends BaseServiceImpl<Activity, Integer> implements ActivityService {
	@Autowired
	protected ActivityDao activityDao;

	@Override
	public boolean updateStatus(String[] ids, String status) {
		activityDao.updateStatus(ids, Integer.parseInt(status));
		return false;
	}

	@Override
	public void increase(int id) {
		Activity activity = activityDao.findById(id);
		if (null != activity) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			activity.setActivityHit(activity.getActivityHit() + 1);
			try {
				activity.setActivityCreateTime(sdf.parse(sdf.format(activity.getActivityCreateTime())));
				activity.setActivityBeginDate(sdf.parse(sdf.format(activity.getActivityBeginDate())));
				activity.setActivityEndDate(sdf.parse(sdf.format(activity.getActivityEndDate())));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			activityDao.update(activity);
		}
	}
}

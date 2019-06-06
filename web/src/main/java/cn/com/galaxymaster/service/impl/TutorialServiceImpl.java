package cn.com.galaxymaster.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

import cn.com.galaxymaster.dao.TutorialDao;
import cn.com.galaxymaster.domain.Tutorial;
import cn.com.galaxymaster.service.TutorialService;

/**
 * Tutorial Service Implementation
 */
@Service
public class TutorialServiceImpl extends BaseServiceImpl<Tutorial, Integer> implements TutorialService {

	@Override
	public boolean updateStatus(String[] ids, String status) {
		((TutorialDao) baseDao).updateStatus(ids, Integer.parseInt(status));
		return false;
	}

	@Override
	public int findCount(String where) {
		return ((TutorialDao) baseDao).findCount(where);
	}
	
	@Override
	public void increase(int id) {
		Tutorial tutorial = ((TutorialDao) baseDao).findById(id);
		if (null != tutorial) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = sdf.format(tutorial.getTutorialCreateTime());
			tutorial.setTutorialHit(tutorial.getTutorialHit() + 1);
			try {
				tutorial.setTutorialCreateTime(sdf.parse(dateString));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			((TutorialDao) baseDao).update(tutorial);
		}
	}
}

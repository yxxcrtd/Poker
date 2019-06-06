package cn.com.galaxymaster.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

import cn.com.galaxymaster.dao.VideoDao;
import cn.com.galaxymaster.domain.Video;
import cn.com.galaxymaster.service.VideoService;

/**
 * Video Service Implementation
 */
@Service
public class VideoServiceImpl extends BaseServiceImpl<Video, Integer> implements VideoService {

	@Override
	public boolean updateStatus(String[] ids, String status) {
		((VideoDao) baseDao).updateStatus(ids, Integer.parseInt(status));
		return false;
	}

	@Override
	public void increase(int id) {
		Video video = ((VideoDao) baseDao).findById(id);
		if (null != video) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = sdf.format(video.getVideoCreateTime());
			video.setVideoHit(video.getVideoHit() + 1);
			try {
				video.setVideoCreateTime(sdf.parse(dateString));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			((VideoDao) baseDao).update(video);
		}
	}

}

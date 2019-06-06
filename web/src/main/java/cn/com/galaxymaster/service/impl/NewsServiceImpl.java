package cn.com.galaxymaster.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

import cn.com.galaxymaster.dao.NewsDao;
import cn.com.galaxymaster.domain.News;
import cn.com.galaxymaster.service.NewsService;

/**
 * News Service Implementation
 */
@Service
public class NewsServiceImpl extends BaseServiceImpl<News, Integer> implements NewsService {

	@Override
	public boolean updateStatus(String[] ids, String status) {
		((NewsDao) baseDao).updateStatus(ids, Integer.parseInt(status));
		return false;
	}

	@Override
	public int findCount(String where) {
		return ((NewsDao) baseDao).findCount(where);
	}

	@Override
	public void increase(int id) {
		News news = ((NewsDao) baseDao).findById(id);
		if (null != news) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = sdf.format(news.getNewsCreateTime());
			news.setNewsHit(news.getNewsHit() + 1);
			try {
				news.setNewsCreateTime(sdf.parse(dateString));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			((NewsDao) baseDao).update(news);
		}
	}
	
}

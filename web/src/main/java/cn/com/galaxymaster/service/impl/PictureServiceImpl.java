package cn.com.galaxymaster.service.impl;

import org.springframework.stereotype.Service;

import cn.com.galaxymaster.dao.PictureDao;
import cn.com.galaxymaster.domain.Picture;
import cn.com.galaxymaster.service.PictureService;

/**
 * Picture Service Implementation
 */
@Service
public class PictureServiceImpl extends BaseServiceImpl<Picture, Integer> implements PictureService {

	@Override
	public void updateStatus(String[] ids, String status) {
		((PictureDao) baseDao).updateStatus(ids, Integer.parseInt(status));
	}

}

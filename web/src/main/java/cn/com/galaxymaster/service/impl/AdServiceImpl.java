package cn.com.galaxymaster.service.impl;

import org.springframework.stereotype.Service;

import cn.com.galaxymaster.dao.AdDao;
import cn.com.galaxymaster.domain.Ad;
import cn.com.galaxymaster.service.AdService;

/**
 * Ad Service Implementation
 */
@Service
public class AdServiceImpl extends BaseServiceImpl<Ad, Integer> implements AdService {

	@Override
	public void updateStatus(String[] ids, int status) {
		((AdDao) baseDao).updateStatus(ids, status);
	}

}

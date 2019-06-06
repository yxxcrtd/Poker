package cn.com.galaxymaster.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.galaxymaster.dao.GameDao;
import cn.com.galaxymaster.domain.Game;
import cn.com.galaxymaster.service.GameService;

/**
 * Game Service Implementation
 */
@Service
public class GameServiceImpl extends BaseServiceImpl<Game, Integer> implements GameService {
	@Autowired
	protected GameDao gameDao;

	@Override
	public boolean updateStatus(String[] ids, String status) {
		gameDao.updateStatus(ids, Integer.parseInt(status));
		return false;
	}
}

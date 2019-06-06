package cn.com.galaxymaster.service.impl;

import org.springframework.stereotype.Service;

import cn.com.galaxymaster.dao.ManagerDao;
import cn.com.galaxymaster.domain.Manager;
import cn.com.galaxymaster.service.ManagerService;

/**
 * Manager Service Implementation
 */
@Service
public class ManagerServiceImpl extends BaseServiceImpl<Manager, Integer>implements ManagerService {

	@Override
	public String getManagerByName(String name) {
		String returnString = "error";
		Manager manager = ((ManagerDao) baseDao).findByName(name);
		if (null != manager) {
			if (1 == manager.getManagerRole()) {
				returnString = "admin";
			}
		} else {
			returnString = "success";
		}
		return returnString;
	}

	@Override
	public boolean deleteById(int id) {
		boolean returnBoolean = false;
		Manager manager = ((ManagerDao) baseDao).findById(id);
		if (null != manager) {
			if (0 == manager.getManagerRole()) {
				((ManagerDao) baseDao).delete(manager);
				returnBoolean = true;
			}
		}
		return returnBoolean;
	}

	@Override
	public boolean reset(int id) {
		boolean returnBoolean = false;
		Manager manager = ((ManagerDao) baseDao).findById(id);
		if (null != manager) {
			((ManagerDao) baseDao).reset(id);
			returnBoolean = true;
		}
		return returnBoolean;
	}

	@Override
	public String updateStatus(String obj, int id) {
		Manager manager = ((ManagerDao) baseDao).findById(id);
		boolean b = getManagerBoolean(manager, obj);
		if (null != manager) {
			((ManagerDao) baseDao).updateStatus(obj, b, id);
		}
		return b ? "true" : "false";
	}
	
	private boolean getManagerBoolean(Manager manager, String obj) {
		boolean b = false;
		switch (obj) {
		case "ManagerNews":
			b = manager.isManagerNews() ? false : true;
			break;
		case "ManagerVideo":
			b = manager.isManagerVideo() ? false : true;
			break;
		case "ManagerAd":
			b = manager.isManagerAd() ? false : true;
			break;
		case "ManagerGame":
			b = manager.isManagerGame() ? false : true;
			break;
		case "ManagerActive":
			b = manager.isManagerActive() ? false : true;
			break;
		case "ManagerMatch":
			b = manager.isManagerMatch() ? false : true;
			break;
		case "ManagerPoker":
			b = manager.isManagerPoker() ? false : true;
			break;
		case "ManagerWebsite":
			b = manager.isManagerWebsite() ? false : true;
			break;
		default:
			break;
		}
		return b;
	}

}

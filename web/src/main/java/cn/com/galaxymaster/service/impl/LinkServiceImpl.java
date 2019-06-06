package cn.com.galaxymaster.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.galaxymaster.dao.LinkDao;
import cn.com.galaxymaster.domain.Link;
import cn.com.galaxymaster.service.LinkService;

/**
 * Link Service Implementation
 */
@Service
public class LinkServiceImpl extends BaseServiceImpl<Link, Integer> implements LinkService {
	@Autowired
	protected LinkDao linkDao;

	@Override
	public boolean updateStatus(String[] ids, String status) {
		linkDao.updateStatus(ids, Integer.parseInt(status));
		return false;
	}

	@Override
	public int findCount(String where) {
		return linkDao.findCount(where);
	}

	@Override
	public String generateWhere(String name, int status) {
		StringBuffer where = new StringBuffer();
		if (status != 2 && !StringUtils.isEmpty(name)) {
			where.append("\"LinkStatus\"=").append(status).append("AND \"LinkName\" LIKE '%").append(name.trim()).append("%' ");
		} else if (!StringUtils.isEmpty(name)) {
			where.append("\"LinkName\" LIKE '%").append(name.trim()).append("%' ");
		} else if (status != 2) {
			where.append("\"LinkStatus\"=").append(status);
		} else {
			return null;
		}
		return where.toString();
	}

}

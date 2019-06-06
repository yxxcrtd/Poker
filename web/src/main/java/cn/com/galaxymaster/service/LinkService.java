package cn.com.galaxymaster.service;

import cn.com.galaxymaster.domain.Link;

/**
 * Link Service Interface
 */
public interface LinkService extends BaseService<Link, Integer> {
	public abstract boolean updateStatus(String[] ids, String status);

	public int findCount(String where);

	public String generateWhere(String name, int status);
}

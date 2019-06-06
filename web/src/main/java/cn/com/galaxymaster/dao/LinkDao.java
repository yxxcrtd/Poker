package cn.com.galaxymaster.dao;

import cn.com.galaxymaster.domain.Link;

/**
 * Link DAO
 */
public interface LinkDao extends BaseDao<Link, Integer> {
	/* 批量更新状态(友情链接显示，隐藏) */
	public int updateStatus(String[] ids, int status);

	/* 条件查询 */
	public int findCount(String where);
}

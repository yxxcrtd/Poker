package cn.com.galaxymaster.dao;

import cn.com.galaxymaster.domain.Picture;

/**
 * Picture DAO
 */
public interface PictureDao extends BaseDao<Picture, Integer> {
	/* 批量更新状态(友情链接显示，隐藏) */
	public int updateStatus(String[] ids, int status);

}

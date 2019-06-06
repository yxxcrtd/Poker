package cn.com.galaxymaster.service;

import cn.com.galaxymaster.domain.Picture;

/**
 * User Service Interface
 */
public interface PictureService extends BaseService<Picture, Integer> {
	public abstract void updateStatus(String[] ids, String status);

}

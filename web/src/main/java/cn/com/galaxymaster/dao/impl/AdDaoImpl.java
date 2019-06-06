package cn.com.galaxymaster.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.galaxymaster.dao.AdDao;
import cn.com.galaxymaster.domain.Ad;

/**
 * Ad Dao Implementation
 */
@Repository
public class AdDaoImpl extends BaseDaoImpl<Ad, Integer, String, String> implements AdDao {

	@Override
	public void updateStatus(String[] ids, int status) {
		for (String id : ids) {
			String sql = "UPDATE \"T_Ad\" SET \"AdVisible\" = ? WHERE \"AdId\" = ?";
			LOGGER.info(sql + " : " + status + ", " + id);
			jdbcTemplate.update(sql, new Object[] { status, Integer.valueOf(id) });
		}
	}

}

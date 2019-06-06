package cn.com.galaxymaster.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.galaxymaster.dao.ManagerDao;
import cn.com.galaxymaster.domain.Manager;
import cn.com.galaxymaster.util.MD5;

/**
 * Manager Dao Implementation
 */
@Repository
public class ManagerDaoImpl extends BaseDaoImpl<Manager, Integer, String, String>implements ManagerDao {

	@Override
	public void updateStatus(String obj, boolean bol, int id) {
		String sql = "UPDATE \"T_Manager\" SET \"" + obj + "\" = ? WHERE \"ManagerId\" = ?";
		LOGGER.info(sql + " : " + obj + ", " + bol + ", " + id);
		jdbcTemplate.update(sql, new Object[] { bol, id });
	}

	@Override
	public void reset(int id) {
		String sql = "UPDATE \"T_Manager\" SET \"ManagerPassword\" = '" + MD5.toMD5("123456") + "' WHERE \"ManagerId\" = ?";
		LOGGER.info(sql + " : " + id);
		jdbcTemplate.update(sql, new Object[] { id });
	}

}

package cn.com.galaxymaster.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.galaxymaster.dao.ActivityDao;
import cn.com.galaxymaster.domain.Activity;

/**
 * Activity Dao Implementation
 */
@Repository
public class ActivityDaoImpl extends BaseDaoImpl<Activity, Integer, String, String> implements ActivityDao {
	@Override
	public int updateStatus(String[] ids, int status) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE \"T_Activity\" SET \"ActivityStatus\" = ? WHERE \"ActivityId\" ");
		if (ids.length == 1) {
			sql.append(" = ? ");
		} else {
			sql.append(" in (");
			for (int i = 0; i < ids.length; i++) {
				sql.append("?,");
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(")");
		}
		LOGGER.info(sql.toString());
		Object[] args = new Object[ids.length + 1];
		int[] argTypes = new int[args.length];
		args[0] = status;
		argTypes[0] = 5;
		for (int i = 0; i < ids.length; i++) {
			args[i + 1] = Integer.parseInt(ids[i]);
			argTypes[i + 1] = 5;
		}
		return this.jdbcTemplate.update(sql.toString(), args);
	}
}

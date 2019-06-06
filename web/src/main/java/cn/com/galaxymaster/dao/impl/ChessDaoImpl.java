package cn.com.galaxymaster.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.galaxymaster.dao.ChessDao;
import cn.com.galaxymaster.domain.Chess;

/**
 * Chess Dao Implementation
 */
@Repository
public class ChessDaoImpl extends BaseDaoImpl<Chess, Integer, String, String> implements ChessDao {

	@Override
	public void updateStatus(String[] ids, int status) {
		for (String id : ids) {
			String sql = "UPDATE \"T_Chess\" SET \"ChessStatus\" = ? WHERE \"ChessId\" = ?";
			LOGGER.info(sql + " : " + status + ", " + id);
			jdbcTemplate.update(sql, new Object[] { status, Integer.valueOf(id) });
		}
	}
}

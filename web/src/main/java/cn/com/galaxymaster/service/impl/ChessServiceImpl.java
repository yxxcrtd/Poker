package cn.com.galaxymaster.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

import cn.com.galaxymaster.dao.ChessDao;
import cn.com.galaxymaster.domain.Chess;
import cn.com.galaxymaster.service.ChessService;

/**
 * Chess Service Implementation
 */
@Service
public class ChessServiceImpl extends BaseServiceImpl<Chess, Integer> implements ChessService {

	@Override
	public void updateStatus(String[] ids, String status) {
		((ChessDao) baseDao).updateStatus(ids, Integer.parseInt(status));
	}

	@Override
	public void increase(int id) {
		Chess chess = ((ChessDao) baseDao).findById(id);
		if (null != chess) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = sdf.format(chess.getChessCreateTime());
			chess.setChessHit(chess.getChessHit() + 1);
			try {
				chess.setChessCreateTime(sdf.parse(dateString));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			((ChessDao) baseDao).update(chess);
		}
	}
}

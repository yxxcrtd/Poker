package cn.com.galaxymaster.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.galaxymaster.dao.MatchDao;
import cn.com.galaxymaster.domain.Match;

/**
 * Match Dao Implementation
 */
@Repository
public class MatchDaoImpl extends BaseDaoImpl<Match, Integer, String, String> implements MatchDao {

}

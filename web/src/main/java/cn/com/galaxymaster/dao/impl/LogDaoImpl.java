package cn.com.galaxymaster.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.galaxymaster.dao.LogDao;
import cn.com.galaxymaster.domain.Log;

/**
 * Log Dao Implementation
 */
@Repository
public class LogDaoImpl extends BaseDaoImpl<Log, Integer, String, String> implements LogDao {

}

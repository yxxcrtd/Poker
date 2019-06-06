package cn.com.galaxymaster.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.galaxymaster.dao.UserDao;
import cn.com.galaxymaster.domain.User;

/**
 * User Dao Implementation
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Integer, String, String> implements UserDao {

}

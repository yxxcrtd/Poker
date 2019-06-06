package cn.com.galaxymaster.service.impl;

import cn.com.galaxymaster.dao.BaseDao;
import cn.com.galaxymaster.service.BaseService;
import cn.com.galaxymaster.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Base Service Implement
 */
public class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {

	/**
	 * Base Dao
	 */
	@Autowired
	protected BaseDao<T, PK> baseDao;

	@Override
	public T findById(PK id) {
		return (T) baseDao.findById(id);
	}

	@Override
	public T findByName(String name) {
		return (T) baseDao.findByName(name);
	}

	@Override
	public int findAllCount(T t, String whereString) {
		return baseDao.findAllCount(t, whereString);
	}

	@Override
	public List<T> findAllList(String whereString, String orderbyString) {
		return baseDao.findAllList(whereString, orderbyString);
	}

	@Override
	public List<T> findAllListById(int id) {
		return baseDao.findAllListById(id);
	}

	@Override
	public List<T> findByPager(Pager pager) {
		return baseDao.findByPager(pager);
	}

	@Override
	public List<T> findByPager(Pager pager, String whereString, String orderbyString) {
		return baseDao.findByPager(pager, whereString, orderbyString);
	}

	@Override
	public List<T> findListByLeftJoin(Pager pager, String keyword) {
		return baseDao.findListByLeftJoin(pager, keyword);
	}

	@Override
	public Long save(T t) {
		return baseDao.save(t);
	}

	@Override
	public void update(T t) {
		baseDao.update(t);
	}

	@Override
	public void delete(PK id) {
		baseDao.delete(id);
	}

	@Override
	public void delete(T t) {
		baseDao.delete(t);
	}

	@Override
	public Long lpush(String key, String value) {
		return baseDao.lpush(key, value);
	}

	@Override
	public boolean zadd(String key, long orderby, String value) {
		return baseDao.zadd(key, orderby, value);
	}

	@Override
	public List<String> getSet(String key) {
		return baseDao.getSet(key);
	}

	@Override
	public void del(String key) {
		List<String> list = new ArrayList<String>();
		list.add(key);
		del(list);
	}

	public void del(List<String> keys) {
		baseDao.del(keys);
	}

}

package cn.com.galaxymaster.service;

import cn.com.galaxymaster.util.Pager;

import java.io.Serializable;
import java.util.List;

/**
 * Generic Service
 */
public interface BaseService<T, PK extends Serializable> {

	T findById(PK id);
	
	T findByName(String name);

	int findAllCount(T t, String whereString);

	List<T> findAllList(String whereString, String orderbyString);

	List<T> findAllListById(int id);

	List<T> findByPager(Pager pager);

	List<T> findByPager(Pager pager, String whereString, String orderbyString);

	List<T> findListByLeftJoin(Pager pager, String keyword);

	Long save(T t);

	void update(T t);

	void delete(PK id);

	void delete(T t);

	Long lpush(String key, String value);

	boolean zadd(String key, long orderby, String value);

	List<String> getSet(String key);

	void del(String key);

}

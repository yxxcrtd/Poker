package cn.com.galaxymaster.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Redis DAO
 *
 * @param Key
 * @param Value
 */
public class RedisDao<K, V> {

	/**
	 * RedisTemplate
	 */
	@Autowired
	protected RedisTemplate<K, V> redisTemplate;

	/**
	 * 设置 RedisTemplate
	 */
	public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * 获取 RedisSerializer
	 */
	protected RedisSerializer<String> getRedisSerializer() {
		return redisTemplate.getStringSerializer();
	}

}

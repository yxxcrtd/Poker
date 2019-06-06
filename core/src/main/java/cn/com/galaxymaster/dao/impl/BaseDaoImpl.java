package cn.com.galaxymaster.dao.impl;

import cn.com.galaxymaster.dao.BaseDao;
import cn.com.galaxymaster.dao.RedisDao;
import cn.com.galaxymaster.util.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * BaseDAO Implementation
 * 
 * @param <K>
 * @param <V>
 */
public class BaseDaoImpl<T, PK extends Serializable, K, V> extends RedisDao<K, V> implements BaseDao<T, PK> {

	/** LOGGER */
	protected static final Logger LOGGER = LoggerFactory.getLogger(BaseDaoImpl.class);

	/** JdbcTemplate */
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	/** SQL Insert Flag */
	private static final int SQL_FLAG_INSERT = 1;

	/** SQL Update Flag */
	private static final int SQL_FLAG_UPDATE = 2;

	/** SQL Delete Flag */
	private static final int SQL_FLAG_DELETE = 3;

	/** Generic Class */
	private Class<T> clazz = null;

	/** Generic Object Name */
	private String tableName = "";

	/**
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) pt.getActualTypeArguments()[0];
		tableName = clazz.getSimpleName();
	}

	@Override
	public T findById(PK id) {
		String sql = new StringBuffer("SELECT * FROM \"T_").append(tableName).append("\"").append(" WHERE \"").append(tableName).append("Id\" = ?").toString();
		LOGGER.info(sql + " : " + id);
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(clazz);
		List<T> list = jdbcTemplate.query(sql, rowMapper, new Object[] { id });
		return (null != list && 0 < list.size()) ? list.get(0) : null;
	}

	@Override
	public T findByName(String name) {
		String sql = new StringBuffer("SELECT * FROM \"T_").append(tableName).append("\"").append(" WHERE \"").append(tableName).append("Name\" = ?").toString();
		LOGGER.info(sql + " : " + name);
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(clazz);
		List<T> list = jdbcTemplate.query(sql, rowMapper, new Object[] { name });
		return (null != list && 0 < list.size()) ? list.get(0) : null;
	}

	@Override
	public int findAllCount(T t, String whereString) {
		whereString = (!"".equals(whereString) && null != whereString && 0 < whereString.length()) ? " WHERE " + whereString : "";
		String sql = new StringBuffer("SELECT * FROM \"T_").append(tableName).append("\"").append(whereString).toString();
		LOGGER.info(sql);
		return jdbcTemplate.queryForList(sql).size();
	}

	@Override
	public List<T> findByPager(Pager pager) {
		String sql = new StringBuffer("SELECT * FROM \"T_").append(tableName).append("\" ORDER BY \"").append(tableName).append("Id\" DESC LIMIT ").append(pager.getPageSize()).append(" OFFSET ").append(pager.getOffset()).toString();
		LOGGER.info(sql + " : " + pager.getPageSize() + " , " + pager.getOffset());
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(clazz));
	}

	@Override
	public List<T> findByPager(Pager pager, String whereString, String orderbyString) {
		whereString = (!"".equals(whereString) && null != whereString && 0 < whereString.length()) ? " WHERE " + whereString : "";
		orderbyString = (!"".equals(orderbyString) && null != orderbyString && 0 < orderbyString.length()) ? " ORDER BY " + orderbyString : "";
		String sql = new StringBuffer("SELECT * FROM \"T_").append(tableName).append("\"").append(whereString).append(orderbyString).append(" LIMIT ").append(pager.getPageSize()).append(" OFFSET ").append(pager.getOffset()).toString();
		LOGGER.info(sql);
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(clazz));
	}

	@Override
	public List<T> findAllList(String whereString, String orderbyString) {
		whereString = (!"".equals(whereString) && null != whereString && 0 < whereString.length()) ? " WHERE " + whereString : "";
		orderbyString = (!"".equals(orderbyString) && null != orderbyString && 0 < orderbyString.length()) ? " ORDER BY " + orderbyString : "";
		String sql = new StringBuffer("SELECT * FROM \"T_").append(tableName).append("\"").append(whereString).append(orderbyString).toString();
		LOGGER.info(sql);
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(clazz));
	}

	@Override
	public List<T> findAllListById(int id) {
		return null;
	}

	@Override
	public Long save(T t) {
		//		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = this.makeSql(SQL_FLAG_INSERT);
		LOGGER.info(sql);
		Object[] args = this.setArgs(t, SQL_FLAG_INSERT);
		int[] argTypes = this.setArgTypes(t, SQL_FLAG_INSERT);
		return (long) jdbcTemplate.update(sql, args, argTypes);
	}

	// TODO
	//	@Override
	//	public Long saveReturnId(T t) {
	//		final String sql = new StringBuffer("insert into wx_").append(tableName).append(" (note_id, note_user_id, note_openId) values (?, ?, ?)").toString();
	//		LOGGER.info(sql);
	//
	//		KeyHolder keyHolder = new GeneratedKeyHolder();
	//		jdbcTemplate.update(new PreparedStatementCreator() {
	//			@Override
	//			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
	//				PreparedStatement ps = con.prepareStatement(sql, new String[] { "note_id", "note_user_id", "note_openId" });
	//				// PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(sql, new String[] { "note_id", "note_user_id", "note_openId" });
	//				ps.setString(1, "111");
	//				ps.setString(2, "222");
	//				ps.setString(3, "333");
	//				return ps;
	//			}
	//		}, keyHolder);
	//
	//		System.out.println("自动插入id============================" + keyHolder.getKey().intValue());
	//
	//		return (long) keyHolder.getKey().intValue();
	//	}

	@Override
	public void update(T t) {
		String sql = this.makeSql(SQL_FLAG_UPDATE);
		LOGGER.info(sql);
		Object[] args = this.setArgs(t, SQL_FLAG_UPDATE);
		int[] argTypes = this.setArgTypes(t, SQL_FLAG_UPDATE);
		jdbcTemplate.update(sql, args, argTypes);
	}

	@Override
	public void delete(PK id) {
		String sql = new StringBuffer("DELETE FROM \"T_").append(tableName).append("\" WHERE \"").append(tableName).append("Id\" = ?").toString();
		LOGGER.info(sql + " : " + id);
		jdbcTemplate.update(sql, id);
	}

	@Override
	public void delete(T t) {
		String sql = this.makeSql(SQL_FLAG_DELETE);
		LOGGER.info(sql);
		Object[] args = this.setArgs(t, SQL_FLAG_DELETE);
		int[] argTypes = this.setArgTypes(t, SQL_FLAG_DELETE);
		jdbcTemplate.update(sql, args, argTypes);
	}

	/**
	 * Make SQL
	 */
	private String makeSql(int flag) {
		StringBuffer sql = new StringBuffer();
		Field[] fields = clazz.getDeclaredFields();
		switch (flag) {
		case SQL_FLAG_INSERT:
			sql.append("INSERT INTO \"T_" + tableName + "\" (");
			for (int i = 1; null != fields && i < fields.length; i++) {
				fields[i].setAccessible(true);
				String column = fields[i].getName();
				column = column.substring(0, 1).toUpperCase() + column.substring(1);
				if (!column.equals("\"" + tableName + "Id\"")) {
					sql.append("\"").append(column).append("\"").append(",");
				}
			}
			sql = sql.deleteCharAt(sql.length() - 1);
			sql.append(") VALUES (");
			for (int i = 1; null != fields && i < fields.length; i++) {
				sql.append("?,");
			}
			sql = sql.deleteCharAt(sql.length() - 1);
			sql.append(")");
			break;
		case SQL_FLAG_UPDATE:
			sql.append("UPDATE \"T_" + tableName + "\" SET ");
			for (int i = 1; null != fields && i < fields.length; i++) {
				fields[i].setAccessible(true);
				String column = fields[i].getName();
				column = column.substring(0, 1).toUpperCase() + column.substring(1);
				if (!column.equals("\"" + tableName + "Id\"")) {
					sql.append("\"").append(column).append("\"").append(" = ?,");
				}
			}
			sql = sql.deleteCharAt(sql.length() - 1);
			sql.append(" WHERE \"").append(tableName).append("Id\" = ?");
			break;
		case SQL_FLAG_DELETE:
			sql.append("DELETE FROM \"T_").append(tableName).append("\" WHERE \"").append(tableName).append("Id\" = ?");
			break;
		default:
			break;
		}
		return sql.toString();
	}

	/**
	 * Set Object Args
	 * 
	 * @param t
	 * @param flag
	 * @return
	 */
	private Object[] setArgs(T t, int flag) {
		Field[] fields = clazz.getDeclaredFields();
		Object[] args = null;
		switch (flag) {
		case SQL_FLAG_INSERT:
			args = new Object[fields.length - 1];
			try {
				for (int i = 1; null != fields && i < fields.length; i++) {
					fields[i].setAccessible(true);
					args[i - 1] = fields[i].get(t);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case SQL_FLAG_UPDATE:
			args = new Object[fields.length];
			Object[] tempArr = new Object[fields.length];
			try {
				for (int i = 0; null != fields && i < fields.length; i++) {
					fields[i].setAccessible(true);
					tempArr[i] = fields[i].get(t);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.arraycopy(tempArr, 1, args, 0, tempArr.length - 1);
			args[args.length - 1] = tempArr[0];
			break;
		case SQL_FLAG_DELETE:
			args = new Object[1];
			try {
				fields[0].setAccessible(true);
				args[0] = fields[0].get(t);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		return args;
	}

	/**
	 * Set Object Args Array
	 * 
	 * @param t
	 * @param flag
	 * @return
	 */
	private int[] setArgTypes(T t, int flag) {
		Field[] fields = clazz.getDeclaredFields();
		int[] argTypes = null;
		switch (flag) {
		case SQL_FLAG_INSERT:
			argTypes = new int[fields.length - 1];
			try {
				for (int i = 1; null != fields && i < fields.length; i++) {
					fields[i].setAccessible(true);
					if (fields[i].get(t).getClass().getName().equals("java.lang.String")) {
						argTypes[i - 1] = Types.VARCHAR;
					} else if (fields[i].get(t).getClass().getName().equals("java.lang.Integer")) {
						argTypes[i - 1] = Types.INTEGER;
					} else if (fields[i].get(t).getClass().getName().equals("java.util.Date")) {
						argTypes[i - 1] = Types.TIMESTAMP;
					} else if (fields[i].get(t).getClass().getName().equals("java.lang.Boolean")) {
						argTypes[i - 1] = Types.BOOLEAN;
					} else if (fields[i].get(t).getClass().getName().equals("java.lang.Double")) {
						argTypes[i - 1] = Types.DOUBLE;
					} else if (fields[i].get(t).getClass().getName().equals("java.math.BigDecimal")) {
						argTypes[i - 1] = Types.DECIMAL;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case SQL_FLAG_UPDATE:
			argTypes = new int[fields.length];
			int[] tempArgTypes = new int[fields.length];
			try {
				for (int i = 0; i < tempArgTypes.length; i++) {
					fields[i].setAccessible(true);
					if (fields[i].get(t).getClass().getName().equals("java.lang.String")) {
						tempArgTypes[i] = Types.VARCHAR;
					} else if (fields[i].get(t).getClass().getName().equals("java.lang.Integer")) {
						tempArgTypes[i] = Types.INTEGER;
					} else if (fields[i].get(t).getClass().getName().equals("java.util.Date")) {
						tempArgTypes[i] = Types.TIMESTAMP;
					} else if (fields[i].get(t).getClass().getName().equals("java.lang.Boolean")) {
						tempArgTypes[i] = Types.BOOLEAN;
					} else if (fields[i].get(t).getClass().getName().equals("java.lang.Double")) {
						tempArgTypes[i] = Types.DOUBLE;
					} else if (fields[i].get(t).getClass().getName().equals("java.math.BigDecimal")) {
						tempArgTypes[i] = Types.DECIMAL;
					}
				}
				System.arraycopy(tempArgTypes, 1, argTypes, 0, tempArgTypes.length - 1);
				argTypes[argTypes.length - 1] = tempArgTypes[0];
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case SQL_FLAG_DELETE:
			argTypes = new int[1];
			try {
				fields[0].setAccessible(true);
				if (fields[0].get(t).getClass().getName().equals("java.lang.String")) {
					argTypes[0] = Types.VARCHAR;
				} else if (fields[0].get(t).getClass().getName().equals("java.lang.Integer")) {
					argTypes[0] = Types.INTEGER;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		return argTypes;
	}

	@Override
	public List<T> findListByLeftJoin(Pager pager, String keyword) {
		return null;
	}

	@Override
	public Long lpush(final String key, final String value) {
		Long result = redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] keyId = serializer.serialize(key);
				byte[] name = serializer.serialize(value);
				return connection.lPush(keyId, name);
			}
		});
		return result;
	}

	@Override
	public boolean zadd(final String key, final long i, final String value) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] keyId = serializer.serialize(key);
				byte[] name = serializer.serialize(value);
				return connection.zAdd(keyId, i, name);
			}
		});
		return result;
	}

	@Override
	public List<String> getSet(final String key) {
		final List<String> list = new ArrayList<String>();
		redisTemplate.execute(new RedisCallback<List<String>>() {
			public List<String> doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] keyId = serializer.serialize(key);
				Set<byte[]> l = connection.zRange(keyId, 0, -1);
				for (byte[] s : l) {
					list.add(new String(s));
				}
				return list;
			}
		});
		return list;
	}

	@Override
	public Long del(final List<String> keys) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				long result = 0;
				for (int i = 0; i < keys.size(); i++) {
					result = connection.del(keys.get(i).getBytes());
				}
				return result;
			}
		});
	}

}

package cn.com.galaxymaster.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.galaxymaster.dao.SeoDao;
import cn.com.galaxymaster.domain.Seo;

/**
 * Seo Dao Implementation
 */
@Repository
public class SeoDaoImpl extends BaseDaoImpl<Seo, Integer, String, String> implements SeoDao {

}

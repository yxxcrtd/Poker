package cn.com.galaxymaster.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Search Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class Search implements Serializable {
	
	/**
	 * 搜索对象
	 */
	private String obj;
	
	/** 
	 * 搜索对象Id
	 */
	private int id;

	/**
	 * 搜索标题
	 */
	private String title;

	/**
	 * 搜索摘要
	 */
	private String summary;

	/**
	 * search时间
	 */
	private String time1;
	private Date time2;

	/**
	 * search图片
	 */
	private String picture;

}

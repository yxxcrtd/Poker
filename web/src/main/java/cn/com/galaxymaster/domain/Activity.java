package cn.com.galaxymaster.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Activity Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class Activity implements Serializable {

	/**
	 * 活动ID
	 */
	private int activityId;

	/**
	 * 活动分类（默认0牌类；1棋类；2麻将；）
	 */
	private int activityCategory;

	/**
	 * 活动标题
	 */
	@Length(min = 1, max = 32, message = "{system.message.length.error}")
	private String activityTitle;

	/**
	 * 活动基本规则
	 */
	@Length(min = 1, max = 2000, message = "{system.message.length.error}")
	private String activityBasicRules;

	/**
	 * 活动时间规则
	 */
	@Length(min = 1, max = 2000, message = "{system.message.length.error}")
	private String activityTimeRules;

	/**
	 * 活动游戏规则
	 */
	@Length(min = 1, max = 2000, message = "{system.message.length.error}")
	private String activityGameRules;

	/**
	 * 活动省份
	 */
	@NotBlank
	private String activityProvince;

	/**
	 * 活动城市
	 */
	@NotBlank
	private String activityCity;

	/**
	 * 活动图片
	 */
	@NotBlank
	private String activityPicture;

	/**
	 * 活动状态（默认0：准备；1：进行；2，停止）
	 */
	private int activityStatus;

	/**
	 * 活动开始时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date activityBeginDate = new Date();

	/**
	 * 活动结束时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date activityEndDate = new Date((System.currentTimeMillis() / 1000 + 60 * 60 * 24 * 30) * 1000);

	/**
	 * 活动排序
	 */
	private int activityOrderby;
	
	/**
	 * 活动点击量
	 */
	private int activityHit;

	/**
	 * 活动创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date activityCreateTime = new Date();

}

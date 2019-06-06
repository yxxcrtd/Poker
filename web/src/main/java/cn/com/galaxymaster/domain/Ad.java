package cn.com.galaxymaster.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Ad Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class Ad implements Serializable {

	/**
	 * 广告ID
	 */
	private int adId;

	/**
	 * 广告状态（0：显示；1：隐藏；）
	 */
	private int adVisible;

	/**
	 * 广告投放位置（1：首页；2：新闻；3：棋牌游戏；4：赛事专区；5：活动；6：视频；7：棋牌学堂；）
	 */
	private int adLocation;

	/**
	 * 广告标题
	 */
	@NotBlank
	@Length(max = 10, message = "{system.message.length.error}")
	private String adTitle;

	/**
	 * 广告摘要
	 */
	@Length(max = 32, message = "{system.message.length.error}")
	private String adSummary;

	/**
	 * 广告内容
	 */
	@Length(max = 128, message = "{system.message.length.error}")
	private String adContent;

	/**
	 * 广告地址
	 */
	@NotBlank
	@Length(max = 128, message = "{system.message.length.error}")
	private String adUrl;

	/**
	 * 广告排序
	 */
	@NotNull
	@Range(min = 1, max = 32, message = "{system.message.value.error}")
	private int adOrderby;

	/**
	 * 广告图片
	 */
	@NotBlank
	private String adPicture;
	
}

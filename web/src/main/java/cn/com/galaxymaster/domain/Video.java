package cn.com.galaxymaster.domain;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Video Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class Video implements Serializable {

	/**
	 * 视频ID
	 */
	private int videoId;

	/**
	 * 视频状态（0显示；1不显示 ）
	 */
	private int videoStatus;

	/**
	 * 视频分类（0赛事直播；1赛事回放；2专题栏目；3视频教学 -德州扑克；4视频教学 -中国象棋；5视频教学 -斗地主；6视频教学-其他；）
	 */
	private int videoCategory;

	/**
	 * 视频名称
	 */
	@Length(min = 1, max = 32, message = "{system.message.length.error}")
	private String videoTitle;

	/**
	 * 视频摘要
	 */
	@Length(min = 1, max = 128, message = "{system.message.length.error}")
	private String videoSummary;

	/**
	 * 视频地址
	 */
	@Length(min = 1, max = 128, message = "{system.message.length.error}")
	private String videoUrl;
	
	/**
	 * 视频地区
	 */
	@Length(min = 1, max = 22, message = "{system.message.length.error}")
	private String videoArea;
	
	/**
	 * 视频时长
	 */
	@Length(min = 1, max = 10, message = "{system.message.length.error}")
	private String videoDuring;
	
	/**
	 * 视频点击量
	 */
	private int videoHit;

	/**
	 * 视频图片
	 */
	@NotBlank
	private String videoPicture;

	/**
	 * 视频排序
	 */
	private int videoOrderby;

	/**
	 * 视频质量（0蓝光；1超清；2高清；3标清）
	 */
	private int videoQuality;

	/**
	 * 视频发布时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date videoCreateTime;

}
